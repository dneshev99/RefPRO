package com.elsys.refpro.refpromobile.dto;

public class PlayerDTO {

    private int shirtNumber;
    private String shirtName;

    public PlayerDTO(){}

    public PlayerDTO(int shirtNumber, String shirtName) {
        this.shirtNumber = shirtNumber;
        this.shirtName = shirtName;
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