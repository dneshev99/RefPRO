package com.refpro.server.controllers;

import com.refpro.server.DBhandlers.UserHandler;
import com.refpro.server.DTOs.UserDTO;
import com.refpro.server.enums.DeviceType;
import com.refpro.server.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserHandler userHandler;

    @RequestMapping(value = "/updateUserToken",method = RequestMethod.POST)
    public HttpEntity<Void> saveToken(@RequestBody String fcmTokenForUser,@RequestHeader("DeviceType") String deviceTypeHeader) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        DeviceType deviceType = DeviceType.valueOf(deviceTypeHeader);
        userHandler.updateUserFcmToken(userName,fcmTokenForUser.replace("\"",""),deviceType);
        System.out.println("asd");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public HttpEntity<Void> register(@RequestBody UserDTO userDTO) throws Exception {
        userHandler.registerNewUserAccount(userDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getTokensForUser",method = RequestMethod.GET)
    public HttpEntity<UserDTO> getTokenForUser() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        UserDTO userDto = userHandler.getFCMtokenForUser(userName);
        return new ResponseEntity<UserDTO>(userDto,HttpStatus.OK);
    }

}
