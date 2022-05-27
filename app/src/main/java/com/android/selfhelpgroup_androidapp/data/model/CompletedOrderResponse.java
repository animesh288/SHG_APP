package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompletedOrderResponse {

    @SerializedName("products")
    private List<CompletedOrder> products;

    public List<CompletedOrder> getProducts() {
        return products;
    }

    public void setProducts(List<CompletedOrder> products) {
        this.products = products;
    }
}
