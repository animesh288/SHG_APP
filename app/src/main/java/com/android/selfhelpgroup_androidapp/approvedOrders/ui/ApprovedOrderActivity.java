package com.android.selfhelpgroup_androidapp.approvedOrders.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.adapter.ApprovedOrderAdapter;
import com.android.selfhelpgroup_androidapp.approvedOrders.viewmodel.ApprovedOrderViewModel;
import com.android.selfhelpgroup_androidapp.data.modal.ApprovedOrder;
import com.android.selfhelpgroup_androidapp.data.modal.Order;
import com.android.selfhelpgroup_androidapp.orders.adapter.OrderAdapter;
import com.android.selfhelpgroup_androidapp.orders.ui.OrdersActivity;
import com.android.selfhelpgroup_androidapp.orders.viewmodel.OrderActivityViewModel;

import java.util.List;

public class ApprovedOrderActivity extends AppCompatActivity {

    ApprovedOrderViewModel approvedOrderViewModel;
    RecyclerView recyclerView;
    List<ApprovedOrder> orderList;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_order);

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
        approvedOrderViewModel.getLiveData(this).observe(this, new Observer<List<ApprovedOrder>>() {
            @Override
            public void onChanged(List<ApprovedOrder> orders) {
                if(orders!=null){
                    orderList=orders;
                    recyclerView.setLayoutManager(new LinearLayoutManager(ApprovedOrderActivity.this));
                    ApprovedOrderAdapter orderAdapter=new ApprovedOrderAdapter();
                    orderAdapter.setOrderList(orderList);
                    orderAdapter.setContext(ApprovedOrderActivity.this);
                    recyclerView.setAdapter(orderAdapter);
                    orderAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void init(){
        recyclerView=findViewById(R.id.recyclerView);
        approvedOrderViewModel=new ViewModelProvider(this).get(ApprovedOrderViewModel.class);
        swipeRefreshLayout=findViewById(R.id.swipe);
    }
}