package com.refpro.server.dtos;

import java.util.HashMap;
import java.util.Map;

public class FirebaseSingleDeviceMessage {
    private String to;
    private Map<String, String> data;

    public FirebaseSingleDeviceMessage(String to, Map<String, String> data) {
        this.to = to;
        this.data = data;
    }

    public FirebaseSingleDeviceMessage() {
        data = new HashMap<>();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public void addToData(String key, String value) {
        this.data.put(key, value);
    }
}
