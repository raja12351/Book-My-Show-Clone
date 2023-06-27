package com.example.BookmyShow.Services;

import com.example.BookmyShow.Dtos.RequestDtos.TicketRequestDto;
import com.example.BookmyShow.Dtos.ResponseDtos.TicketResponseDto;
import com.example.BookmyShow.Exceptions.NoUserFoundException;
import com.example.BookmyShow.Exceptions.SeatNotAvailableException;
import com.example.BookmyShow.Exceptions.ShowNotFoundException;
import com.example.BookmyShow.Models.Show;
import com.example.BookmyShow.Models.ShowSeat;
import com.example.BookmyShow.Models.Ticket;
import com.example.BookmyShow.Models.User;
import com.example.BookmyShow.Repository.ShowRepository;
import com.example.BookmyShow.Repository.TicketRepository;
import com.example.BookmyShow.Repository.UserRepository;
import com.example.BookmyShow.Transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender emailSender;

    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto) throws NoUserFoundException, ShowNotFoundException, SeatNotAvailableException {
        int userId = ticketRequestDto.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()){
            throw new NoUserFoundException("No user is found with given id " + userId);
        }

        int showId = ticketRequestDto.getShowId();
        Optional<Show> showOptional = showRepository.findById(showId);

        if(showOptional.isEmpty()){
            throw new ShowNotFoundException("No show found with given id " + showId);
        }

        User user = userOptional.get();
        Show show= showOptional.get();

        boolean isValid = validateRequestedSeats(show,ticketRequestDto.getRequestedSeats());

        if(!isValid){
            throw new SeatNotAvailableException("Requested seats are not available.");
        }

        int totalPrice = calculateTotalPrice(show,ticketRequestDto.getRequestedSeats(), ticketRequestDto.isHaveSnacksIncluded());

        Ticket ticket = new Ticket();
        ticket.setTotalTicketPrice(totalPrice);

        String bookedSeats = TicketTransformer.convertListToString(ticketRequestDto.getRequestedSeats());
        ticket.setSeatsBooked(bookedSeats);

        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);

        userRepository.save(user);
        showRepository.save(show);

        TicketResponseDto ticketResponseDto = TicketTransformer.convertEntityToDto(ticket, show);

//        sending mail for booking information
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String body = "Hi "+ user.getUserName() + ","+"\n"+
                "Your have successfully booked seats : " + bookedSeats + "for the movie " + show.getMovie().getMovieName() +
                " on " + show.getShowDate() + " at " + show.getShowTime() + "\n" +
                "Thank you for the booking ,Enjoy the show.";

        mailMessage.setSubject("Booking Confirmation!");
        mailMessage.setText(body);
        mailMessage.setTo(user.getEmailId());
        mailMessage.setFrom("springtesting17@gmail.com");

        emailSender.send(mailMessage);


        return ticketResponseDto;
    }

    private boolean validateRequestedSeats(Show show, List<String> requestedSeats){
        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(ShowSeat showSeat : showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo()) && showSeat.isBooked()){
                return false;
            }
        }
        return true;
    }

    private int calculateTotalPrice(Show show, List<String> requestedSeats, boolean isSnacksIncluded){
        List<ShowSeat> showSeatList = show.getShowSeatList();
        int totalPrice = 0;

        for(ShowSeat showSeat : showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalPrice += showSeat.getPrice();
                showSeat.setBooked(true);

                //setting booking of snacks from here
                if(isSnacksIncluded){
                    showSeat.setSnacksAdded(true);
                }
            }
        }

        return totalPrice;
    }
}
