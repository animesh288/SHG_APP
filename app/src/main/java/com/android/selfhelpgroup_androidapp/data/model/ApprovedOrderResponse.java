package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApprovedOrderResponse {

    @SerializedName("products")
    private List<ApprovedOrder> products;

    public List<ApprovedOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ApprovedOrder> products) {
        this.products = products;
    }
}
