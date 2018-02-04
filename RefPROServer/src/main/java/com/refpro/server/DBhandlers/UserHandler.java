package com.refpro.server.DBhandlers;

import com.refpro.server.DTOs.UserDTO;
import com.refpro.server.enums.DeviceType;
import com.refpro.server.models.User;
import com.refpro.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserHandler implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User result = userRepository.getUserByUsername(username);

        if (result != null) {
            return result;
        }
        return null;
    }

    public void updateUserFcmToken(String username, String fcmTokenForUser, DeviceType deviceType){
        User user = (User) this.loadUserByUsername(username);
        switch(deviceType){
            case WEAR:
                user.setWatchFCMtoken(fcmTokenForUser);
                break;
            case MOBILE:
                user.setMobileFCMtoken(fcmTokenForUser);
                break;
        }
        userRepository.save(user);
    }
    public UserDetails registerNewUserAccount(UserDTO userDto) throws Exception {

        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),null,null);

        return userRepository.save(user);
    }

    public String getFCMtokenForUser(String username,DeviceType deviceType) throws Exception {
        User user = userRepository.getUserByUsername(username);
        String token = null;

        switch(deviceType){
            case WEAR:
                token = user.getWatchFCMtoken();
                break;
            case MOBILE:
                token = user.getMobileFCMtoken();
                break;
        }

        return token;
    }
}
