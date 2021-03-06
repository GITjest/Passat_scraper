package com.example.webscraper.response;

import com.example.webscraper.model.PassatEntity;

import java.util.List;
import java.util.Map;

public class PassatResponse {
    private boolean validated;
    private Map<String, String> errorMessages;
    private List<PassatEntity> passats;

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<PassatEntity> getPassats() {
        return passats;
    }

    public void setPassats(List<PassatEntity> passats) {
        this.passats = passats;
    }
}
