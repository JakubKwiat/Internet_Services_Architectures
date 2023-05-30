package com.example.demo.player.controller;

import com.example.demo.footballteam.entity.FootballTeam;
import com.example.demo.footballteam.service.FootballTeamService;
import com.example.demo.player.dto.GetPlayerResponse;
import com.example.demo.player.dto.GetPlayersResponse;
import com.example.demo.player.dto.PostPlayerRequest;
import com.example.demo.player.dto.PutPlayerRequest;
import com.example.demo.player.entity.Player;
import com.example.demo.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("api/footballteams/{name}/players")
public class PlayerFootballTeamController {
    private PlayerService playerService;
    private FootballTeamService footballTeamService;

    @Autowired
    void FootballTeamPlayerController(PlayerService playerService, FootballTeamService footballTeamService){
        this.playerService=playerService;
        this.footballTeamService=footballTeamService;
    }

    @GetMapping
    public ResponseEntity<GetPlayersResponse> getPlayers(@PathVariable("name") String name){
        Optional<FootballTeam> footballTeam = footballTeamService.find(name);
        return footballTeam.map(value -> ResponseEntity.ok(GetPlayersResponse.entityToDtoMapper().apply(playerService.findAll(value))))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("{surname}")
    public ResponseEntity<GetPlayerResponse> getPlayer(@PathVariable("name") String name,
                                                       @PathVariable("surname")String surname){
        return playerService.find(name,surname).map(value->ResponseEntity.ok(GetPlayerResponse.entityToDToMapper().apply(value)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postPlayer(@PathVariable("name") String name,
                                             @RequestBody PostPlayerRequest request,
                                             UriComponentsBuilder builder){
        Optional<FootballTeam> footballTeam=footballTeamService.find(name);
        if(footballTeam.isPresent()){
            Player player= PostPlayerRequest
                    .dtoToEntityMapper(value -> footballTeamService.find(value).orElseThrow(),footballTeam::get)
                    .apply(request);
            player=playerService.create(player);
            return ResponseEntity.created(builder.pathSegment("api","footballteams","{name}","players","{surname}")
                    .buildAndExpand(footballTeam.get().getName(),player.getSurname()).toUri()).build();

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{surname}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("name") String name,
                                             @PathVariable("surname") String surname){
        Optional<Player> player = playerService.find(name,surname);

        if(player.isPresent()){
            playerService.delete(player.get().getSurname());
            return ResponseEntity.accepted().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("surname")
    public ResponseEntity<Void> updatePlayer(@PathVariable("name") String name,
                                             @RequestBody PutPlayerRequest request,
                                             @PathVariable("surname") String surname){
        Optional<Player> player  = playerService.find(name,surname);

        if(player.isPresent()){
            PutPlayerRequest.dtoToEntityUpdater().apply(player.get(),request);
            playerService.update(player.get());
            return ResponseEntity.accepted().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
