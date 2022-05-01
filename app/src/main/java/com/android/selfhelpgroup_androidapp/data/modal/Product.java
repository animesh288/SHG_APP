package com.android.selfhelpgroup_androidapp.data.modal;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Product {

    @SerializedName("name")
    private String name;

    @SerializedName("quantity")
    private Integer quantity;

    @SerializedName("price")
    private Integer  price;

    @SerializedName("manufacturingdate")
    private Date manufacturingDate;

    @SerializedName("type")
    private String  type;

    @SerializedName("expirydate")
    private Date  expiryDate;

    @SerializedName("unit")
    private String unit;

    @SerializedName("_id")
    private String id;



    public Product() {
    }

    public Product(String name, Integer  quantity, Integer  price, Date  manufacturingDate, String type, Date  expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.manufacturingDate = manufacturingDate;
        this.type = type;
        this.expiryDate = expiryDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date  manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date  getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date  expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
