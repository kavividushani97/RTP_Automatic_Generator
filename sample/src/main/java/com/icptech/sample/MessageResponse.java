package com.icptech.sample;

public class MessageResponse {
    private ReceiveCT receiveCT;
    private String generatedMessage;

    public MessageResponse(ReceiveCT receiveCT, String generatedMessage) {
        this.receiveCT = receiveCT;
        this.generatedMessage = generatedMessage;
    }

    public ReceiveCT getReceiveCT() {
        return receiveCT;
    }

    public String getGeneratedMessage() {
        return generatedMessage;
    }
}
