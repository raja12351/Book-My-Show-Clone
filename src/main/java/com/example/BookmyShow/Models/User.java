package com.example.BookmyShow.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private int age;

    @Column(nullable = false)
    private String mobileNo;

    private String emailId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> ticketList = new ArrayList<>();
}
