package com.refpro.server.DBhandlers;


import com.refpro.server.DTOs.PlayerDTO;
import com.refpro.server.models.Player;
import com.refpro.server.models.Team;
import com.refpro.server.repositories.PlayerRepository;
import com.refpro.server.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class PlayerHandler {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    private static final Logger log = Logger.getLogger(PlayerHandler.class.getName());

    public List<String> createPlayer(List<PlayerDTO> players ){
        List<String> createdIds = new LinkedList<>();
        if(players==null)
            return  null;

        for(PlayerDTO playerDTO :players){
            Player newPlayer = new Player();
            newPlayer.setShirtName(playerDTO.getShirtName());
            newPlayer.setFirstName(playerDTO.getFirstName());
            newPlayer.setLastName(playerDTO.getLastName());
            newPlayer.setBirthday(playerDTO.getBirthday());
            newPlayer.setShirtNumber(playerDTO.getShirtNumber());

            Team team =teamRepository.findByName(playerDTO.getTeam());
            newPlayer.setTeam(team);
            playerRepository.save(newPlayer);
            createdIds.add(newPlayer.getId());
        }
        return createdIds;
    }

    public List<PlayerDTO> getPlayersByTeam(String name) {
        List<PlayerDTO> result = new ArrayList<>();

        Team team = teamRepository.findByName(name);
        List<Player> players = playerRepository.findAllByTeam(team);
        log.info(players.size()+"");
        for (Player player : players) {
            PlayerDTO newPlayer = new PlayerDTO(player);
            result.add(newPlayer);
        }

        return result;
    }

    List<PlayerDTO> convertEntitiesToDto(List<Player> players){
        List<PlayerDTO> result = new ArrayList<>();
        for (Player player : players) {
            PlayerDTO newPlayer = new PlayerDTO(player);
            result.add(newPlayer);
        }
        return result;
    }
}
