package com.PrepLite.response;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister {

    @SerializedName("error")
    public boolean error;

    @SerializedName("message")
    public String message;

    public ResponseRegister(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
