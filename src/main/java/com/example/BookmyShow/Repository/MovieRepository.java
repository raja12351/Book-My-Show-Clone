package com.example.BookmyShow.Repository;

import com.example.BookmyShow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
