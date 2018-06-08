package com.elsys.refpro.refprowatch.models;


import com.elsys.refpro.refprowatch.http.dto.PlayerDTO;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private boolean isHome;
    private String name;
    private String abbreaviature;

    private int goals;
    private int yellowCards;
    private int redCards;

    private List<Player> players = new ArrayList<>();
    private List<Player> substitutes = new ArrayList<>();

    public Team(boolean isHome, String name, String abbreaviature , ArrayList<Player> players, ArrayList<Player> substitutes) {

        this.isHome = isHome;
        this.name = name;
        this.abbreaviature = abbreaviature;
        this.players = players;
        this.substitutes = substitutes;
    }

    public Team(boolean isHome, String name, String abbreaviature) {

        this.isHome = isHome;
        this.name = name;
        this.abbreaviature = abbreaviature;
    }

    public boolean isHome() {
        return isHome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreaviature(String abbreaviature) {
        this.abbreaviature = abbreaviature;
    }

    public String getAbbreaviature() {
        return abbreaviature;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void setPlayersFromDto(List<PlayerDTO> dtos) {
       for(PlayerDTO each : dtos){
           this.players.add(new Player(each));
       }
    }

    public List<Player> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<Player> substitutes) {
        this.substitutes = substitutes;
    }

    public void setSubstitutesFromDto(List<PlayerDTO> dtos) {
        for(PlayerDTO each : dtos){
            this.substitutes.add(new Player(each));
        }
    }

    public void addGoal() {

        goals++;
    }

}
