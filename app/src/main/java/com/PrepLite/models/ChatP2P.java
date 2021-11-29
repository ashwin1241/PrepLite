package com.PrepLite.models;

import com.google.gson.annotations.SerializedName;

public class ChatP2P {

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("message")
    public String message;

    public boolean isSender;

    public boolean isFirstOfSameType;

    @SerializedName("from")
    public User fromUser;

    @SerializedName("to")
    public User toUser;

    public ChatP2P(String timestamp, String message, boolean isSender, boolean isFirstOfSameType, User fromUser, User toUser) {
        this.timestamp = timestamp;
        this.message = message;
        this.isSender = isSender;
        this.isFirstOfSameType = isFirstOfSameType;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public ChatP2P(String timestamp, String message, boolean isSender, boolean isFirstOfSameType) {
        this.timestamp = timestamp;
        this.message = message;
        this.isSender = isSender;
        this.isFirstOfSameType = isFirstOfSameType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }

    public boolean isFirstOfSameType() {
        return isFirstOfSameType;
    }

    public void setFirstOfSameType(boolean firstOfSameType) {
        isFirstOfSameType = firstOfSameType;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
}
