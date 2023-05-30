package com.example.demo.footballteam.controller;
import com.example.demo.footballteam.dto.GetFootballTeamResponse;
import com.example.demo.footballteam.dto.GetFootballTeamsResponse;
import com.example.demo.footballteam.dto.PostFootballTeamRequest;
import com.example.demo.footballteam.dto.PutFootballTeamRequest;
import com.example.demo.footballteam.entity.FootballTeam;
import com.example.demo.footballteam.service.FootballTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("api/footballteams")
public class FootballTeamController {

    private FootballTeamService footballTeamService;

    @Autowired
    public FootballTeamController(FootballTeamService footballTeamService){
        this.footballTeamService=footballTeamService;
    }

    @GetMapping
    public ResponseEntity<GetFootballTeamsResponse> getFootballTeams(){
        return ResponseEntity.ok(GetFootballTeamsResponse.entityToDtoMapper().apply(footballTeamService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetFootballTeamResponse> getFootballTeam(@PathVariable("name") String name){
        Optional<FootballTeam> footballTeam = footballTeamService.find(name);
        return footballTeam.map(value-> ResponseEntity.ok(GetFootballTeamResponse.entitytoDtoMapper().apply(value)))
                .orElseGet(()-> ResponseEntity.notFound().build());                 //tutaj wojcik ma inaczej
    }

    @PostMapping("")
    public ResponseEntity<Void> postFootballTeam(@RequestBody PostFootballTeamRequest request, UriComponentsBuilder builder){
        FootballTeam footballTeam = PostFootballTeamRequest.dtoToEntityMapper().apply(request);
        footballTeamService.create(footballTeam);
        return ResponseEntity.created(builder.pathSegment("api","footballteams","{name}").buildAndExpand(footballTeam.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteFootballTeam(@PathVariable("name") String name){
        Optional<FootballTeam> footballTeam = footballTeamService.find(name);
        if(footballTeam.isPresent()){
            footballTeamService.delete(footballTeam.get());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> putFootballTeam(@RequestBody PutFootballTeamRequest request, @PathVariable("name") String name){
        Optional<FootballTeam> footballTeam= footballTeamService.find(name);
        if(footballTeam.isPresent()){
            PutFootballTeamRequest.dtoToEntityUpdater().apply(footballTeam.get(),request);
            footballTeamService.put(footballTeam.get());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
