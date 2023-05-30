package com.example.demo.footballteam.dto;
import com.example.demo.footballteam.entity.FootballTeam;
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
public class GetFootballTeamsResponse {


    @Singular
    private List<String> footballTeams;

    public static Function<Collection<FootballTeam>, GetFootballTeamsResponse> entityToDtoMapper(){
       return players ->{
           GetFootballTeamsResponseBuilder response = GetFootballTeamsResponse.builder();
           players.stream().map(FootballTeam::getName).forEach(response::footballTeam);
           return response.build();
        };
    }
}
