package com.PrepLite.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    public int id;

    @SerializedName("name")
    public String username;

    @SerializedName("email")
    public String email;

    @SerializedName("batch")
    public String batch;

    @SerializedName("alumni")
    public boolean alumni;

    @SerializedName("phone")
    public String phone;

    @SerializedName("profileImage")
    public String profileImage;

    public User(int id, String username, String email, String batch, boolean alumni, String phone, String profileImage) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.batch = batch;
        this.alumni = alumni;
        this.phone = phone;
        this.profileImage = profileImage;
    }

    public User(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isAlumni() {
        return alumni;
    }

    public void setAlumni(boolean alumni) {
        this.alumni = alumni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
