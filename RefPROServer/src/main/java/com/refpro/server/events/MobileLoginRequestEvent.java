package com.refpro.server.events;

public class MobileLoginRequestEvent {
    private String username;
    private String password;

    public MobileLoginRequestEvent(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MobileLoginRequestEvent() {
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
}
