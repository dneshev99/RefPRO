package com.refpro.server.dtos;

public class PlayerEventDTO extends MatchEventDTO {
    private String message;
    private PlayerDTO player;

    public PlayerEventDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }
}
