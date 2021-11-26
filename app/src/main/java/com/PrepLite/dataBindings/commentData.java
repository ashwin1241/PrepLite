package com.PrepLite.dataBindings;

public class commentData {

    private String username;
    private String content;
    private String date;
    private String time;

    public commentData(String username, String content, String date, String time)
    {
        this.username = username;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
