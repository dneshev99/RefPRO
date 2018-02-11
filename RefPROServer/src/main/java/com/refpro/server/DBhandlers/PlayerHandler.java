package com.refpro.server.DBhandlers;


import com.refpro.server.DTOs.PlayerDto;
import com.refpro.server.models.Player;
import com.refpro.server.models.Team;
import com.refpro.server.repositories.PlayerRepository;
import com.refpro.server.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerHandler {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public String createPlayer(List<PlayerDto> players ){
        if(players==null)
            return  null;

        for(PlayerDto playerDto:players){
            Player newPlayer = new Player();
            newPlayer.setShirtName(playerDto.getShirtName());
            //check
            Team team =teamRepository.findByName(playerDto.getTeam().getName());
            newPlayer.setTeam(team);
            playerRepository.save(newPlayer);
        }
        return "kappa";
    }
}
