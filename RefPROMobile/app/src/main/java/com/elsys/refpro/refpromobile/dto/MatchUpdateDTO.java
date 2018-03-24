package com.elsys.refpro.refpromobile.dto;

import java.util.ArrayList;
import java.util.List;

public class MatchUpdateDTO {

    private String matchId;
    private List<PlayerDTO> playersHome;
    private List<PlayerDTO> playersAway;
    private List<PlayerDTO> subsHome;
    private List<PlayerDTO> subsAway;

    public MatchUpdateDTO(String matchId, List<PlayerDTO> playersHome, List<PlayerDTO> playersAway, List<PlayerDTO> subsHome, List<PlayerDTO> subsAway) {
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

    public List<PlayerDTO> getPlayersHome() {
        return playersHome;
    }

    public void setPlayersHome(List<PlayerDTO> playersHome) {
        this.playersHome = playersHome;
    }

    public List<PlayerDTO> getPlayersAway() {
        return playersAway;
    }

    public void setPlayersAway(List<PlayerDTO> playersAway) {
        this.playersAway = playersAway;
    }

    public List<PlayerDTO> getSubsHome() {
        return subsHome;
    }

    public void setSubsHome(List<PlayerDTO> subsHome) {
        this.subsHome = subsHome;
    }

    public List<PlayerDTO> getSubsAway() {
        return subsAway;
    }

    public void setSubsAway(List<PlayerDTO> subsAway) {
        this.subsAway = subsAway;
    }
}