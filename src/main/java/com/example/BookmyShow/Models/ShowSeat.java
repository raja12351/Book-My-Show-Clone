package com.example.BookmyShow.Models;

import com.example.BookmyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "show_seats")
@Data
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private int price;

    private boolean isBooked;

    private boolean isSnacksAdded;

    @ManyToOne
    @JoinColumn
    private Show show;
}
