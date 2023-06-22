package com.example.BookmyShow.Transformers;

import com.example.BookmyShow.Dtos.RequestDtos.MovieDto;
import com.example.BookmyShow.Models.Movie;

public class MovieTransformer {

    public static Movie convertDtoToEntity(MovieDto movieDto){
        Movie movie = Movie.builder().movieName(movieDto.getMovieName()).duration(movieDto.getDuration())
                .releaseDate(movieDto.getReleaseDate()).genre(movieDto.getGenre())
                .language(movieDto.getLanguage()).rating(movieDto.getRating()).build();

        return movie;
    }
}
