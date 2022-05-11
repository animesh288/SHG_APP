package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BidRequest {

    @SerializedName("orderid")
    private String orderId;


    @SerializedName("product")
    private List<BidSubRequest> products;

    public BidRequest() {
    }

    public BidRequest(String orderId, List<BidSubRequest> products) {
        this.orderId = orderId;
        this.products = products;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<BidSubRequest> getProducts() {
        return products;
    }

    public void setProducts(List<BidSubRequest> products) {
        this.products = products;
    }
}
