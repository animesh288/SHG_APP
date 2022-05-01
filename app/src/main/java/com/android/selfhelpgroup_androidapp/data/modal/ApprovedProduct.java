package com.android.selfhelpgroup_androidapp.data.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApprovedProduct implements Serializable {

    @SerializedName("shgproduct")
    private String product;

    @SerializedName("quantity")
    private Integer quantity;

    @SerializedName("unit")
    private String unit;

    @SerializedName("_id")
    private String id;

    public ApprovedProduct() {
    }

    public ApprovedProduct(String product, Integer quantity, String unit, String id) {
        this.product = product;
        this.quantity = quantity;
        this.unit = unit;
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}

