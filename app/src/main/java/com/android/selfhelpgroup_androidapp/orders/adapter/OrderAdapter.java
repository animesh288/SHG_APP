package com.android.selfhelpgroup_androidapp.orders.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.model.Order;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.orders.holder.OrderHolder;
import com.android.selfhelpgroup_androidapp.orders.listener.OrderClickListener;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {

    List<Order> orderList;
    Context context;
    OrderClickListener orderClickListener;
    SimpleDateFormat simpleDateFormat;


    @Inject
    Retrofit retrofit;

    @Inject
    ServiceApi serviceApi;

    public OrderAdapter(OrderClickListener orderClickListener,Context context){
        this.orderClickListener=orderClickListener;
        this.context=context;

        ((BaseApplication)context.getApplicationContext()).getNetworkComponent().inject(OrderAdapter.this);
        serviceApi=retrofit.create(ServiceApi.class);
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
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
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_item,parent,false);
        return new OrderHolder(view,orderClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        holder.instituteName.setText(orderList.get(position).getInstituteName());
        holder.departmentName.setText(orderList.get(position).getDepartment());
        holder.instituteLocation.setText("पता : "+orderList.get(position).getInstituteLocation());
        holder.updateDate.setText("आर्डर की तारीख : "+simpleDateFormat.format(orderList.get(position).getUpdatedAt()));
    }

    @Override
    public int getItemCount() {
        return orderList==null?0:orderList.size();
    }

}
