package com.android.selfhelpgroup_androidapp.approvedOrders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.holder.ApprovedProductHolder;
import com.android.selfhelpgroup_androidapp.data.modal.ApprovedProduct;

import java.util.List;

public class ApprovedProductAdapter extends RecyclerView.Adapter<ApprovedProductHolder> {

    List<ApprovedProduct> productList;
    Context context;

    public ApprovedProductAdapter(List<ApprovedProduct> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    public List<ApprovedProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<ApprovedProduct> productList) {
        this.productList = productList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ApprovedProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.approved_product_item,parent,false);
        return new ApprovedProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedProductHolder holder, int position) {
        holder.itemName.setText(productList.get(position).getProduct());
        holder.itemQuantity.setText(productList.get(position).getQuantity()+productList.get(position).getUnit());
    }

    @Override
    public int getItemCount() {
        return productList==null?0:productList.size();
    }
}
