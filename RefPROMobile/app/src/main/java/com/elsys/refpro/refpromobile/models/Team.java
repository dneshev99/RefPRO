package com.elsys.refpro.refpromobile.models;


public class Team {

    private String name;
    private String abbreaviature;
    private String country;

    private Player coach;

    public Team() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreaviature() {
        return abbreaviature;
    }

    public void setAbbreaviature(String abbreaviature) {
        this.abbreaviature = abbreaviature;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Player getCoach() {
        return coach;
    }

    public void setCoach(Player coach) {
        this.coach = coach;
    }
}