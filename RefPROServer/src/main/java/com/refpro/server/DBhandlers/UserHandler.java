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

import java.util.HashMap;
import java.util.Map;

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

    public UserDTO getFCMtokenForUser(String username) throws Exception {
        User user = userRepository.getUserByUsername(username);
        Map<DeviceType,String> tokens = new HashMap<DeviceType,String>();
        tokens.put(DeviceType.MOBILE,user.getMobileFCMtoken());
        tokens.put(DeviceType.WEAR,user.getWatchFCMtoken());
        UserDTO resultDto = new UserDTO(user.getUsername(),tokens);

        return resultDto;
    }
}
