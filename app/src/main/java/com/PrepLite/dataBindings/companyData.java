package com.PrepLite.dataBindings;

public class companyData {

    private String companyName;
    private String companyImage;

    public companyData(String companyName, String companyImage) {
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
        return Integer.parseInt(companyImage);
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }
}
