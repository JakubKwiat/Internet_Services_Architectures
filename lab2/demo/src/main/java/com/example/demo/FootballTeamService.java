package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
@Service
public class FootballTeamService {
    private static FootballTeamRepository repository;

    @Autowired
    public FootballTeamService(FootballTeamRepository repository){this.repository=repository;}

    public static Optional<FootballTeam> find(String name){return repository.findById(name);}

    public List<FootballTeam> findAll(){return repository.findAll();}

    @Transactional
    public FootballTeam create(FootballTeam footballTeam){return repository.save(footballTeam);}


    public void delete(String name){
        repository.deleteById(name);
    }

    @Transactional
    public void update(FootballTeam footballTeam){repository.save(footballTeam);}
}
