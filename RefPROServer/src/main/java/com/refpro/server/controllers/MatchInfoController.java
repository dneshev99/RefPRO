package com.refpro.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refpro.server.DBhandlers.MatchInfoService;
import com.refpro.server.DBhandlers.UserHandler;
import com.refpro.server.DTOs.MatchUpdateDTO;
import com.refpro.server.DTOs.NewMatchInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequestMapping("/MatchInfo")
@Controller
public class MatchInfoController {
    @Autowired
    private MatchInfoService matchInfoHandler;
    private ObjectMapper mapper = new ObjectMapper();
    private UserHandler userHandler = new UserHandler();
    private static final Logger log= Logger.getLogger(MatchInfoController.class.getName());

    @RequestMapping(value = "/Create",method = RequestMethod.POST)
    public ResponseEntity<String> createMatchInfo(@RequestBody NewMatchInfoDTO newMatchInfoDTO) {
        String ID = matchInfoHandler.addMatchInfo(newMatchInfoDTO);
        log.log(Level.INFO,"Create matcg Info: "+newMatchInfoDTO);

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

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public HttpEntity<String> test() {
       matchInfoHandler.addEventToMatch();
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /*@RequestMapping(value = "/register",method = RequestMethod.POST)
    public HttpEntity<String> register(@RequestBody AccountDto accountDto) {
        try {
            userHandler.registerNewUserAccount(accountDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @RequestMapping(value = "/Update",method = RequestMethod.POST)
    public HttpEntity<String> updateMatchInfo(@RequestBody MatchUpdateDTO matchUpdateDTO) {
        /*try{

        }catch(AbstractRestException e){

        }catch(Exception e ){
            return new ResponseEntity<>(mapper.writeValueAsString(new ErrorDto(e.getMessage())),HttpStatus.INTERNAL_SERVER_ERROR));
        }*/
        System.out.println(matchUpdateDTO.toString());
        matchInfoHandler.updateMatchInfo(matchUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/Delete/{id}",method = RequestMethod.DELETE)
    public HttpEntity<String> deleteMatchInfo(@PathVariable("id") String id) {
        matchInfoHandler.deleteMatchInfo(id);
        log.log(Level.INFO,"Delete match: "+id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
