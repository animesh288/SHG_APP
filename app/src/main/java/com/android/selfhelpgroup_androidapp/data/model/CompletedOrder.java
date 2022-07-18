package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CompletedOrder implements Serializable {

    @SerializedName("orderid")
    private String orderId;

    @SerializedName("department")
    private String department;

    @SerializedName("institutelocation")
    private String instituteLocation;

    @SerializedName("institutename")
    private String instituteName;

    @SerializedName("institutecontact")
    private String instituteContact;

    @SerializedName("updatedAt")
    private Date updatedAt;


    @SerializedName("products")
    private List<CompletedProduct> products;

    @SerializedName("_id")
    private String bidId;

    @SerializedName("totalamount")
    private double totalAmount;

    public CompletedOrder(String orderId, String department, String instituteLocation, String instituteName, Date updatedAt, List<CompletedProduct> products, String bidId) {
        this.orderId = orderId;
        this.department = department;
        this.instituteLocation = instituteLocation;
        this.instituteName = instituteName;
        this.updatedAt = updatedAt;
        this.products = products;
        this.bidId = bidId;
    }

    public CompletedOrder() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getInstituteLocation() {
        return instituteLocation;
    }

    public void setInstituteLocation(String instituteLocation) {
        this.instituteLocation = instituteLocation;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<CompletedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CompletedProduct> products) {
        this.products = products;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInstituteContact() {
        return instituteContact;
    }

    public void setInstituteContact(String instituteContact) {
        this.instituteContact = instituteContact;
    }
}
