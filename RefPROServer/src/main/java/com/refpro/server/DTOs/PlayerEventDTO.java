package com.refpro.server.DTOs;

import com.refpro.server.enums.MatchEventTypes;
import com.refpro.server.models.Player;

public class PlayerEventDTO {
    private String time;
    private MatchEventTypes eventType;
    private String team;
    private String message;
    private PlayerDTO player;

    public PlayerEventDTO() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MatchEventTypes getEventType() {
        return eventType;
    }

    public void setEventType(MatchEventTypes eventType) {
        this.eventType = eventType;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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
