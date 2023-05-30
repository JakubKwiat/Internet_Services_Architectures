package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class PlayerService {
    private PlayerRepository repository;

    @Autowired
    public void PlayerService(PlayerRepository repository){this.repository=repository;}

    public Optional<Player> find(String surname){return repository.find(surname);}

    public List<Player> findAll(){return  repository.findAll();}

    public void add(Player player){repository.add(player);}

    public void delete(String surname){repository.delete(repository.find(surname).orElseThrow());}

}
