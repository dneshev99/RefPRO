package com.refpro.server.controllers;


import com.refpro.server.DBhandlers.TeamHandler;
import com.refpro.server.DTOs.NewMatchInfoDTO;
import com.refpro.server.DTOs.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.logging.Level;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamHandler teamHandler;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<String> createNewTeam(@RequestBody TeamDto teamDto) {

       // log.log(Level.INFO,"Create matcg Info: "+newMatchInfoDTO);
        String id=teamHandler.createNewTeam(teamDto);
        return  new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseEntity<List<TeamDto>> getAllTeams() {

        // log.log(Level.INFO,"Create matcg Info: "+newMatchInfoDTO);
        List<TeamDto> result = teamHandler.getAllTeams();
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

}
