package com.example.BookmyShow.Services;

import com.example.BookmyShow.Dtos.RequestDtos.AddShowDto;
import com.example.BookmyShow.Dtos.RequestDtos.MaxShowDto;
import com.example.BookmyShow.Dtos.RequestDtos.ShowSeatDto;
import com.example.BookmyShow.Dtos.ResponseDtos.QueryResponseDto;
import com.example.BookmyShow.Enums.SeatType;
import com.example.BookmyShow.Exceptions.MovieNotFoundException;
import com.example.BookmyShow.Exceptions.ShowNotFoundException;
import com.example.BookmyShow.Exceptions.TheaterNotFoundException;
import com.example.BookmyShow.Models.*;
import com.example.BookmyShow.Repository.MovieRepository;
import com.example.BookmyShow.Repository.ShowRepository;
import com.example.BookmyShow.Repository.TheaterRepository;
import com.example.BookmyShow.Transformers.MovieTransformer;
import com.example.BookmyShow.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;
    public String addshow(AddShowDto addShowDto) throws TheaterNotFoundException, MovieNotFoundException {
        Show show = ShowTransformer.convertDtoToEntity(addShowDto);

        Optional<Movie> movieOpt = movieRepository.findById(addShowDto.getMovieId());

        if(movieOpt.isEmpty()){
            throw new MovieNotFoundException("There is no movie with id " + addShowDto.getMovieId());
        }
        Optional<Theater> theaterOpt = theaterRepository.findById(addShowDto.getTheaterId());

        if(theaterOpt.isEmpty()){
            throw new TheaterNotFoundException("There is no theater with id " + addShowDto.getTheaterId());
        }

        Movie movie = movieOpt.get();
        Theater theater = theaterOpt.get();

        show.setMovie(movie);
        show.setTheater(theater);

        show = showRepository.save(show);

        movie.getShowList().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show has been added with showId as " + show.getId();
    }

    public String associateSeat(ShowSeatDto showSeatDto) throws ShowNotFoundException {
        Optional<Show> showOptional = showRepository.findById(showSeatDto.getShowId());

        if(showOptional.isEmpty()){
            throw new ShowNotFoundException("There is no show with id " + showSeatDto.getShowId());
        }

        Show show = showOptional.get();

        Theater theater = show.getTheater();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(TheaterSeat theaterSeat : theaterSeatList){
            ShowSeat showSeat = new ShowSeat();

            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(showSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showSeatDto.getPriceOfClassicSeats());
            }else{
                showSeat.setPrice(showSeatDto.getPriceOfPremiumSeats());
            }

            showSeat.setShow(show);
            showSeat.setBooked(false);
            showSeat.setSnacksAdded(false);

            showSeatList.add(showSeat);
        }
        showRepository.save(show);

        return "Show seats has been successfully associated with the show.";
    }

    public QueryResponseDto getMovieWithMaxShow(MaxShowDto showDto) throws MovieNotFoundException{
        Date date = showDto.getShowDate();

        Integer movieId = showRepository.getMostShowMovie(date);

        if(movieId==null){
            throw new MovieNotFoundException("There is no movie with given id : " + movieId);
        }

        Movie movie = movieRepository.findById(movieId).get();

        QueryResponseDto responseDto = MovieTransformer.detailsToDto(movie,movieId);

        return responseDto;
    }
}
