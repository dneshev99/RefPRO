package com.refpro.server.models;

import org.springframework.data.annotation.Id;

public class MatchEvent {
    @Id
    String ID;

    private String team;
    private String message;
    private String time;

    public MatchEvent(String team, String message, String time) {
        this.team = team;
        this.message = message;
        this.time = time;
    }

    public MatchEvent() {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MatchEvent{" +
                "ID='" + ID + '\'' +
                ", team='" + team + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
