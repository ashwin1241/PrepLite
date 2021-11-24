package com.PrepLite.dataBindings;

public class companyData {

    private String companyName;
    private Integer companyImage;

    public companyData(String companyName, Integer companyImage) {
        this.companyName = companyName;
        this.companyImage = companyImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(Integer companyImage) {
        this.companyImage = companyImage;
    }
}
