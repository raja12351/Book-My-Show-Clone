package com.example.BookmyShow.Repository;

import com.example.BookmyShow.Dtos.ResponseDtos.QueryResponseDto;
import com.example.BookmyShow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Query(value = "Select movie_id from shows where date = :checkDate group by movie_id order by count(*) desc limit 1",nativeQuery = true)
    public Integer getMostShowMovie(Date checkDate);
}
