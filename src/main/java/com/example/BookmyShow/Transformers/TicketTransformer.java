package com.example.BookmyShow.Transformers;

import com.example.BookmyShow.Dtos.RequestDtos.TicketRequestDto;
import com.example.BookmyShow.Dtos.ResponseDtos.TicketResponseDto;
import com.example.BookmyShow.Models.Show;
import com.example.BookmyShow.Models.ShowSeat;
import com.example.BookmyShow.Models.Ticket;

import java.util.List;

public class TicketTransformer {

    public static String convertListToString(List<String> showSeatList){
        StringBuilder result = new StringBuilder();

        for(String seats : showSeatList){
            result.append(seats).append(", ");
        }

        return result.toString();
    }

    public static TicketResponseDto convertEntityToDto(Ticket ticket, Show show){
        TicketResponseDto ticketResponseDto = TicketResponseDto.builder().bookedSeats(ticket.getSeatsBooked())
                .showDate(show.getShowDate()).location(show.getTheater().getLocation())
                .movieName(show.getMovie().getMovieName()).theaterName(show.getTheater().getName())
                .showTime(show.getShowTime()).totalPrice(ticket.getTotalTicketPrice()).build();

        return ticketResponseDto;
    }
}
