package com.refpro.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refpro.server.DTOs.UserDTO;
import com.refpro.server.models.User;
import com.refpro.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/MobileLogin",method = RequestMethod.POST)
    public ResponseEntity<String> mobileLoginAttempt(UserDTO userDTO) {
        List<User> users = userRepository.findAll();
        
        Optional<User> userOptional = users.stream().filter(user -> user.getUsername().equals(userDTO.getUsername())
                            && user.getPassword().equals(userDTO.getPassword())).findAny();

        if (userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/Get",method = RequestMethod.GET)
    public ResponseEntity<String> getUsers() {
        ObjectMapper mapper = new ObjectMapper();
        String response = null;
        try {
            response = mapper.writeValueAsString(userRepository.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
