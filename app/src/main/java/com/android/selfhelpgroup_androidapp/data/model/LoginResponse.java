package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("shgId")
    private String shgId;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getShgId() {
        return shgId;
    }

    public void setShgId(String shgId) {
        this.shgId = shgId;
    }
}
