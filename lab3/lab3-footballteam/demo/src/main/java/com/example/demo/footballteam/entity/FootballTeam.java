package com.example.demo.footballteam.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder
@Entity
@Setter
@Getter
@Table(name="footballteams")
public class FootballTeam {
    @Id
    private String name;
    @Column
    private int attack;
    @Column
    private int defence;
}
