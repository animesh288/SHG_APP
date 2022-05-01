package com.android.selfhelpgroup_androidapp.approvedOrders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.holder.ApprovedOrderHolder;
import com.android.selfhelpgroup_androidapp.approvedOrders.listener.ApprovedOrderListener;
import com.android.selfhelpgroup_androidapp.data.modal.ApprovedOrder;
import com.android.selfhelpgroup_androidapp.data.modal.Order;

import java.util.List;

public class ApprovedOrderAdapter extends RecyclerView.Adapter<ApprovedOrderHolder> {

    List<ApprovedOrder> orderList;
    Context context;
    ApprovedOrderListener approvedOrderListener;

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

    public ApprovedOrderAdapter(Context context, ApprovedOrderListener approvedOrderListener) {
        this.context = context;
        this.approvedOrderListener = approvedOrderListener;
    }

    @NonNull
    @Override
    public ApprovedOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.approved_order_item,parent,false);
        return new ApprovedOrderHolder(view,approvedOrderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedOrderHolder holder, int position) {
        holder.instituteName.setText(orderList.get(position).getInstituteName());
        holder.departmentName.setText(orderList.get(position).getDepartment());
        holder.instituteLocation.setText("पता : "+orderList.get(position).getInstituteLocation());
    }

    @Override
    public int getItemCount() {
        return orderList==null?0:orderList.size();
    }
}
