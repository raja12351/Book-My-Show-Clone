package com.example.BookmyShow.Controllers;

import com.example.BookmyShow.Dtos.RequestDtos.MovieDto;
import com.example.BookmyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody MovieDto movieDto){
        try{
            String result = movieService.addMovie(movieDto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
