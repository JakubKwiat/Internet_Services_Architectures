package com.example.demo.player.service;

import com.example.demo.footballteam.entity.FootballTeam;
import com.example.demo.footballteam.repository.FootballTeamRepository;
import com.example.demo.player.entity.Player;
import com.example.demo.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class PlayerService {
    private PlayerRepository playerRepository;
    private FootballTeamRepository footballTeamRepository;

    @Autowired
    public void PlayerService(PlayerRepository playerRepository, FootballTeamRepository footballTeamRepository){
        this.playerRepository=playerRepository;
        this.footballTeamRepository=footballTeamRepository;
    }

    public Optional<Player> find(String surname){return playerRepository.findById(surname);}

    public Optional<Player> find(FootballTeam footballTeam, String surname){return playerRepository.findBySurnameAndFootballTeam(surname,footballTeam);}

    public Optional<Player> find (String name,String surname){
        Optional<FootballTeam> footballTeam = footballTeamRepository.findById(name);
        if(footballTeam.isPresent()){
            return  playerRepository.findBySurnameAndFootballTeam(surname,footballTeam.get());
        }else{
            return Optional.empty();
        }
    }
    public List<Player> findAll(){return  playerRepository.findAll();}

    public List<Player> findAll(FootballTeam footballTeam){return  playerRepository.findAllByFootballTeam(footballTeam);}
    @Transactional
    public Player create(Player player){return playerRepository.save(player);}

    @Transactional
    public void delete(String surname){playerRepository.deleteById(surname);}

    @Transactional
    public void update (Player player) {playerRepository.save(player);}

}
