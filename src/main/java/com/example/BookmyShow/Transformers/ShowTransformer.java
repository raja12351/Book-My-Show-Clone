package com.example.BookmyShow.Transformers;

import com.example.BookmyShow.Dtos.RequestDtos.AddShowDto;
import com.example.BookmyShow.Models.Show;
import com.example.BookmyShow.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ShowTransformer {

    public static Show convertDtoToEntity(AddShowDto addShowDto){
        Show show = Show.builder().showTime(addShowDto.getShowTime()).showDate(addShowDto.getShowDate())
                .build();

        return show;
    }
}
