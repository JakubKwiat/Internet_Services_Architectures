package com.example.demo.footballteam.service;

import com.example.demo.footballteam.entity.FootballTeam;
import com.example.demo.footballteam.event.repository.FootballTeamEventRepository;
import com.example.demo.footballteam.repository.FootballTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
@Service
public class FootballTeamService {
    private FootballTeamRepository repository;
    private FootballTeamEventRepository eventRepository;

    @Autowired
    public FootballTeamService(FootballTeamRepository repository, FootballTeamEventRepository eventRepository){
        this.repository=repository;
        this.eventRepository=eventRepository;
    }

    public Optional<FootballTeam> find(String name){return repository.findById(name);}


    public List<FootballTeam> findAll(){return repository.findAll();}

    @Transactional
    public void create(FootballTeam footballTeam){
        repository.save(footballTeam);
        eventRepository.create(footballTeam);
    }

    @Transactional
    public void delete(FootballTeam footballTeam){
        eventRepository.delete(footballTeam);
        repository.delete(footballTeam);
    }

    @Transactional
    public void put(FootballTeam footballTeam){
        repository.save(footballTeam);
        eventRepository.create(footballTeam);
    }

}
