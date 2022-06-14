package com.android.selfhelpgroup_androidapp.completedOrders.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.adapter.ApprovedProductAdapter;
import com.android.selfhelpgroup_androidapp.approvedOrders.ui.ApprovedOrderDetails;
import com.android.selfhelpgroup_androidapp.completedOrders.adapter.CompletedProductAdapter;
import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrder;
import com.android.selfhelpgroup_androidapp.data.model.CompletedOrder;

public class CompletedOrderDetailsActivity extends AppCompatActivity {

    CompletedOrder completedOrder;
    TextView instituteName,departmentName,instituteLocation,total;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_order_details);

        Intent intent=getIntent();
        completedOrder= (CompletedOrder) intent.getSerializableExtra("Order");
        init();
    }

    public void init(){
        instituteName=findViewById(R.id.instituteName);
        instituteLocation=findViewById(R.id.instituteLocation);
        departmentName=findViewById(R.id.departmentName);
        recyclerView=findViewById(R.id.recyclerView);
        total=findViewById(R.id.total);

        instituteLocation.setText(completedOrder.getInstituteLocation());
        instituteName.setText(completedOrder.getInstituteName());
        departmentName.setText(completedOrder.getDepartment());
        total.setText("Rs. "+completedOrder.getTotalAmount());
        initRecyclerView();
    }

    public void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(CompletedOrderDetailsActivity.this));
        CompletedProductAdapter adapter=new CompletedProductAdapter(completedOrder.getProducts(),CompletedOrderDetailsActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}