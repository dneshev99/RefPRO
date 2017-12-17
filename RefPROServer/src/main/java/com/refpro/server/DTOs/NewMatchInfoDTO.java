package com.refpro.server.DTOs;

import java.util.ArrayList;

public class NewMatchInfoDTO {

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

    public NewMatchInfoDTO(boolean isActive, String competition, String venue, String date, String time, String home, String away, String homeabbr, String awayabbr, int players, int subs, int length) {
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
    }

    public NewMatchInfoDTO() {
    }

    public boolean isActive() {
        return isActive;
    }

    public String getCompetition() {
        return competition;
    }

    public String getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getHome() {
        return home;
    }

    public String getAway() {
        return away;
    }

    public String getHomeabbr() {
        return homeabbr;
    }

    public String getAwayabbr() {
        return awayabbr;
    }

    public int getPlayers() {
        return players;
    }

    public int getSubs() {
        return subs;
    }

    public int getLength() {
        return length;
    }
}
