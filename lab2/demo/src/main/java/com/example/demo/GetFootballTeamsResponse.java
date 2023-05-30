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
public class GetFootballTeamsResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class FootballTeam{
        private String name;
        private int attack;
        private int defence;
    }

    @Singular
    private List<FootballTeam> footballTeams;

    public static Function<Collection<com.example.demo.FootballTeam>, GetFootballTeamsResponse> entityToDtoMapper(){
        return footballTeams ->{
            GetFootballTeamsResponse.GetFootballTeamsResponseBuilder response = GetFootballTeamsResponse.builder();
            footballTeams.stream()
                    .map(footballTeam -> FootballTeam.builder()
                            .name(footballTeam.getName())
                            .attack(footballTeam.getAttack())
                            .defence(footballTeam.getDefence())
                            .build())
                    .forEach(response::footballTeam);
            return response.build();
        };
    }
}
