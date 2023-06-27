package com.example.BookmyShow.Dtos.RequestDtos;

import com.example.BookmyShow.Enums.Genre;
import com.example.BookmyShow.Enums.Language;
import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class MovieDto {

    private String movieName;

    private double duration;

    private double rating;

    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Language language;
}
