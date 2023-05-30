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
public class CreateFootballTeamRequest {
    private String name;
    private int attack;
    private int defence;

    public static Function<CreateFootballTeamRequest,FootballTeam> dtoToEntityMapper(
            Function<String,Player> playerFunction
    ){
        return request->FootballTeam.builder()
                .name(request.getName())
                .attack(request.getAttack())
                .defence(request.getDefence())
                .build();
    }
}
