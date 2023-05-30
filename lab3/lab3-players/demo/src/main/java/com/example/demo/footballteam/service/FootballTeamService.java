package com.example.demo.footballteam.service;

import com.example.demo.footballteam.entity.FootballTeam;
import com.example.demo.footballteam.repository.FootballTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
@Service
public class FootballTeamService {
    private FootballTeamRepository repository;

    @Autowired
    public FootballTeamService(FootballTeamRepository repository){this.repository=repository;}

    public Optional<FootballTeam> find(String name){return repository.findById(name);}

    @Transactional
    public void create(FootballTeam footballTeam){repository.save(footballTeam);}


    public void delete(FootballTeam footballTeam){
        repository.delete(footballTeam);
    }

}
