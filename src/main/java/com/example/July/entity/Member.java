package com.example.July.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String joinedDate;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}