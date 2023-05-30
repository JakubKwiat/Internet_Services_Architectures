package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class PlayerService {
    private PlayerRepository repository;

    @Autowired
    public void PlayerService(PlayerRepository repository){this.repository=repository;}

    public Optional<Player> find(String surname){return repository.findById(surname);}

    public Optional<Player> find(FootballTeam footballTeam,String surname){return repository.findBySurnameAndFootballTeam(surname,footballTeam);}

    public List<Player> findAll(){return  repository.findAll();}

    public List<Player> findAll(FootballTeam footballTeam){return  repository.findAllByFootballTeam(footballTeam);}
    @Transactional
    public Player add(Player player){return repository.save(player);}

    @Transactional
    public void delete(String surname){repository.deleteById(surname);}

    @Transactional
    public void update (Player player) {repository.save(player);}

}
