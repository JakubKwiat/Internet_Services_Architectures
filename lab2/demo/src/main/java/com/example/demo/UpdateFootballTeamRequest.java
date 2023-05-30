package com.example.demo;

import java.util.*;
import java.util.function.BiFunction;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateFootballTeamRequest {
    private String name;
    private int attack;
    private int defence;

    public static BiFunction<FootballTeam,UpdateFootballTeamRequest,FootballTeam> dtoToEntityUpdater(){
        return (footballTeam,request) ->{
            footballTeam.setAttack(request.getAttack());
            footballTeam.setDefence(request.getDefence());
            return footballTeam;
        };
    }
}
