package com.example.BookmyShow.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AddShowDto {

    private LocalTime showTime;

    private Date showDate;

    private int theaterId;

    private int movieId;
}
