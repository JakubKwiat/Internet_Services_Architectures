package com.example.demo.player.dto;

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
public class GetPlayersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Player{
        private String surname;
        private int rate;
    }

    @Singular
    private List<Player> players;

    public static Function<Collection<com.example.demo.player.entity.Player>, GetPlayersResponse> entityToDtoMapper(){
        return players -> {
            GetPlayersResponseBuilder response = GetPlayersResponse.builder();
            players.stream()
                    .map(player -> Player.builder()
                            .surname(player.getSurname())
                            .rate(player.getRate())
                            .build())
                    .forEach(response::player);
            return  response.build();
        };
    }
}
