package com.PrepLite.models;

import com.google.gson.annotations.SerializedName;

public class Company {

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
