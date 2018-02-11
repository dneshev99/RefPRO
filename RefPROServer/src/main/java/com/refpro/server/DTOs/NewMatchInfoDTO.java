package com.refpro.server.DTOs;

public class NewMatchInfoDTO {

    private boolean isActive;

    private String competition;
    private String venue;
    private String date;
    private String time;
    private String home;
    private String away;
    private String homeAbbreviature;
    private String awayAbbreviature;

    private int playersNumber;
    private int substitutesNumber;
    private int halfLength;

    public NewMatchInfoDTO(boolean isActive, String competition, String venue, String date, String time, String home, String away, String homeAbbreviature, String awayAbbreviature, int playersNumber, int substitutesNumber, int halfLength) {
        this.isActive = isActive;
        this.competition = competition;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.home = home;
        this.away = away;
        this.homeAbbreviature = homeAbbreviature;
        this.awayAbbreviature = awayAbbreviature;
        this.playersNumber = playersNumber;
        this.substitutesNumber = substitutesNumber;
        this.halfLength = halfLength;
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

    public String getHomeAbbreviature() {
        return homeAbbreviature;
    }

    public String getAwayAbbreviature() {
        return awayAbbreviature;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public int getSubstitutesNumber() {
        return substitutesNumber;
    }

    public int getHalfLength() {
        return halfLength;
    }

    @Override
    public String toString() {
        return "NewMatchInfoDTO{" +
                "isActive=" + isActive +
                ", competition='" + competition + '\'' +
                ", venue='" + venue + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", home='" + home + '\'' +
                ", away='" + away + '\'' +
                ", homeAbbreviature='" + homeAbbreviature + '\'' +
                ", awayAbbreviature='" + awayAbbreviature + '\'' +
                ", playersNumber=" + playersNumber +
                ", substitutesNumber=" + substitutesNumber +
                ", halfLength=" + halfLength +
                '}';
    }
}
