package com.example.demo.configuration;


import com.example.demo.footballteam.entity.FootballTeam;
import com.example.demo.footballteam.service.FootballTeamService;
import com.example.demo.player.entity.Player;
import com.example.demo.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final FootballTeamService footballTeamService;
    private final PlayerService playerService;

    @Autowired
    public InitializedData(FootballTeamService footballTeamService, PlayerService playerService){
        this.footballTeamService=footballTeamService;
        this.playerService=playerService;
    }

    @PostConstruct
    private synchronized void init(){
        FootballTeam RealMadryt = FootballTeam.builder()
                .name("Real_Madryt")
                .build();

        FootballTeam BorussiaDortmund = FootballTeam.builder()
                .name("Borussia_Dortmund")
                .build();

        FootballTeam LechPoznan= FootballTeam.builder()
                .name("Lech_Poznan")
                .build();

        footballTeamService.create(RealMadryt);
        footballTeamService.create(BorussiaDortmund);
        footballTeamService.create(LechPoznan);

        Player KarimBenzema = Player.builder()
                .surname("Benzema")
                .rate(91)
                .footballTeam(RealMadryt)
                .build();

        Player ThibautCortuois = Player.builder()
                .surname("Courtois")
                .rate(92)
                .footballTeam(RealMadryt)
                .build();

        Player Vinicius = Player.builder()
                .surname("Vinicius")
                .rate(88)
                .footballTeam(RealMadryt)
                .build();

        Player JudeBellingham = Player.builder()
                .surname("Bellingham")
                .rate(86)
                .footballTeam(BorussiaDortmund)
                .build();

        Player MarcoReus=Player.builder()
                .surname("Reus")
                .rate(85)
                .footballTeam(BorussiaDortmund)
                .build();

        Player YoussoufaMoukoko=Player.builder()
                .surname("Moukoko")
                .rate(83)
                .footballTeam(BorussiaDortmund)
                .build();

        Player MichalSkoras=Player.builder()
                .surname("Skoras")
                .rate(73)
                .footballTeam(LechPoznan)
                .build();

        Player FilipBednarek=Player.builder()
                .surname("Bednarek")
                .rate(71)
                .footballTeam(LechPoznan)
                .build();

        Player MikaelIshak=Player.builder()
                .surname("Ishak")
                .rate(74)
                .footballTeam(LechPoznan)
                .build();

        playerService.create(KarimBenzema);
        playerService.create(ThibautCortuois);
        playerService.create(Vinicius);
        playerService.create(JudeBellingham);
        playerService.create(MarcoReus);
        playerService.create(YoussoufaMoukoko);
        playerService.create(MichalSkoras);
        playerService.create(FilipBednarek);
        playerService.create(MikaelIshak);
    }
}
