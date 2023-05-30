package com.example.demo.footballteam.event.dto;

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


    public static Function<FootballTeam, PostFootballTeamRequest> entityToDtoMapper() {
       return entity -> PostFootballTeamRequest.builder().name(entity.getName()).build();
    }
}
