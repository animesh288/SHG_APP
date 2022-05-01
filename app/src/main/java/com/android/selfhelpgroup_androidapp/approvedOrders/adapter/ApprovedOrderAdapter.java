package com.android.selfhelpgroup_androidapp.approvedOrders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.holder.ApprovedOrderHolder;
import com.android.selfhelpgroup_androidapp.data.modal.ApprovedOrder;
import com.android.selfhelpgroup_androidapp.data.modal.Order;

import java.util.List;

public class ApprovedOrderAdapter extends RecyclerView.Adapter<ApprovedOrderHolder> {

    List<ApprovedOrder> orderList;
    Context context;

    public List<ApprovedOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ApprovedOrder> orderList) {
        this.orderList = orderList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ApprovedOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.approved_order_item,parent,false);
        return new ApprovedOrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedOrderHolder holder, int position) {
        holder.name.setText(orderList.get(position).getName());
        holder.department.setText(orderList.get(position).getDepartment());
        holder.institute.setText(orderList.get(position).getInstituteName());
        holder.quantity.setText(orderList.get(position).getQuantity()+" "+orderList.get(position).getUnit());
    }

    @Override
    public int getItemCount() {
        return orderList==null?0:orderList.size();
    }
}
