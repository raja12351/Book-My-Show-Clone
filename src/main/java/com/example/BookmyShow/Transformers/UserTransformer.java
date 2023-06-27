package com.example.BookmyShow.Transformers;

import com.example.BookmyShow.Dtos.RequestDtos.AddUserDto;
import com.example.BookmyShow.Dtos.ResponseDtos.UserResponseDto;
import com.example.BookmyShow.Models.User;

public class UserTransformer {

    public static User convertDtoToEntity(AddUserDto userDto){
        User user = User.builder().userName(userDto.getName()).age(userDto.getAge())
                .emailId(userDto.getEmailId()).mobileNo(userDto.getMobileNo()).build();

        return user;
    }

    public static UserResponseDto convertEntityToDto(User user) {
        UserResponseDto responseDto = UserResponseDto.builder().name(user.getUserName()).mobileNo(user.getMobileNo()).
                                      age(user.getAge()).emailId(user.getEmailId()).build();

        return responseDto;
    }
}
