package com.android.selfhelpgroup_androidapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ApprovedOrder implements Serializable {

    @SerializedName("orderid")
    private String orderId;

    @SerializedName("department")
    private String department;

    @SerializedName("institutelocation")
    private String instituteLocation;

    @SerializedName("institutename")
    private String instituteName;

    @SerializedName("_id")
    private String bidId;

    @SerializedName("status")
    private String status;
    
    @SerializedName("updatedAt")
    private Date updatedAt;


    @SerializedName("products")
    private List<ApprovedProduct> products;

    @SerializedName("totalamount")
    private double totalAmount;



    public ApprovedOrder(String id, String department, String instituteLocation, String instituteName) {
        this.orderId = id;
        this.department = department;
        this.instituteLocation = instituteLocation;
        this.instituteName = instituteName;
    }

    public ApprovedOrder() {
    }

    public String getId() {
        return orderId;
    }

    public void setId(String id) {
        this.orderId = id;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ApprovedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ApprovedProduct> products) {
        this.products = products;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
}
