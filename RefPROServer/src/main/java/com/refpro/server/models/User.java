package com.refpro.server.models;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User implements UserDetails{
    @Id
    String ID;

    private String username;
    private String password;
    private String watchFCMtoken;
    private String mobileFCMtoken;

    public User(String username, String password, String watchFCMtoken, String mobileFCMtoken) {
        this.username = username;
        this.password = password;
        this.watchFCMtoken = watchFCMtoken;
        this.mobileFCMtoken = mobileFCMtoken;
    }

    public User() {
    }

    public String getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWatchFCMtoken() {
        return watchFCMtoken;
    }

    public void setWatchFCMtoken(String watchFCMtoken) {
        this.watchFCMtoken = watchFCMtoken;
    }

    public String getMobileFCMtoken() {
        return mobileFCMtoken;
    }

    public void setMobileFCMtoken(String mobileFCMtoken) {
        this.mobileFCMtoken = mobileFCMtoken;
    }
}
