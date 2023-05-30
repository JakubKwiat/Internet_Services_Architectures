package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("api/footballteams")
public class FootballTeamController {

    private PlayerService playerService;
    private FootballTeamService footballTeamService;

    @Autowired
    public FootballTeamController(PlayerService playerService, FootballTeamService footballTeamService){
        this.playerService=playerService;
        this.footballTeamService=footballTeamService;
    }

    @GetMapping
    public ResponseEntity<GetFootballTeamsResponse> getFootballTeams(){
        return ResponseEntity.ok(GetFootballTeamsResponse.entityToDtoMapper().apply(footballTeamService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetFootballTeamResponse> getFootballTeam(@PathVariable("name") String name){
        Optional<FootballTeam> footballTeam = FootballTeamService.find(name);
        return footballTeam.map(value-> ResponseEntity.ok(GetFootballTeamResponse.entitytoDtoMapper().apply(value)))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createFootballTeam(@RequestBody CreateFootballTeamRequest request, UriComponentsBuilder builder){
        FootballTeam footballTeam = CreateFootballTeamRequest.dtoToEntityMapper(surname->playerService.find(surname).orElseThrow())
                .apply(request);
        footballTeam=footballTeamService.create(footballTeam);
        return ResponseEntity.created(builder.pathSegment("api","footballteams","{name}").buildAndExpand(footballTeam.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteFootballTeam(@PathVariable("name") String name){
        Optional<FootballTeam> footballTeam = footballTeamService.find(name);

        if(footballTeam.isPresent()){
            footballTeamService.delete(footballTeam.get().getName());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateFootballTeam(@RequestBody UpdateFootballTeamRequest request, @PathVariable("name") String name){
        Optional<FootballTeam> footballTeam= footballTeamService.find(name);

        if(footballTeam.isPresent()){
            UpdateFootballTeamRequest.dtoToEntityUpdater().apply(footballTeam.get(),request);
            footballTeamService.update(footballTeam.get());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
