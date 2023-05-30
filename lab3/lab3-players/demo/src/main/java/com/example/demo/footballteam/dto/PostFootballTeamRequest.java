package com.example.demo.footballteam.dto;

import com.example.demo.footballteam.entity.FootballTeam;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostFootballTeamRequest {
    private String name;

    public static Function<PostFootballTeamRequest, FootballTeam> dtoToEntityMapper(){

        return request->FootballTeam.builder()
                .name(request.getName())
                .build();
    }
}
