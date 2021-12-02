package com.PrepLite.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class University implements Parcelable {

    @SerializedName("university_id")
    public int universityId;

    @SerializedName("name")
    public String universityName;

    @SerializedName("stream_name")
    public String streamName;

    @SerializedName("university_logo")
    public String universityLogo;

    @SerializedName("participants")
    public int participants;

    public University(int universityId, String universityName, String streamName, String universityLogo, int participants) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.streamName = streamName;
        this.universityLogo = universityLogo;
        this.participants = participants;
    }

    public University(String universityName, String universityLogo, int participants) {
        this.universityName = universityName;
        this.universityLogo = universityLogo;
        this.participants = participants;
    }

    public University(String universityName) {
        this.universityName = universityName;
    }

    protected University(Parcel in) {
        universityId = in.readInt();
        universityName = in.readString();
        streamName = in.readString();
        universityLogo = in.readString();
        participants = in.readInt();
    }

    public static final Creator<University> CREATOR = new Creator<University>() {
        @Override
        public University createFromParcel(Parcel in) {
            return new University(in);
        }

        @Override
        public University[] newArray(int size) {
            return new University[size];
        }
    };

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getUniversityLogo() {
        return universityLogo;
    }

    public void setUniversityLogo(String universityLogo) {
        this.universityLogo = universityLogo;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(universityId);
        parcel.writeString(universityName);
        parcel.writeString(streamName);
        parcel.writeString(universityLogo);
        parcel.writeInt(participants);
    }
}
