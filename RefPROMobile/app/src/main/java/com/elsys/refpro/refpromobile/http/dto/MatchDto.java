package com.elsys.refpro.refpromobile.http.dto;

/**
 * Created by Mitko on 10.12.2017 г..
 */

public class MatchDto {

    private boolean isActive;

    private String competition;
    private String venue;
    private String date;
    private String time;
    private String home;
    private String away;
    private String homeabbr;
    private String awayabbr;

    private int players;
    private int subs;
    private int length;

    private String playersHome;
    private String playersAway;
    private String subsHome;
    private String subsAway;

    private String log;

    public MatchDto(boolean isActive, String competition, String venue, String date, String time, String home, String away, String homeabbr, String awayabbr, int players, int subs, int length, String playersHome, String playersAway, String subsHome, String subsAway, String log) {
        this.isActive = isActive;
        this.competition = competition;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.home = home;
        this.away = away;
        this.homeabbr = homeabbr;
        this.awayabbr = awayabbr;
        this.players = players;
        this.subs = subs;
        this.length = length;
        this.playersHome = playersHome;
        this.playersAway = playersAway;
        this.subsHome = subsHome;
        this.subsAway = subsAway;
        this.log = log;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

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

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getHomeabbr() {
        return homeabbr;
    }

    public void setHomeabbr(String homeabbr) {
        this.homeabbr = homeabbr;
    }

    public String getAwayabbr() {
        return awayabbr;
    }

    public void setAwayabbr(String awayabbr) {
        this.awayabbr = awayabbr;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getSubs() {
        return subs;
    }

    public void setSubs(int subs) {
        this.subs = subs;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPlayersHome() {
        return playersHome;
    }

    public void setPlayersHome(String playersHome) {
        this.playersHome = playersHome;
    }

    public String getPlayersAway() {
        return playersAway;
    }

    public void setPlayersAway(String playersAway) {
        this.playersAway = playersAway;
    }

    public String getSubsHome() {
        return subsHome;
    }

    public void setSubsHome(String subsHome) {
        this.subsHome = subsHome;
    }

    public String getSubsAway() {
        return subsAway;
    }

    public void setSubsAway(String subsAway) {
        this.subsAway = subsAway;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
