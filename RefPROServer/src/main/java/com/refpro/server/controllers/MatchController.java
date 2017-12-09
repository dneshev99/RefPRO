package com.refpro.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refpro.server.DTOs.MatchEventDTO;
import com.refpro.server.repositories.MatchEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/match")
public class MatchController {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MatchEventRepository matchEventRepository;


    @RequestMapping(value = "/newEvent",method = RequestMethod.POST,consumes = "application/json")
    public HttpEntity<String> saveMatchResult(@RequestBody MatchEventDTO matchEventDTO) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public HttpEntity<String> getMachEvents() {
        String response = null;
        try {
            response = mapper.writeValueAsString(matchEventRepository.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
