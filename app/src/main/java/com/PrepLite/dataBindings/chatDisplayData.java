package com.PrepLite.dataBindings;

public class chatDisplayData {

    private String username;
    private String chat_first_message;
    private String profile_image_url;
    private String timestamp;

    public chatDisplayData(String username, String chat_first_message, String profile_image_url, String timestamp)
    {
        this.username = username;
        this.chat_first_message = chat_first_message;
        this.profile_image_url = profile_image_url;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChat_first_message() {
        return chat_first_message;
    }

    public void setChat_first_message(String chat_first_message) {
        this.chat_first_message = chat_first_message;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
