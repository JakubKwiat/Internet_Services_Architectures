package com.example.demo.player.dto;

import com.example.demo.footballteam.entity.FootballTeam;
import com.example.demo.player.entity.Player;
import lombok.*;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostPlayerRequest {

    private String surname;
    private int rate;
    private String footballteam;

    public static Function<PostPlayerRequest, Player> dtoToEntityMapper(
            Function<String, FootballTeam> footballTeamFunction,
            Supplier<FootballTeam> footballTeamSupplier){
        return request -> Player.builder()
                .surname(request.getSurname())
                .rate(request.getRate())
                .footballTeam(footballTeamFunction.apply(request.getFootballteam()))
                .build();
    }

}
