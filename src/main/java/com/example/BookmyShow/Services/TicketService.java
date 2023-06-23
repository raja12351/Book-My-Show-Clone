package com.example.BookmyShow.Services;

import com.example.BookmyShow.Dtos.RequestDtos.TicketRequestDto;
import com.example.BookmyShow.Dtos.ResponseDtos.TicketResponseDto;
import com.example.BookmyShow.Exceptions.NoUserFoundException;
import com.example.BookmyShow.Exceptions.SeatNotAvailableException;
import com.example.BookmyShow.Exceptions.ShowNotFoundException;
import com.example.BookmyShow.Models.Show;
import com.example.BookmyShow.Models.ShowSeat;
import com.example.BookmyShow.Models.User;
import com.example.BookmyShow.Repository.ShowRepository;
import com.example.BookmyShow.Repository.TicketRepository;
import com.example.BookmyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}