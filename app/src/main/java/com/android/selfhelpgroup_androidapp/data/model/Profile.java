package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("name")
    private String name;

    @SerializedName("contact")
    private String contact;

    @SerializedName("location")
    private String location;

    public Profile() {
    }


    public Profile(String name, String contact, String location) {
        this.name = name;
        this.contact = contact;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
