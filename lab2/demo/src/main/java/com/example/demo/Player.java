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
@AllArgsConstructor
public class Player {
    @Id
    private String surname;

    @Column
    private int rate;

    @ManyToOne
    @JoinColumn(name="footballTeam")
    private FootballTeam footballTeam;

//    public Player(String surname, int rate,FootballTeam footballTeam){
//        this.surname=surname;
//        this.rate=rate;
//        this.footballTeam=footballTeam;
//    }

}
