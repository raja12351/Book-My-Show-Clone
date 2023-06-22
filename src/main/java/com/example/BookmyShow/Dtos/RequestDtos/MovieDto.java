package com.example.BookmyShow.Dtos.RequestDtos;

import com.example.BookmyShow.Enums.Genre;
import com.example.BookmyShow.Enums.Language;
import com.fasterxml.jackson.databind.DatabindException;
import lombok.Data;

import java.util.Date;

@Data
public class MovieDto {

    private String movieName;

    private double duration;

    private double rating;

    private Date releaseDate;

    private Genre genre;

    private Language language;
}
