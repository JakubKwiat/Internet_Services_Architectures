package com.example.demo.footballteam.dto;

import lombok.*;
import com.example.demo.footballteam.entity.FootballTeam;
import java.util.function.BiFunction;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutFootballTeamRequest {
    private String name;
    private int attack;
    private int defence;

    public static BiFunction<FootballTeam, PutFootballTeamRequest,FootballTeam> dtoToEntityUpdater(){
        return (footballTeam,request) ->{
            footballTeam.setAttack(request.getAttack());
            footballTeam.setDefence(request.getDefence());
            return footballTeam;
        };
    }
}
