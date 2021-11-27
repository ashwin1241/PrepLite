package com.PrepLite.models;

import com.google.gson.annotations.SerializedName;

public class University {

    @SerializedName("university_id")
    public int university_id;

    @SerializedName("name")
    public String universityName;

    @SerializedName("stream_name")
    public String stream_name;

    public University(int university_id, String universityName, String stream_name) {
        this.university_id = university_id;
        this.universityName = universityName;
        this.stream_name = stream_name;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getStream_name() {
        return stream_name;
    }

    public void setStream_name(String stream_name) {
        this.stream_name = stream_name;
    }
}
