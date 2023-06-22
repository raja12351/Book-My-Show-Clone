package com.example.BookmyShow.Transformers;

import com.example.BookmyShow.Dtos.RequestDtos.AddTheaterDto;
import com.example.BookmyShow.Models.Theater;

public class TheaterTransformer {
    public static Theater convertDtoToEntity(AddTheaterDto addTheaterDto){
        Theater theater = Theater.builder().name(addTheaterDto.getName())
                          .location(addTheaterDto.getLocation()).build();

        return theater;
    }
}
