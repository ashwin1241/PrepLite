package com.PrepLite.models;

import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("company_id")
    public int company_id;

    @SerializedName("name")
    public String companyName;

    @SerializedName("logo")
    public String companyLogo;

    public Company(int company_id, String companyName, String companyLogo) {
        this.company_id = company_id;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
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
}
