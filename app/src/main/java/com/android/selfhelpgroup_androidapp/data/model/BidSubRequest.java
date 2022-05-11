package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

public class BidSubRequest {

    @SerializedName("productid")
    private String productId;

    @SerializedName("quantity")
    private Integer quantity;

    @SerializedName("unitprice")
    private Integer unitPrice;

    public BidSubRequest(String productId, Integer quantity,Integer unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice=unitPrice;
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

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }
}
