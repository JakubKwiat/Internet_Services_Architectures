package com.example.demo;

import lombok.*;
import java.util.*;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFootballTeamResponse {
    private String name;
    private int attack;
    private int defence;

    public static Function<FootballTeam,GetFootballTeamResponse> entitytoDtoMapper(){
        return footballTeam -> GetFootballTeamResponse.builder()
                .name(footballTeam.getName())
                .attack(footballTeam.getAttack())
                .defence(footballTeam.getDefence())
                .build();
    }
}
