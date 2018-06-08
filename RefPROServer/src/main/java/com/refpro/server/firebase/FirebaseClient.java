package com.refpro.server.firebase;

import com.refpro.server.dtos.FirebaseSingleDeviceMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Component
public class FirebaseClient {

    RestTemplate restTemplate;

    HttpHeaders headers;

    final static String FIREBASE_URL = "https://fcm.googleapis.com/fcm/send";

    final static String API_KEY = "key=AAAAjh3KD7U:APA91bHcDRfM4Vk96KnYf2TA_AagYbyB2Y23iRvahJEuF5mgsooL--JN7FYN4UPyisZVizN5lIB5Jl768AqiDc0ex_vbZtfg9qNxJHT8n91I8nt2t94UYjx6uJrViLps0d7jC7dB-m1k";

    public FirebaseClient() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("Authorization", Collections.singletonList(API_KEY));
    }

    public void sendMessageToSingleDevice(String token, Map<String, String> data) {
        FirebaseSingleDeviceMessage message = new FirebaseSingleDeviceMessage();
        message.setTo(token);
        message.setData(data);

        HttpEntity<FirebaseSingleDeviceMessage> entity = new HttpEntity<>(message, headers);

        restTemplate.postForLocation(FIREBASE_URL,entity);


    }
}
