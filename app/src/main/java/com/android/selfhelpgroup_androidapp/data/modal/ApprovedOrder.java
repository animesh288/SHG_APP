package com.android.selfhelpgroup_androidapp.data.modal;

import com.google.gson.annotations.SerializedName;

public class ApprovedOrder {

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("_id")
    private String id;

    @SerializedName("bidorderid")
    private String bidOrderId;

    @SerializedName("department")
    private String department;

    @SerializedName("unit")
    private String unit;

    @SerializedName("institutelocation")
    private String instituteLocation;

    @SerializedName("institutename")
    private String instituteName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBidOrderId() {
        return bidOrderId;
    }

    public void setBidOrderId(String bidOrderId) {
        this.bidOrderId = bidOrderId;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
}
