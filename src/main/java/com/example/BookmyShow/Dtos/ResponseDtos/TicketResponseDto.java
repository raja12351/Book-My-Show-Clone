package com.example.BookmyShow.Dtos.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {

    private LocalTime showTime;

    private Date showDate;

    private String movieName;

    private String theaterName;

    private String location;

    private String bookedSeats;

    private String statusCode;

    private String statusMessage;
}
