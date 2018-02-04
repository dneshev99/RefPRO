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

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public HttpEntity<Void> saveToken(@RequestBody String fcmTokenForUser,@RequestHeader("DeviceType") String deviceTypeHeader) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        DeviceType deviceType = DeviceType.valueOf(deviceTypeHeader);
        userHandler.updateUserFcmToken(userName,fcmTokenForUser,deviceType);

        System.out.println("DA" + " " + deviceTypeHeader + ":" + fcmTokenForUser);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public HttpEntity<Void> saveToken(@RequestBody UserDTO userDTO) throws Exception {
        userHandler.registerNewUserAccount(userDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getFCMtokentforUser",method = RequestMethod.GET)
    public HttpEntity<String> getTokenForUser(@RequestHeader("DeviceType") String deviceTypeHeader) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        DeviceType deviceType = DeviceType.valueOf(deviceTypeHeader);

        String token = userHandler.getFCMtokenForUser(userName,deviceType);

        return new ResponseEntity<>(token,HttpStatus.OK);
    }

}
