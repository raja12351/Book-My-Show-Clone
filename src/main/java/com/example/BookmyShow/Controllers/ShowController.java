package com.example.BookmyShow.Controllers;

import com.example.BookmyShow.Dtos.RequestDtos.AddShowDto;
import com.example.BookmyShow.Dtos.RequestDtos.MaxShowDto;
import com.example.BookmyShow.Dtos.RequestDtos.ShowSeatDto;
import com.example.BookmyShow.Dtos.ResponseDtos.QueryResponseDto;
import com.example.BookmyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/addShow")
    public ResponseEntity<String> addShow(@RequestBody AddShowDto addShowDto){
        try{
            String result = showService.addshow(addShowDto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/associateSeats")
    public ResponseEntity<String> associateSeats(@RequestBody ShowSeatDto showSeatDto){
        try{
            String result = showService.associateSeat(showSeatDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/MostRecommendedMovie")
    public ResponseEntity<QueryResponseDto> getMovieWithMaxShow(@RequestBody MaxShowDto showDto){
        try{
            QueryResponseDto responseDto = showService.getMovieWithMaxShow(showDto);
            responseDto.setStatusCode("200");
            responseDto.setStatusMessage("Movie is found.");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch(Exception e){
            QueryResponseDto responseDto = new QueryResponseDto();
            responseDto.setStatusCode("500");
            responseDto.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }
    }
}
