package com.example.demo.player.repository;

import com.example.demo.footballteam.entity.FootballTeam;
import com.example.demo.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findBySurnameAndFootballTeam(String surname, FootballTeam footballTeam);

    List<Player> findAllByFootballTeam(FootballTeam footballTeam);
    //private Storage storage;

   // @Autowired
    //public PlayerRepository(Storage storage){this.storage=storage;}

    //public Optional<Player> find(String surname){return storage.findPlayer(surname);}

    //public List<Player> findAll(){return storage.findAllPlayers();}

    //public void add(Player entity) {storage.addPlayer(entity);}

    //public void delete(Player entity) {storage.deletePlayer(entity.getSurname());}
}
