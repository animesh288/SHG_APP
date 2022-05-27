package com.android.selfhelpgroup_androidapp.completedOrders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.holder.ApprovedOrderHolder;
import com.android.selfhelpgroup_androidapp.approvedOrders.listener.ApprovedOrderListener;
import com.android.selfhelpgroup_androidapp.completedOrders.holder.CompletedOrderHolder;
import com.android.selfhelpgroup_androidapp.completedOrders.listener.CompletedOrderListener;
import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrder;
import com.android.selfhelpgroup_androidapp.data.model.CompletedOrder;

import java.text.SimpleDateFormat;
import java.util.List;

public class CompletedOrderAdapter extends RecyclerView.Adapter<CompletedOrderHolder> {

    List<CompletedOrder> orderList;
    Context context;
    CompletedOrderListener completedOrderListener;
    SimpleDateFormat simpleDateFormat1,simpleDateFormat2;

    public List<CompletedOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<CompletedOrder> orderList) {
        this.orderList = orderList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public CompletedOrderAdapter(Context context, CompletedOrderListener completedOrderListener) {
        this.context = context;
        this.completedOrderListener = completedOrderListener;
        simpleDateFormat1=new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat2=new SimpleDateFormat("HH:mm a");
    }

    @NonNull
    @Override
    public CompletedOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.completed_order_item,parent,false);
        return new CompletedOrderHolder(view,completedOrderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedOrderHolder holder, int position) {
        holder.instituteName.setText(orderList.get(position).getInstituteName());
        holder.departmentName.setText(orderList.get(position).getDepartment());
        holder.instituteLocation.setText("पता : "+orderList.get(position).getInstituteLocation());
        holder.updateDate.setText("तिथि : "+simpleDateFormat1.format(orderList.get(position).getUpdatedAt()));
        holder.updateTime.setText("समय : "+ simpleDateFormat2.format(orderList.get(position).getUpdatedAt()));

    }

    @Override
    public int getItemCount() {
        return orderList==null?0:orderList.size();
    }
}
