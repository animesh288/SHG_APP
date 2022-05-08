package com.android.selfhelpgroup_androidapp.data.modal;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("contact")
    private String contact;

    public LoginRequest(String contact) {
        this.contact = contact;
    }

    public LoginRequest() {
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
