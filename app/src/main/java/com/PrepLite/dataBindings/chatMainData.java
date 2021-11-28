package com.PrepLite.dataBindings;

public class chatMainData {

    private String date;
    private String time;
    private String message;
    private boolean isSender;
    private boolean isFirstOfSameType;

    public chatMainData(String date, String time, String message, boolean isSender, boolean isFirstOfSameType) {
        this.date = date;
        this.time = time;
        this.message = message;
        this.isSender = isSender;
        this.isFirstOfSameType = isFirstOfSameType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }

    public boolean isFirstOfSameType() {
        return isFirstOfSameType;
    }

    public void setFirstOfSameType(boolean firstOfSameType) {
        isFirstOfSameType = firstOfSameType;
    }
}
