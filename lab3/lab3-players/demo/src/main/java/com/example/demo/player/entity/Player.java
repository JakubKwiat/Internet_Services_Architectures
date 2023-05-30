package com.example.demo.player.entity;

import com.example.demo.footballteam.entity.FootballTeam;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@ToString(callSuper = true)
@SuperBuilder
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name="players")

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
