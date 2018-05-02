package com.elsys.refpro.refpromobile.dto;

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

        if (shirtNumber != playerDTO.shirtNumber) return false;
        if (!shirtName.equals(playerDTO.shirtName)) return false;
        if (!firstName.equals(playerDTO.firstName)) return false;
        if (!lastName.equals(playerDTO.lastName)) return false;
        return birthday != null ? birthday.equals(playerDTO.birthday) : playerDTO.birthday == null;
    }

    @Override
    public int hashCode() {
        int result = shirtNumber;
        result = 31 * result + shirtName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}