package com.example.BookmyShow.Dtos.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryResponseDto {

    private Integer movieId;

    private String movieName;

    private String statusCode;

    private String statusMessage;
}
