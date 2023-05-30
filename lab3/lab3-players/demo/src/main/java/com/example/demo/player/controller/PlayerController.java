package com.example.demo.player.controller;
import com.example.demo.footballteam.service.FootballTeamService;
import com.example.demo.player.dto.PostPlayerRequest;
import com.example.demo.player.dto.GetPlayerResponse;
import com.example.demo.player.dto.GetPlayersResponse;
import com.example.demo.player.dto.PutPlayerRequest;
import com.example.demo.player.entity.Player;
import com.example.demo.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetPlayersResponse> getPlayers(){
        return ResponseEntity.ok(GetPlayersResponse.entityToDtoMapper().apply(playerService.findAll()));
    }

    @GetMapping({"{surname}"})
    public ResponseEntity<GetPlayerResponse> getPlayer(@PathVariable("surname") String surname){
        return playerService.find(surname).map(value->ResponseEntity.ok(GetPlayerResponse.entityToDToMapper().apply(value)))
                .orElseGet(()->ResponseEntity
                .notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postPlayer(@RequestBody PostPlayerRequest request, UriComponentsBuilder builder){
        Player player = PostPlayerRequest
                .dtoToEntityMapper(name-> footballTeamService.find(name).orElseThrow(),()->null)
                .apply(request);
        player=playerService.create(player);
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
    public ResponseEntity<Void> putPlayer(@RequestBody PutPlayerRequest request, @PathVariable("surname") String surname){
        Optional<Player> player= playerService.find(surname);
        if(player.isPresent()){
            PutPlayerRequest.dtoToEntityUpdater().apply(player.get(),request);
            playerService.update(player.get());
            return ResponseEntity.accepted().build();}
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
