package com.elsys.refpro.refpromobile.dto;

import java.util.Objects;

public class PlayerDTO {

    private int shirtNumber;
    private String shirtName;
    private String firstName;
    private String lastName;
    private String birthday;
    private String pictureId;
    private String playerId;
    public PlayerDTO(){}

    public PlayerDTO(int shirtNumber, String shirtName) {
        this.shirtNumber = shirtNumber;
        this.shirtName = shirtName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
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

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDTO playerDTO = (PlayerDTO) o;
        return Objects.equals(playerId, playerDTO.playerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(playerId);
    }
}