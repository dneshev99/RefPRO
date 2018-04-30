package com.refpro.server.DBhandlers;


import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.refpro.server.DTOs.PlayerDTO;
import com.refpro.server.exception.PlayerNotFoundExeption;
import com.refpro.server.models.Player;
import com.refpro.server.models.Team;
import com.refpro.server.repositories.PlayerRepository;
import com.refpro.server.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private MongoTemplate mongoTemplate;

    GridFS gfsPhoto = new GridFS(mongoTemplate.getDb(), "photos");

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

    public void setPictureForPlayer(PlayerDTO playerDTO, MultipartFile file) throws IOException, PlayerNotFoundExeption {

        Player player = playerRepository.findPlayerByShirtNumberAndShirtName(playerDTO.getShirtNumber(),playerDTO.getShirtName());

        if (player == null) {
            throw new PlayerNotFoundExeption("Player not found.");
        }

        GridFSInputFile gfsFile = gfsPhoto.createFile(file.getBytes());
        gfsFile.setFilename(player.getTeam() + "_" + player.getShirtName());
        gfsFile.setChunkSize(4096*1024);
        gfsFile.save();

        player.setPicture_id(gfsFile.getId().toString());
        playerRepository.save(player);

    }

}
