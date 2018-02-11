package com.refpro.server.models;

import com.refpro.server.enums.MatchEventTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class MatchEvent {
    @Id
    String ID;

    private String time;
    private MatchEventTypes eventType;
    private Team team;
    private String message;



    public MatchEvent(String time, MatchEventTypes eventType, Team team, String message) {
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

    public void setID(String ID) {
        this.ID = ID;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
