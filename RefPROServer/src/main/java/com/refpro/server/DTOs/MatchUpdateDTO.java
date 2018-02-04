package com.refpro.server.DTOs;

import java.util.ArrayList;

public class MatchUpdateDTO {
    private String matchId;
    private ArrayList<String> playersHome;
    private ArrayList<String> playersAway;
    private ArrayList<String> subsHome;
    private ArrayList<String> subsAway;

    public MatchUpdateDTO(String matchId, ArrayList<String> playersHome, ArrayList<String> playersAway, ArrayList<String> subsHome, ArrayList<String> subsAway) {
        this.matchId = matchId;
        this.playersHome = playersHome;
        this.playersAway = playersAway;
        this.subsHome = subsHome;
        this.subsAway = subsAway;
    }

    public MatchUpdateDTO() {

    }


    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public ArrayList<String> getPlayersHome() {
        return playersHome;
    }

    public void setPlayersHome(ArrayList<String> playersHome) {
        this.playersHome = playersHome;
    }

    public ArrayList<String> getPlayersAway() {
        return playersAway;
    }

    public void setPlayersAway(ArrayList<String> playersAway) {
        this.playersAway = playersAway;
    }

    public ArrayList<String> getSubsHome() {
        return subsHome;
    }

    public void setSubsHome(ArrayList<String> subsHome) {
        this.subsHome = subsHome;
    }

    public ArrayList<String> getSubsAway() {
        return subsAway;
    }

    public void setSubsAway(ArrayList<String> subsAway) {
        this.subsAway = subsAway;
    }

    @Override
    public String toString() {
        return "MatchUpdateDTO{" +
                "MatchId='" + matchId + '\'' +
                ", playersHome=" + playersHome +
                ", playersAway=" + playersAway +
                ", subsHome=" + subsHome +
                ", subsAway=" + subsAway +
                '}';
    }
}
