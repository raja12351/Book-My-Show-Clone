package com.example.BookmyShow.Controllers;

import com.example.BookmyShow.Dtos.RequestDtos.AddTheaterDto;
import com.example.BookmyShow.Dtos.RequestDtos.AddTheaterSeatDto;
import com.example.BookmyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/addTheater")
    public ResponseEntity<String> addTheater(@RequestBody AddTheaterDto addTheaterDto){
        try{
            String result = theaterService.addTheater(addTheaterDto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addTheaterSeats")
    public ResponseEntity<String> addTheaterSeats(@RequestBody AddTheaterSeatDto addTheaterSeatDto){
        try{
            String result = theaterService.addTheaterSeats(addTheaterSeatDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
