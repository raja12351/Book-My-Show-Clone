package com.example.BookmyShow.Dtos.RequestDtos;

import lombok.Data;

@Data
public class ShowSeatDto {

    private int showId;

    private int priceOfClassicSeats;

    private int priceOfPremiumSeats;
}
