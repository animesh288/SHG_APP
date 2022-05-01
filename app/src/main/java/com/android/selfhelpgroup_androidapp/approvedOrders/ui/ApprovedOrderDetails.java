package com.android.selfhelpgroup_androidapp.approvedOrders.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.adapter.ApprovedProductAdapter;
import com.android.selfhelpgroup_androidapp.data.modal.ApprovedOrder;

import org.w3c.dom.Text;

public class ApprovedOrderDetails extends AppCompatActivity {

    ApprovedOrder approvedOrder;
    TextView instituteName,departmentName,instituteLocation;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_order_details);

        Intent intent=getIntent();
        approvedOrder= (ApprovedOrder) intent.getSerializableExtra("Order");
        init();

    }
    public void init(){
        instituteName=findViewById(R.id.instituteName);
        instituteLocation=findViewById(R.id.instituteLocation);
        departmentName=findViewById(R.id.departmentName);
        recyclerView=findViewById(R.id.recyclerView);
        instituteLocation.setText(approvedOrder.getInstituteLocation());
        instituteName.setText(approvedOrder.getInstituteName());
        departmentName.setText(approvedOrder.getDepartment());
        initRecyclerView();
    }

    public void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(ApprovedOrderDetails.this));
        ApprovedProductAdapter adapter=new ApprovedProductAdapter(approvedOrder.getProducts(),ApprovedOrderDetails.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}