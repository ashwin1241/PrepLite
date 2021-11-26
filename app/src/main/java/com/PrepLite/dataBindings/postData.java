package com.PrepLite.dataBindings;

public class postData {

    private String username;
    private String comp_insti;
    private String date;
    private String time;
    private String content;
    private String img_url;

    public postData(String username, String comp_insti, String date, String time, String content,String img_url)
    {
        this.username=username;
        this.comp_insti=comp_insti;
        this.date=date;
        this.time=time;
        this.content=content;
        this.img_url=img_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComp_insti() {
        return comp_insti;
    }

    public void setComp_insti(String comp_insti) {
        this.comp_insti = comp_insti;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
