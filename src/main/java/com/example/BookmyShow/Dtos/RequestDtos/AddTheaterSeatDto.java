package com.example.BookmyShow.Dtos.RequestDtos;

import lombok.Data;

@Data
public class AddTheaterSeatDto {

    private int noOfSeatIn1Row;

    private int noOfClassicSeats;

    private int noOfPremiumSeats;

    private String location;
}
