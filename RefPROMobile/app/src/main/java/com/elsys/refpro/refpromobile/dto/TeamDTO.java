package com.elsys.refpro.refpromobile.dto;


public class TeamDTO {
    private String name;
    private String abbreaviature;
    private String country;

    public TeamDTO() {

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

    @Override
    public String toString() {
        return "TeamDTO{" +
                "name='" + name + '\'' +
                ", abbreaviature='" + abbreaviature + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
