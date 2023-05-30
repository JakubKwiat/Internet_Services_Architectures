package com.example.demo.footballteam.entity;

import com.example.demo.player.entity.Player;
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

    @OneToMany(mappedBy = "footballTeam", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Player> players;

}
