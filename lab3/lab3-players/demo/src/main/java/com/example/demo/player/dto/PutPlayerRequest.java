package com.example.demo.player.dto;

import java.util.function.BiFunction;

import com.example.demo.player.entity.Player;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutPlayerRequest {
    private int rate;

    public static BiFunction<Player, PutPlayerRequest,Player> dtoToEntityUpdater(){
        return(player,request)->{
            player.setRate(request.getRate());
            return player;
        };
    }
}
