package com.example.BookmyShow.Services;

import com.example.BookmyShow.Dtos.RequestDtos.AddUserDto;
import com.example.BookmyShow.Dtos.ResponseDtos.UserResponseDto;
import com.example.BookmyShow.Exceptions.EmailException;
import com.example.BookmyShow.Exceptions.MobileNoException;
import com.example.BookmyShow.Exceptions.NoUserFoundException;
import com.example.BookmyShow.Models.User;
import com.example.BookmyShow.Repository.UserRepository;
import com.example.BookmyShow.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public String addUser(AddUserDto addUserDto) throws MobileNoException, EmailException {
        User user = UserTransformer.convertDtoToEntity(addUserDto);

        if(user.getMobileNo()==null){
            throw new MobileNoException("Mobile number is mandatory in details.");
        }
        if(user.getEmailId()==null){
            throw new EmailException("Email Id is required in details.");
        }

        userRepository.save(user);

        return "User is registered successfully.";
    }

    public UserResponseDto getOldestUser() throws NoUserFoundException{
        List<User> userList = userRepository.findAll();

        int maxAge = 0;
        User oldOne = null;

        for(User users : userList){
            if(users.getAge() > maxAge){
                maxAge = users.getAge();
                oldOne = users;
            }
        }

        if(oldOne == null){
            throw new NoUserFoundException("No user found in database.");
        }

        UserResponseDto responseDto = UserTransformer.convertEntityToDto(oldOne);

        return responseDto;
    }

    public List<User> getOlderThan(int age) {
        List<User> userList = userRepository.findUserWithGreaterAge(age);

        return userList;
    }
}
