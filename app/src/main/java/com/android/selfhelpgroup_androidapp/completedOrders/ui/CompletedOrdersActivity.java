package com.android.selfhelpgroup_androidapp.completedOrders.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.adapter.ApprovedOrderAdapter;
import com.android.selfhelpgroup_androidapp.approvedOrders.ui.ApprovedOrderActivity;
import com.android.selfhelpgroup_androidapp.approvedOrders.ui.ApprovedOrderDetails;
import com.android.selfhelpgroup_androidapp.approvedOrders.viewmodel.ApprovedOrderViewModel;
import com.android.selfhelpgroup_androidapp.completedOrders.adapter.CompletedOrderAdapter;
import com.android.selfhelpgroup_androidapp.completedOrders.listener.CompletedOrderListener;
import com.android.selfhelpgroup_androidapp.completedOrders.viewmodel.CompletedOrderViewModel;
import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrder;
import com.android.selfhelpgroup_androidapp.data.model.CompletedOrder;

import java.util.List;

public class CompletedOrdersActivity extends AppCompatActivity implements CompletedOrderListener {

    CompletedOrderViewModel completedOrderViewModel;
    RecyclerView recyclerView;
    List<CompletedOrder> orderList;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_orders);

        init();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startActivity(getIntent());
                finish();
                overridePendingTransition(0, 0);
            }
        });



        getOrders();
    }
    private void getOrders() {
        completedOrderViewModel.getLiveData(this).observe(this, new Observer<List<CompletedOrder>>() {
            @Override
            public void onChanged(List<CompletedOrder> orders) {
                if(orders!=null){
                    orderList=orders;
                    recyclerView.setLayoutManager(new LinearLayoutManager(CompletedOrdersActivity.this));
                    CompletedOrderAdapter orderAdapter=new CompletedOrderAdapter(CompletedOrdersActivity.this,CompletedOrdersActivity.this);
                    orderAdapter.setOrderList(orderList);
                    orderAdapter.setContext(CompletedOrdersActivity.this);
                    recyclerView.setAdapter(orderAdapter);
                    orderAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void init(){
        recyclerView=findViewById(R.id.recyclerView);
        completedOrderViewModel=new ViewModelProvider(this).get(CompletedOrderViewModel.class);
        swipeRefreshLayout=findViewById(R.id.swipe);
    }

    @Override
    public void onClick(int pos) {
        Intent intent=new Intent(CompletedOrdersActivity.this, CompletedOrderDetailsActivity.class);
        intent.putExtra("Order",orderList.get(pos));
        startActivity(intent);
    }
}