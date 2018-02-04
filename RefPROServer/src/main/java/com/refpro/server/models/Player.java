package com.refpro.server.models;

import org.springframework.data.annotation.Id;

public class Player {
    @Id
    String id;

    private int number;
    private String name;

    private int yellowCards;
    private int redCards;
    private int goals;
    private int minutesPlayed;

    public Player(int number, String name) {

        this.number = number;
        this.name = name;
        this.yellowCards = 0;
        this.redCards = 0;
        this.goals = 0;
        this.minutesPlayed = 0;
    }

    public Player() {
    }

    public String getId() {
        return id;
    }

    public String getNumberAndName() {

        return getNumber() + "." + getName();
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
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

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }


}
