package com.example.demo.footballteam.repository;

import com.example.demo.footballteam.entity.FootballTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballTeamRepository extends JpaRepository<FootballTeam,String> {

    //public Storage storage;

    //@Autowired
    //public FootballTeamRepository(Storage storage){this.storage=storage;}

    //public Optional<FootballTeam> find(String name){return storage.findFootballTeam(name);}

    //public List<FootballTeam> findAll(){return storage.findAllFootballTeams();}

    //public void add(FootballTeam entity){storage.addFootballTeam(entity);}

    //public void delete(FootballTeam entity){storage.deleteFootballTeam(entity.getName());}
}
