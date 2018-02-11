package com.refpro.server.DTOs;


import com.refpro.server.models.Team;

public class TeamDto {

    private String name;
    private String abbreaviature;
    private String country;


    private PlayerDto coach;

    public TeamDto(Team team) {
        this.name=team.getName();
        this.abbreaviature=team.getAbbreaviature();
        this.country=team.getCountry();
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

    public PlayerDto getCoach() {
        return coach;
    }

    public void setCoach(PlayerDto coach) {
        this.coach = coach;
    }
}
