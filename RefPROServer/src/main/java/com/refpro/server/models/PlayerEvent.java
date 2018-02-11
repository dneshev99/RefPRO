package com.refpro.server.models;

import org.springframework.data.annotation.Id;

public class PlayerEvent extends MatchEvent {
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
