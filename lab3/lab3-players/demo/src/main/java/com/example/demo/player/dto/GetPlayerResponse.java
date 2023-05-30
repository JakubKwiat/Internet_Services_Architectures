package com.example.demo.player.dto;
import com.example.demo.player.entity.Player;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class GetPlayerResponse {
    private String surname;
    private int rate;
    private String footballTeam;

    public static Function<Player,GetPlayerResponse> entityToDToMapper(){
        return player -> GetPlayerResponse.builder()
                .surname(player.getSurname())
                .rate(player.getRate())
                .footballTeam(player.getFootballTeam().getName())
                .build();
    }
}
