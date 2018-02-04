package com.refpro.server.models;

import org.springframework.data.annotation.Id;

public class MatchEvent {
    @Id
    String ID;

    private String time;
    private String eventType;
    private String team;
    private String message;

    public MatchEvent(String time, String eventType, String team, String message) {
        this.time = time;
        this.eventType = eventType;
        this.team = team;
        this.message = message;
    }

    public MatchEvent() {
    }

    public String getID() {
        return ID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
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
}
