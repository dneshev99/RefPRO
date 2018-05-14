package com.elsys.refpro.refpromobile.models;

public class Item {

    private String home, away, date, time, venue;
    private String mongoId;
    private int dbId;

    public Item(String home, String away, String date, String time, String venue, String mongoId) {

        this.home = home;
        this.away = away;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.mongoId = mongoId;
    }

    public Item() {
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }
}
