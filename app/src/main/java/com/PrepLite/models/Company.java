package com.PrepLite.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Company implements Parcelable {

    @SerializedName("company_id")
    public int companyId;

    @SerializedName("name")
    public String companyName;

    @SerializedName("logo")
    public String companyLogo;

    @SerializedName("participants")
    public int participants;

    public Company(int companyId, String companyName, String companyLogo, int participants) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.participants = participants;
    }

    public Company(String companyName, String companyLogo, int participants) {
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.participants = participants;
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    protected Company(Parcel in) {
        companyId = in.readInt();
        companyName = in.readString();
        companyLogo = in.readString();
        participants = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(companyId);
        dest.writeString(companyName);
        dest.writeString(companyLogo);
        dest.writeInt(participants);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }
}
