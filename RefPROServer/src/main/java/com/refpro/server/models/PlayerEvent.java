package com.refpro.server.models;


import com.refpro.server.enums.MatchEventTypes;

public class PlayerEvent extends MatchEvent {
    private Player player;

    public PlayerEvent(String time, MatchEventTypes eventType, Team team, String message, Player player) {
        super(time, eventType, team, message);
        this.player = player;
    }

    public PlayerEvent() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
