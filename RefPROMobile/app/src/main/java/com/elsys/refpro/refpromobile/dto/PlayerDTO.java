package com.elsys.refpro.refpromobile.dto;

public class PlayerDTO {

    private int shirtNumber;
    private String shirtName;
    private String firstName;
    private String lastName;
    private String birthday;

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
}