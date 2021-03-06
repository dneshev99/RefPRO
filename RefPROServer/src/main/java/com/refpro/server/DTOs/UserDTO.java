package com.refpro.server.DTOs;


import com.refpro.server.enums.DeviceType;

import java.util.HashMap;
import java.util.Map;

public class UserDTO {
    private String username;
    private String password;

    Map<DeviceType,String> tokens = new HashMap<>();

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO(String username, Map<DeviceType,String> tokens) {
        this.username = username;
        this.tokens=tokens;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Map<DeviceType, String> getTokens() {
        return tokens;
    }
}
