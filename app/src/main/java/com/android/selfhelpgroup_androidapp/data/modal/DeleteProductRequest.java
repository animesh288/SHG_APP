package com.android.selfhelpgroup_androidapp.data.modal;

import com.google.gson.annotations.SerializedName;

public class DeleteProductRequest {

    @SerializedName("productid")
    private String productId;

    public DeleteProductRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
