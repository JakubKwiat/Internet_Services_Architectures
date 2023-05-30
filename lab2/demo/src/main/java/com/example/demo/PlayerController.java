package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("api/players")
public class PlayerController {
    private PlayerService playerService;
    private FootballTeamService footballTeamService;

    @Autowired
    public PlayerController(PlayerService playerService, FootballTeamService footballTeamService){
        this.playerService=playerService;
        this.footballTeamService=footballTeamService;
    }

    @GetMapping
    public ResponseEntity<GetPlayersResponse> getPlayers(){
        return ResponseEntity.ok(GetPlayersResponse.entityToDtoMapper().apply(playerService.findAll()));
    }

    @GetMapping({"{surname}"})
    public ResponseEntity<GetPlayerResponse> getPlayer(@PathVariable("surname") String surname){
        Optional<Player> player =playerService.find(surname);
        return player.map(value-> ResponseEntity.ok(GetPlayerResponse.entityToDToMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createPlayer(@RequestBody CreatePlayerRequest request, UriComponentsBuilder builder){
        Player player = CreatePlayerRequest
                .dtoToEntityMapper(name-> FootballTeamService.find(name).orElseThrow(),()->null)
                .apply(request);
        player=playerService.add(player);
        return ResponseEntity.created(builder.pathSegment("api","players","{surname}").buildAndExpand(player.getSurname()).toUri()).build();
    }

    @DeleteMapping("{surname}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("surname") String surname){
        Optional<Player> player=playerService.find(surname);

        if(player.isPresent()){
            playerService.delete(player.get().getSurname());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{surname}")
    public ResponseEntity<Void> updatePlayer(@RequestBody UpdatePlayerRequest request, @PathVariable("surname") String surname){
        Optional<Player> player= playerService.find(surname);
        if(player.isPresent()){
            UpdatePlayerRequest.dtoToEntityUpdater().apply(player.get(),request);
            playerService.update(player.get());
            return ResponseEntity.accepted().build();}
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
