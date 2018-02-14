package com.refpro.server.DTOs;

import com.refpro.server.models.Player;
import com.refpro.server.models.Team;

import java.util.List;

public class MatchInfoDTO {
    private String competition;
    private String venue;
    private String date;
    private String time;

    private Team home;
    private Team away;

    private String homeAbbr;
    private String awayAbbr;

    private List<Player> homePlayers;
    private List<Player> awayPlayers;
    private int length;

    private List<Player> subsHome;
    private List<Player> subsAway;

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public String getHomeAbbr() {
        return homeAbbr;
    }

    public void setHomeAbbr(String homeAbbr) {
        this.homeAbbr = homeAbbr;
    }

    public String getAwayAbbr() {
        return awayAbbr;
    }

    public void setAwayAbbr(String awayAbbr) {
        this.awayAbbr = awayAbbr;
    }

    public List<Player> getHomePlayers() {
        return homePlayers;
    }

    public void setHomePlayers(List<Player> homePlayers) {
        this.homePlayers = homePlayers;
    }

    public List<Player> getAwayPlayers() {
        return awayPlayers;
    }

    public void setAwayPlayers(List<Player> awayPlayers) {
        this.awayPlayers = awayPlayers;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Player> getSubsHome() {
        return subsHome;
    }

    public void setSubsHome(List<Player> subsHome) {
        this.subsHome = subsHome;
    }

    public List<Player> getSubsAway() {
        return subsAway;
    }

    public void setSubsAway(List<Player> subsAway) {
        this.subsAway = subsAway;
    }
}