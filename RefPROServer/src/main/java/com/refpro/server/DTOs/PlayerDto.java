package com.refpro.server.DTOs;

import com.refpro.server.models.Player;

public class PlayerDTO {

    private String team;

    private String firstName;
    private String lastName;
    private String birthday;

    private int shirtNumber;
    private String shirtName;

    public PlayerDTO(){}

    public PlayerDTO(Player player) {
        this.team = player.getTeam().getName();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.birthday = player.getBirthday();
        this.shirtNumber = player.getShirtNumber();
        this.shirtName = player.getShirtName();
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getShirtName() {
        return shirtName;
    }

    public void setShirtName(String shirtName) {
        this.shirtName = shirtName;
    }
}