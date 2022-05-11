package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

public class OtpRequest {

    @SerializedName("shgId")
    private String shgId;

    @SerializedName("otp")
    private String otp;

    public String getShgId() {
        return shgId;
    }

    public void setShgId(String shgId) {
        this.shgId = shgId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
