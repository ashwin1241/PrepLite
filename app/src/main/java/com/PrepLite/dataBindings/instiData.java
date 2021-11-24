package com.PrepLite.dataBindings;

public class instiData {

    private String instiName;
    private String instiImage;
    private long no_participants;

    public instiData(String instiName, String instiImage, long no_participants) {
        this.instiName = instiName;
        this.instiImage = instiImage;
        this.no_participants = no_participants;
    }

    public String getInstiName() {
        return instiName;
    }

    public void setInstiName(String instiName) {
        this.instiName = instiName;
    }

    public int getInstiImage() {
        return Integer.parseInt(instiImage);
    }

    public void setInstiImage(String instiImage) {
        this.instiImage = instiImage;
    }

    public long getNo_participants() {
        return no_participants;
    }

    public void setNo_participants(long no_participants) {
        this.no_participants = no_participants;
    }
}
