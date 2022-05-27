package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

public class BidSubRequest {

    @SerializedName("productid")
    private String productId;

    @SerializedName("quantity")
    private Double quantity;

    @SerializedName("unitprice")
    private Double unitPrice;

    public BidSubRequest(String productId, Double quantity,Double unitPrice) {
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
