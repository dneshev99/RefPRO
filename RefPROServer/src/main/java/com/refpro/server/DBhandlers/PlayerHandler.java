package com.refpro.server.DBhandlers;


import com.refpro.server.DTOs.PlayerDTO;
import com.refpro.server.models.Player;
import com.refpro.server.models.Team;
import com.refpro.server.repositories.PlayerRepository;
import com.refpro.server.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerHandler {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public String createPlayer(List<PlayerDTO> players ){
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
        }
        return "kappa";
    }

    public List<PlayerDTO> getPlayersByTeam(String name) {
        List<PlayerDTO> result = new ArrayList<>();

        Team team = teamRepository.findByName(name);
        List<Player> players = playerRepository.findAllByTeam(team);

        for (Player player : players) {
            PlayerDTO newPlayer = new PlayerDTO(player);

            result.add(newPlayer);
        }

        return result;
    }
}
