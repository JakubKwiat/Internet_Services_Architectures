package com.example.demo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.*;
@ToString
@SuperBuilder
@Entity
@NoArgsConstructor
@Table(name="players")
@Getter
@Setter
public class Player {
    @Id
    private String surname;

    @Column
    private int rate;

    @ManyToOne
    @JoinColumn(name="club")
    private FootballTeam footballTeam;



}
