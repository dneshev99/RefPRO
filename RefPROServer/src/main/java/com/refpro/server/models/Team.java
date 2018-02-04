package com.refpro.server.models;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class Team {
    @Id
    String id;

    private boolean isHome;
    private String name;
    private String abbreaviature;

    private int goals;
    private int yellowCards;
    private int redCards;

    public String getId() {
        return id;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreaviature() {
        return abbreaviature;
    }

    public void setAbbreaviature(String abbreaviature) {
        this.abbreaviature = abbreaviature;
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(ArrayList<Player> substitutes) {
        this.substitutes = substitutes;
    }

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> substitutes = new ArrayList<>();

    public Team(boolean isHome,
                String name,
                String abbreviate,
                int goals,
                int yellowCards,
                int redCards,
                ArrayList<Player> players,
                ArrayList<Player> substitutes) {
        this.isHome = isHome;
        this.name = name;
        this.abbreaviature = abbreviate;
        this.goals = goals;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.players = players;
        this.substitutes = substitutes;
    }

    public Team() {
    }


}
