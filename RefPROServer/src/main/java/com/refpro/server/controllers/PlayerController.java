package com.refpro.server.controllers;


import com.refpro.server.DBhandlers.PlayerHandler;
import com.refpro.server.DTOs.PlayerDto;
import com.refpro.server.DTOs.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerHandler playerHandler;
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<String> createNewTeam(@RequestBody List<PlayerDto> playersToCreate) {

        // log.log(Level.INFO,"Create matcg Info: "+newMatchInfoDTO);
        String id=playerHandler.createPlayer(playersToCreate);
        return  new ResponseEntity<>( HttpStatus.OK);
    }


}
