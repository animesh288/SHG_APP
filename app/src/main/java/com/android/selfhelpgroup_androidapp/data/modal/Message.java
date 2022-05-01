package com.android.selfhelpgroup_androidapp.data.modal;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
