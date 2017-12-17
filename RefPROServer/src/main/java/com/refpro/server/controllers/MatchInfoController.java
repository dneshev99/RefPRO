package com.refpro.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refpro.server.DBhandlers.MatchInfoHandler;
import com.refpro.server.DTOs.MatchUpdateDTO;
import com.refpro.server.DTOs.NewMatchInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/MatchInfo")
@Controller
public class MatchInfoController {
    @Autowired
    MatchInfoHandler matchInfoHandler;
    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/Create",method = RequestMethod.POST)
    public ResponseEntity<String> createMatchInfo(@RequestBody NewMatchInfoDTO newMatchInfoDTO) {
        String ID = matchInfoHandler.addMatchInfo(newMatchInfoDTO);
        System.out.println("asd "+ID);
        return  new ResponseEntity<>(ID,HttpStatus.OK);
    }

    @RequestMapping(value = "/Get",method = RequestMethod.GET)
    public HttpEntity<String> getMatchInfo() {
        String result = null;

        try {
            result = mapper.writeValueAsString(matchInfoHandler.getAllMatchInfo());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/Update",method = RequestMethod.POST)
    public HttpEntity<String> updateMatchInfo(@RequestBody MatchUpdateDTO matchUpdateDTO) {
        System.out.println(matchUpdateDTO.toString());
        matchInfoHandler.updateMatchInfo(matchUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
