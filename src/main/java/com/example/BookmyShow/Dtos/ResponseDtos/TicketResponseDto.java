package com.example.BookmyShow.Dtos.ResponseDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class TicketResponseDto {

    private LocalTime showTime;

    private Date showDate;

    private String movieName;

    private String theaterName;

    private String bookedSeats;

    private String statusCode;

    private String statusMessage;
}
