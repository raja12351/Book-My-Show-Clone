package com.example.BookmyShow.Controllers;

import com.example.BookmyShow.Dtos.RequestDtos.AddUserDto;
import com.example.BookmyShow.Dtos.ResponseDtos.UserResponseDto;
import com.example.BookmyShow.Exceptions.NoUserFoundException;
import com.example.BookmyShow.Models.User;
import com.example.BookmyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody AddUserDto addUserDto){
       try {
           String result = userService.addUser(addUserDto);
           return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/getOldestUser")
    public ResponseEntity<UserResponseDto> getOldestUser(){
        try{
            UserResponseDto responseDto = userService.getOldestUser();
            responseDto.setStatusCode("200");
            responseDto.setStatusMessage("User is founded in the database");

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch(Exception e){
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setStatusCode("500");
            userResponseDto.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(userResponseDto, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUserOlderThan(@RequestParam int age){
        try {
            List<User> userList = userService.getOlderThan(age);
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
