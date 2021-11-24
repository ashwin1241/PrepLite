package com.PrepLite.dataBindings;

public class companyData {

    private String companyName;
    private String companyImage;
    private long no_participants;

    public companyData(String companyName, String companyImage, long no_participants) {
        this.companyName = companyName;
        this.companyImage = companyImage;
        this.no_participants = no_participants;
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

    public long getNo_participants() {
        return no_participants;
    }

    public void setNo_participants(long no_participants) {
        this.no_participants = no_participants;
    }
}
