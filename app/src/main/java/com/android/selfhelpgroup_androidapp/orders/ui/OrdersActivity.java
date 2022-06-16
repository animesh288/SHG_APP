package com.android.selfhelpgroup_androidapp.orders.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.selfhelpgroup_androidapp.itemBid.ui.BidActivity;
import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.model.Order;
import com.android.selfhelpgroup_androidapp.orders.adapter.OrderAdapter;
import com.android.selfhelpgroup_androidapp.orders.listener.OrderClickListener;
import com.android.selfhelpgroup_androidapp.orders.viewmodel.OrderActivityViewModel;

import java.util.List;

public class OrdersActivity extends AppCompatActivity implements OrderClickListener {

    OrderActivityViewModel orderActivityViewModel;
    RecyclerView recyclerView;
    List<Order> orderList;
    SwipeRefreshLayout swipeRefreshLayout;
    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


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
        orderActivityViewModel.getLiveData(this).observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                if(orders!=null && orders.size()>0){
                    orderList=orders;
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrdersActivity.this));
                    orderAdapter=new OrderAdapter(OrdersActivity.this,OrdersActivity.this);
                    orderAdapter.setOrderList(orderList);
                    orderAdapter.setContext(OrdersActivity.this);
                    recyclerView.setAdapter(orderAdapter);
                    orderAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(OrdersActivity.this, "कोई आर्डर नहीं है", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init(){
        recyclerView=findViewById(R.id.recyclerView);
        orderActivityViewModel=new ViewModelProvider(this).get(OrderActivityViewModel.class);
        swipeRefreshLayout=findViewById(R.id.swipe);
    }

    @Override
    public void onClick(int position) {
        Intent intent=new Intent(OrdersActivity.this, BidActivity.class);
        intent.putExtra("Order",orderList.get(position));
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}