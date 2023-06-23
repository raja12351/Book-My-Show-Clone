package com.example.BookmyShow.Controllers;

import com.example.BookmyShow.Dtos.RequestDtos.TicketRequestDto;
import com.example.BookmyShow.Dtos.ResponseDtos.TicketResponseDto;
import com.example.BookmyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/bookTicket")
    public ResponseEntity<TicketResponseDto> bookTickets(@RequestBody TicketRequestDto ticketRequestDto){
        try{
            TicketResponseDto result = ticketService.bookTicket(ticketRequestDto);
            result.setStatusCode("200");
            result.setStatusMessage("Ticket is booked for the given movie.");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            TicketResponseDto responseDto = new TicketResponseDto();
            responseDto.setStatusCode("500");
            responseDto.setStatusMessage("Ticket is not booked yet.");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }
}
