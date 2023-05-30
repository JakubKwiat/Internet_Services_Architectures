package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class FootballTeamService {
    private FootballTeamRepository repository;

    @Autowired
    public FootballTeamService(FootballTeamRepository repository){this.repository=repository;}

    public Optional<FootballTeam> find(String name){return repository.find(name);}

    public List<FootballTeam> findAll(){return repository.findAll();}

    public void add(FootballTeam footballTeam){repository.add(footballTeam);}

    public void delete(String name){repository.delete(repository.find(name).orElseThrow());}
}
