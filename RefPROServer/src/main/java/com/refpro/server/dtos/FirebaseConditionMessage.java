package com.refpro.server.dtos;

import java.util.Map;

public class FirebaseConditionMessage {
    private String condition;
    private Map<String, String> data;

    public FirebaseConditionMessage(String condition, Map<String, String> data) {
        this.condition = condition;
        this.data = data;
    }

    public FirebaseConditionMessage() {
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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
