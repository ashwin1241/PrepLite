package com.PrepLite.models;

public class Chat {

    public User user;
    public String chatFirstMessage;
    public String timestamp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getChatFirstMessage() {
        return chatFirstMessage;
    }

    public void setChatFirstMessage(String chatFirstMessage) {
        this.chatFirstMessage = chatFirstMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
