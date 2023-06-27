package com.example.BookmyShow.Services;

import com.example.BookmyShow.Dtos.RequestDtos.AddTheaterDto;
import com.example.BookmyShow.Dtos.RequestDtos.AddTheaterSeatDto;
import com.example.BookmyShow.Enums.SeatType;
import com.example.BookmyShow.Exceptions.NoLocationException;
import com.example.BookmyShow.Exceptions.TheaterNotFoundException;
import com.example.BookmyShow.Models.Theater;
import com.example.BookmyShow.Models.TheaterSeat;
import com.example.BookmyShow.Repository.TheaterRepository;
import com.example.BookmyShow.Transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    public String addTheater(AddTheaterDto addTheaterDto) throws NoLocationException{
        Theater theater = TheaterTransformer.convertDtoToEntity(addTheaterDto);

        if(theater.getLocation()==null){
            throw new NoLocationException("Location of theater is needed!");
        }

        theaterRepository.save(theater);

        return theater.getName() + " Theater is added successfully.";
    }

    public String addTheaterSeats(AddTheaterSeatDto addTheaterSeatDto) throws TheaterNotFoundException{
        Theater theaterOpt = theaterRepository.findByLocation(addTheaterSeatDto.getLocation());

        if(theaterOpt == null){
            throw new TheaterNotFoundException("No theater found on location " + addTheaterSeatDto.getLocation());
        }

        int noOfColumns = addTheaterSeatDto.getNoOfSeatIn1Row();
        int noOfClassic = addTheaterSeatDto.getNoOfClassicSeats();
        int noOfPremium = addTheaterSeatDto.getNoOfPremiumSeats();

        List<TheaterSeat> theaterSeatList = theaterOpt.getTheaterSeatList();

        int seatCount = 1;
        char ch = 'A';

        for(int i=1;i<noOfClassic;i++){
            String seatNo = seatCount+"";
            seatNo+=ch;
            ch++;

            if(ch-'A'==noOfColumns){
                ch = 'A';
                seatCount++;
            }
            TheaterSeat seat = TheaterSeat.builder().seatNo(seatNo)
                               .seatType(SeatType.CLASSIC).theater(theaterOpt).build();
            theaterSeatList.add(seat);
        }

        for(int i=1;i<noOfPremium;i++){
            String seatNo = seatCount+"";
            seatNo+=ch;
            ch++;

            if(ch-'A'==noOfColumns){
                seatCount++;
                ch = 'A';
            }
            TheaterSeat seat = TheaterSeat.builder().seatNo(seatNo)
                    .seatType(SeatType.PREMIUM).theater(theaterOpt).build();
            theaterSeatList.add(seat);
        }

        theaterRepository.save(theaterOpt);

        return "Seats are added in the theater according to requirements.";
    }
}
