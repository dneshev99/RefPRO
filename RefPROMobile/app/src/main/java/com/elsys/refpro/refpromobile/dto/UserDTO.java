package com.elsys.refpro.refpromobile.dto;

import com.elsys.refpro.refpromobile.enums.DeviceType;

import java.util.HashMap;
import java.util.Map;

public class UserDTO {
    private String username;
    private String password;

    Map<DeviceType, String> tokens = new HashMap<>();

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<DeviceType, String> getTokens() {
        return tokens;
    }

    public void setTokens(Map<DeviceType, String> tokens) {
        this.tokens = tokens;
    }
}

