package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompletedRequest implements Serializable {

    @SerializedName("orderid")
    private String orderId;

    public CompletedRequest(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

