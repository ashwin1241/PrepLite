package com.PrepLite.response;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("user")
    public User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
