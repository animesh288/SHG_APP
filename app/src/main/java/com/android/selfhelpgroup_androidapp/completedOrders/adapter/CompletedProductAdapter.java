package com.android.selfhelpgroup_androidapp.completedOrders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.holder.ApprovedProductHolder;
import com.android.selfhelpgroup_androidapp.completedOrders.holder.CompletedProductHolder;
import com.android.selfhelpgroup_androidapp.data.model.ApprovedProduct;
import com.android.selfhelpgroup_androidapp.data.model.CompletedProduct;

import java.util.List;

public class CompletedProductAdapter extends RecyclerView.Adapter<CompletedProductHolder> {

    List<CompletedProduct> productList;
    Context context;

    public CompletedProductAdapter(List<CompletedProduct> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    public List<CompletedProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<CompletedProduct> productList) {
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
    public CompletedProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.completed_product_item,parent,false);
        return new CompletedProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedProductHolder holder, int position) {
        holder.itemName.setText(productList.get(position).getProduct());
        holder.itemQuantity.setText(productList.get(position).getUnitPrice()+" x "+ productList.get(position).getQuantity()+productList.get(position).getUnit());
        holder.price.setText(String.format("%.2f", productList.get(position).getPrice())+" ₹");
    }

    @Override
    public int getItemCount() {
        return productList==null?0:productList.size();
    }
}
