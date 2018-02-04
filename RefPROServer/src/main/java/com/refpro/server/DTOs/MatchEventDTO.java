package com.refpro.server.DTOs;

public class MatchEventDTO {
    private String team;
    private String message;
    private String time;

    public MatchEventDTO(String team, String message, String time) {
        this.team = team;
        this.message = message;
        this.time = time;
    }

    public MatchEventDTO() {
    }

    public String getTeam() {
        return team;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "MatchEventDTO{" +
                "team='" + team + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
