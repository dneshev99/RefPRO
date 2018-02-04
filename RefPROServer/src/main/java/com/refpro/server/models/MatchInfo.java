package com.refpro.server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;

public class MatchInfo {
    @Id
    @Indexed
    String ID;

    @Indexed
    private boolean isActive;

    private String competition;
    private String venue;
    private String date;
    private String time;
    private String home;
    private String away;
    private String homeAbbr;
    private String awayAbbr;

    private int players;
    private int subs;
    private int length;


    private ArrayList<String> playersHome;
    private ArrayList<String> playersAway;
    private ArrayList<String> subsHome;
    private ArrayList<String> subsAway;

    private String log;

    public MatchInfo(boolean isActive, String competition, String venue, String date, String time, String home, String away, String homeAbbr, String awayAbbr, int players, int subs, int length, ArrayList<String> playersHome, ArrayList<String> playersAway, ArrayList<String> subsHome, ArrayList<String> subsAway, String log) {
        this.isActive = isActive;
        this.competition = competition;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.home = home;
        this.away = away;
        this.homeAbbr = homeAbbr;
        this.awayAbbr = awayAbbr;
        this.players = players;
        this.subs = subs;
        this.length = length;
        this.playersHome = playersHome;
        this.playersAway = playersAway;
        this.subsHome = subsHome;
        this.subsAway = subsAway;
        this.log = log;
    }

    public MatchInfo() {
    }

    public String getID() {
        return ID;
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

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
