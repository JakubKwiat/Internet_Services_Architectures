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
public class UpdatePlayerRequest {
    private int rate;

    public static BiFunction<Player,UpdatePlayerRequest,Player> dtoToEntityUpdater(){
        return(player,request)->{
            player.setRate(request.getRate());
            return player;
        };
    }
}
