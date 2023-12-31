package com.example.BookmyShow.Repository;

import com.example.BookmyShow.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {

    Theater findByLocation(String location);
}
