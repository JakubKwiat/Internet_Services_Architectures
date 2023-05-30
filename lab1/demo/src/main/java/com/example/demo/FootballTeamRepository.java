package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FootballTeamRepository {
    public Storage storage;

    @Autowired
    public FootballTeamRepository(Storage storage){this.storage=storage;}

    public Optional<FootballTeam> find(String name){return storage.findFootballTeam(name);}

    public List<FootballTeam> findAll(){return storage.findAllFootballTeams();}

    public void add(FootballTeam entity){storage.addFootballTeam(entity);}

    public void delete(FootballTeam entity){storage.deleteFootballTeam(entity.getName());}
}
