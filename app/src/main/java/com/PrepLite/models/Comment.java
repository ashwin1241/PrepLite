package com.PrepLite.models;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("user")
    public User user;

    @SerializedName("content")
    public String content;

    @SerializedName("timestamp")
    public String timestamp;

    public Comment(User user, String content, String timestamp) {
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
