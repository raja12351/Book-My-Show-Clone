package com.example.BookmyShow.Dtos.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String name;

    private int age;

    private String mobileNo;

    private String emailId;

    private String StatusCode;

    private String StatusMessage;
}
