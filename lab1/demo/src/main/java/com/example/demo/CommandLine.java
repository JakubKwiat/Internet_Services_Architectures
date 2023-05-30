package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class CommandLine implements CommandLineRunner {
    private PlayerService playerService;
    private FootballTeamService footballTeamService;

    @Autowired
    public CommandLine(PlayerService playerService,FootballTeamService footballTeamService) {
        this.footballTeamService=footballTeamService;
        this.playerService=playerService;
    }

    private ConfigurableApplicationContext context;
    @Override
    public void run(String... args) throws Exception {
        //System.out.println("xxx");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1-Wyświetl");
            System.out.println("2-Dodaj");
            System.out.println("3-Usuń");
            System.out.println("4-Wyświetl kategorie");
            System.out.println("5-Wyjście");
            int choose = scanner.nextInt();
            if (choose == 1) {
                System.out.println("Wybierz kategorie");
                System.out.println("1-Piłkarze");
                System.out.println("2-Kluby");
                int chooseCategory=scanner.nextInt();
                if(chooseCategory==1){
                    playerService.findAll().forEach(System.out::println);
                }
                else if (chooseCategory == 2) {
                    footballTeamService.findAll().forEach(System.out::println);
                }
                else{
                    System.out.println("Wybrales zla liczbe!!!");
                }
            } else if (choose == 2) {
                System.out.println("Wybierz kategorie");
                System.out.println("1-Piłkarz");
                System.out.println("2-Klub");
                int chooseCategory = scanner.nextInt();
                if (chooseCategory == 1) {
                    System.out.println("Podaj nazwisko piłkarza");
                    String surname = scanner.next();

                    Optional<Player> player = playerService.find(surname);
                    if (!player.isEmpty()) {
                        System.out.println("Już istnieje taki zawodnik");
                    } else {
                        System.out.println("Podaj ocene pilkarza");
                        int rate = scanner.nextInt();
                        System.out.println("Wybierz klub dla pilkarza");
                        footballTeamService.findAll().forEach(System.out::println);
                        String teamName = scanner.next();

                        Optional<FootballTeam> footballTeam = footballTeamService.find(teamName);
                        String finalsurname = surname;
                        footballTeam.ifPresentOrElse(
                                value -> playerService.add(Player.builder().surname(finalsurname).rate(rate).footballTeam(value).build()),
                                () -> System.out.println("Nie mozna dodac tego pilkarza")
                        );
                    }
                }
                else if(chooseCategory==2){
                    System.out.println("Podaj nazwe klubu");
                    String name=scanner.next();

                    System.out.println("Podaj ocene ataku tego klubu");
                    int attackrate=scanner.nextInt();

                    System.out.println("Podaj ocene obrony tego klubu");
                    int defencerate=scanner.nextInt();

                    FootballTeam footballTeam= FootballTeam.builder()
                            .name(name)
                            .attack(attackrate)
                            .defence(defencerate)
                            .build();
                    footballTeamService.add(footballTeam);
                }
                else{
                    System.out.println("Wybrales zla liczbe!!!");
                }
            }
            else if(choose==3){
                System.out.println("Wybierz kategorie");
                System.out.println("1-Piłkarz");
                System.out.println("2-Klub");
                int chooseCategory = scanner.nextInt();
                if (chooseCategory == 1) {
                    playerService.findAll().forEach(System.out::println);
                    System.out.println("Podaj nazwisko piłkarza, ktorego chcesz usunac");
                    String surname = "";
                    while(surname.isEmpty()){
                        surname=scanner.next();
                    }
                    Optional<Player> player = playerService.find(surname);
                    if (player.isEmpty()) {
                        System.out.println("Nie istnieje taki zawodnik");
                    } else {
                        playerService.delete(surname);
                    }
                }
                else if(chooseCategory==2){
                    footballTeamService.findAll().forEach(System.out::println);
                    System.out.println("Podaj nazwe klubu, ktory chcesz usunac");
                    String name="";
                    while(name.isEmpty()){
                        name=scanner.next();
                    }
                    Optional<FootballTeam> footballTeam = footballTeamService.find(name);
                    if(footballTeam.isEmpty()){
                        System.out.println("Nie istnieje taki klub");
                    }
                    else{
                        footballTeamService.delete(name);
                    }
                }
                else{
                    System.out.println("Wybrales zla liczbe!!!");
                }
            }
            else if (choose==4){
                System.out.println("Kategorie:");
                System.out.println("Piłkarze");
                System.out.println("Kluby");
                System.out.println(" ");
            }
            else if (choose==5){
                System.exit(0);
                break;
            }
        }
    }
}

