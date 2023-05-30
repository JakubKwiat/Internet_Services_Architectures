package com.example.demo;

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
        Optional<FootballTeam> footballTeam = footballTeamService.find(name);
        if(footballTeam.isPresent()){
            Optional<Player> player=playerService.find(footballTeam.get(),surname);
            return player.map(value-> ResponseEntity.ok(GetPlayerResponse.entityToDToMapper().apply(value)))
                    .orElseGet(()->ResponseEntity.notFound().build());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createPlayer(@PathVariable("name") String name,
                                             @RequestBody CreatePlayerRequest request,
                                             UriComponentsBuilder builder){
        Optional<FootballTeam> footballTeam=footballTeamService.find(name);

        if(footballTeam.isPresent()){
            Player player= CreatePlayerRequest
                    .dtoToEntityMapper(value -> FootballTeamService.find(value).orElseThrow(),footballTeam::get)
                    .apply(request);
            player=playerService.add(player);
            return ResponseEntity.created(builder.pathSegment("api","footballteams","{name}","players","{surname")
                    .buildAndExpand(footballTeam.get().getName(),player.getSurname()).toUri()).build();

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{surname}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("name") String name,
                                             @PathVariable("surname") String surname){
        Optional<FootballTeam> footballTeam =footballTeamService.find(name);

        if(footballTeam.isPresent()){
            Optional<Player> player = playerService.find(footballTeam.get(),surname);

            if(player.isPresent()){
                playerService.delete(player.get().getSurname());
                return ResponseEntity.accepted().build();
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("surname")
    public ResponseEntity<Void> updatePlayer(@PathVariable("name") String name,
                                             @RequestBody UpdatePlayerRequest request,
                                             @PathVariable("surname") String surname){
        Optional<FootballTeam> footballTeam = footballTeamService.find(name);

        if(footballTeam.isPresent()) {
            Optional<Player> player = playerService.find(footballTeam.get(), surname);

            if (player.isPresent()) {
                UpdatePlayerRequest.dtoToEntityUpdater().apply(player.get(), request);
                playerService.update(player.get());
                return ResponseEntity.accepted().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        else{
            return  ResponseEntity.notFound().build();
        }
    }
}
