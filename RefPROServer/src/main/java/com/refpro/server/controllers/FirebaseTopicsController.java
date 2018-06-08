package com.refpro.server.controllers;

import com.refpro.server.dbhandlers.FirebaseTopicsHandler;
import com.refpro.server.dbhandlers.UserHandler;
import com.refpro.server.dtos.FirebaseTopicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/firebasetopics")
public class FirebaseTopicsController {
    @Autowired
    private UserHandler userHandler;

    @Autowired
    private FirebaseTopicsHandler firebaseTopicsHandler;

    @RequestMapping(value = "/createNewFirebaseTopic",method = RequestMethod.POST)
    public HttpEntity saveToken(@RequestBody List<FirebaseTopicDTO> firebaseTopicDTOList) throws Exception {
        firebaseTopicsHandler.createTopics(firebaseTopicDTOList);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
