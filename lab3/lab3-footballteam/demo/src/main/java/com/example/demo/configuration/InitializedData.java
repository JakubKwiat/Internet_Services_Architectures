package com.example.demo.configuration;


import com.example.demo.footballteam.entity.FootballTeam;
import com.example.demo.footballteam.service.FootballTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final FootballTeamService footballTeamService;

    @Autowired
    public InitializedData(FootballTeamService footballTeamService){
        this.footballTeamService=footballTeamService;
    }

    @PostConstruct
    private synchronized void init(){
        FootballTeam RealMadryt = FootballTeam.builder()
                .name("Real_Madryt")
                .attack(90)
                .defence(85)
                .build();

        FootballTeam BorussiaDortmund = FootballTeam.builder()
                .name("Borussia_Dortmund")
                .attack(86)
                .defence(80)
                .build();

        FootballTeam LechPoznan= FootballTeam.builder()
                .name("Lech_Poznan")
                .attack(74)
                .defence(73)
                .build();

        footballTeamService.create(RealMadryt);
        footballTeamService.create(BorussiaDortmund);
        footballTeamService.create(LechPoznan);


    }
}
