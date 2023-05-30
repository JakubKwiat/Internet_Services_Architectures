package com.example.demo.footballteam.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import com.example.demo.footballteam.entity.FootballTeam;
import com.example.demo.footballteam.event.dto.PostFootballTeamRequest;

@Repository
public class FootballTeamEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public FootballTeamEventRepository(@Value("${rpg.characters.url}") String baseUrl){
        restTemplate=new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(FootballTeam footballTeam){
        restTemplate.delete("/footballteams/{name}",footballTeam.getName());
    }

    public void create(FootballTeam footballTeam){
        restTemplate.postForLocation("/footballteams", PostFootballTeamRequest.entityToDtoMapper().apply(footballTeam));
    }


}
