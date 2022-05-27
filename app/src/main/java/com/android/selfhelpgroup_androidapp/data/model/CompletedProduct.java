package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompletedProduct implements Serializable {

    @SerializedName("shgproduct")
    private String product;

    @SerializedName("quantity")
    private Double quantity;

    @SerializedName("unit")
    private String unit;

    @SerializedName("totalprice")
    private Double price;

    @SerializedName("unitprice")
    private Double unitPrice;

    public CompletedProduct() {
    }

    public CompletedProduct(String product, Double quantity, String unit, Double price, Double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.unitPrice = unitPrice;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
