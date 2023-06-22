package com.example.BookmyShow.Services;

import com.example.BookmyShow.Dtos.RequestDtos.MovieDto;
import com.example.BookmyShow.Exceptions.GenreException;
import com.example.BookmyShow.Exceptions.MovieNameMustException;
import com.example.BookmyShow.Models.Movie;
import com.example.BookmyShow.Repository.MovieRepository;
import com.example.BookmyShow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieDto movieDto) throws MovieNameMustException, GenreException{
        Movie movie = MovieTransformer.convertDtoToEntity(movieDto);

        if(movie.getMovieName()==null){
            throw new MovieNameMustException("Movie name is must while adding.");
        }

        if(movie.getGenre() == null){
            throw new GenreException("Genre of the movie should be provided while adding.");
        }

        movieRepository.save(movie);

        return "Movie is added successfully.";
    }
}
