package com.android.selfhelpgroup_androidapp.data.modal;

import com.google.gson.annotations.SerializedName;

public class BidSubRequest {

    @SerializedName("productid")
    private String productId;

    @SerializedName("quantity")
    private Integer quantity;

    public BidSubRequest(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public BidSubRequest() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
