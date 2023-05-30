package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class PlayerRepository {
    private Storage storage;

    @Autowired
    public PlayerRepository(Storage storage){this.storage=storage;}

    public Optional<Player> find(String surname){return storage.findPlayer(surname);}

    public List<Player> findAll(){return storage.findAllPlayers();}

    public void add(Player entity) {storage.addPlayer(entity);}

    public void delete(Player entity) {storage.deletePlayer(entity.getSurname());}
}
