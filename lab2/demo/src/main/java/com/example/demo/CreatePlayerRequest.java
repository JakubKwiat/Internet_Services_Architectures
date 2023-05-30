package com.example.demo;

import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreatePlayerRequest {

    private String surname;
    private int rate;
    private String footballteam;

    public static Function<CreatePlayerRequest,Player> dtoToEntityMapper(
            Function<String,FootballTeam> footballTeamFunction,
            Supplier<FootballTeam> footballTeamSupplier){
        return request -> Player.builder()
                .surname(request.getSurname())
                .rate(request.getRate())
                .footballTeam(footballTeamFunction.apply(request.getFootballteam()))
                .build();
    }

}
