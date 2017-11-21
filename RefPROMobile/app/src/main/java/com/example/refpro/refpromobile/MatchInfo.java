package com.example.refpro.refpromobile;

import java.util.Date;

/**
 * Created by user on 19.11.2017 г..
 */

public class MatchInfo {

    private String competition, venue, home, away, homeabbr, awayabbr, time;
    private int players, subs, lenght;
    private String date;

    public MatchInfo(String competition, String venue, String home, String away, String homeabbr, String awayabbr, String time, int players, int subs, int lenght, String date) {

        this.competition = competition;
        this.venue = venue;
        this.home = home;
        this.away = away;
        this.homeabbr = homeabbr;
        this.awayabbr = awayabbr;
        this.time = time;
        this.players = players;
        this.subs = subs;
        this.lenght = lenght;
        this.date = date;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
