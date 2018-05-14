package com.refpro.server.DTOs;


import com.refpro.server.models.Team;

public class TeamDto {

    private String name;
    private String abbreaviature;
    private String country;
    private String id;

    private PlayerDTO coach;

    public TeamDto(Team team) {
        this.name=team.getName();
        this.abbreaviature=team.getAbbreaviature();
        this.country=team.getCountry();
        this.id = team.getId();
    }

    public TeamDto(){}

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

    public PlayerDTO getCoach() {
        return coach;
    }

    public void setCoach(PlayerDTO coach) {
        this.coach = coach;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
