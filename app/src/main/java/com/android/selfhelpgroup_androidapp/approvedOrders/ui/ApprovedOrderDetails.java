package com.android.selfhelpgroup_androidapp.approvedOrders.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.adapter.ApprovedProductAdapter;
import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrder;

public class ApprovedOrderDetails extends AppCompatActivity {

    ApprovedOrder approvedOrder;
    TextView instituteName,departmentName,instituteLocation;
    RecyclerView recyclerView;
    Button print;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_order_details);

        Intent intent=getIntent();
        approvedOrder= (ApprovedOrder) intent.getSerializableExtra("Order");
        init();
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePdf();
            }
        });

    }

    private void generatePdf() {
    }

    public void init(){
        instituteName=findViewById(R.id.instituteName);
        instituteLocation=findViewById(R.id.instituteLocation);
        departmentName=findViewById(R.id.departmentName);
        recyclerView=findViewById(R.id.recyclerView);
        print=findViewById(R.id.print);
        
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