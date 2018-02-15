package com.refpro.server.controllers;


import com.refpro.server.DBhandlers.PlayerHandler;
import com.refpro.server.DTOs.ErrorDto;
import com.refpro.server.DTOs.PlayerDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;



@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerHandler playerHandler;

    private static final Logger log = Logger.getLogger(PlayerController.class.getName());

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public static ResponseEntity handleTypeMismatchException() {
        return new ResponseEntity<>(new ErrorDto("Invalid input"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public static ResponseEntity handleNotReadableException() {
        return new ResponseEntity<>(new ErrorDto("Invalid input"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity createNewPlayers(@RequestBody List<PlayerDTO> playersToCreate) {

        log.log(Level.INFO,"Create match Info: "+playersToCreate);
        List<String> ids=playerHandler.createPlayer(playersToCreate);
        return  new ResponseEntity<>( ids,HttpStatus.OK);
    }

    @RequestMapping(value = "/getPlayersByTeam/{name}",method = RequestMethod.GET)
    public ResponseEntity getPlayersByTeam(@PathVariable("name") String name) {
        List<PlayerDTO> result = playerHandler. getPlayersByTeam(name);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
