package com.android.selfhelpgroup_androidapp.approvedOrders.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.modal.ApprovedOrder;

public class ApprovedOrderDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_order_details);

        Intent intent=getIntent();
        ApprovedOrder approvedOrder= (ApprovedOrder) intent.getSerializableExtra("Order");
        Log.i("animesh",approvedOrder.getProducts().toString());
    }
}