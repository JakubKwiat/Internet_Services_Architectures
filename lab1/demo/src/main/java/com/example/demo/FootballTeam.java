package com.example.demo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.*;

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
    @OneToMany(mappedBy = "footballTeam")
    @ToString.Exclude
    private List<Player> players;

}
