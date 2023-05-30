package com.example.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Set;
import java.util.*;
@Component
public class Storage {

    private Set<Player> players = new HashSet<>();

    private Set<FootballTeam> footballTeams = new HashSet<>();

    public synchronized List<Player> findAllPlayers(){return new ArrayList<>(players);}

    public Optional<Player> findPlayer(String surname){
        return players.stream().filter(player -> player.getSurname().equals(surname)).findFirst();
    }

    public synchronized void addPlayer(Player player) throws IllegalArgumentException{
        findPlayer(player.getSurname()).ifPresentOrElse(original ->{throw new IllegalArgumentException("Taki zawodnik już istnieje");},
                ()->players.add(player));
    }

    public synchronized void deletePlayer(String surname) throws IllegalArgumentException{
        findPlayer(surname).ifPresentOrElse(original -> players.remove(original),
                ()->{
                    throw new IllegalArgumentException("Taki zawodnik nie istnieje");
                });
    }

    public synchronized List<FootballTeam> findAllFootballTeams(){return new ArrayList<>(footballTeams);}

    public Optional<FootballTeam> findFootballTeam(String name){
        return footballTeams.stream().filter(footballTeam -> footballTeam.getName().equals(name)).findFirst();
    }

    public synchronized void addFootballTeam(FootballTeam footballTeam) throws IllegalArgumentException{
        findFootballTeam(footballTeam.getName()).ifPresentOrElse(original -> {throw new IllegalArgumentException("Taki klub piłksarski już istnieje");},
                ()->footballTeams.add(footballTeam));
    }

    public synchronized void deleteFootballTeam(String name) throws IllegalArgumentException{
        findFootballTeam(name).ifPresentOrElse(original -> footballTeams.remove(original),
                ()->{
                    throw new IllegalArgumentException("Taki zawodnik nie istnieje");
                });
    }

}
