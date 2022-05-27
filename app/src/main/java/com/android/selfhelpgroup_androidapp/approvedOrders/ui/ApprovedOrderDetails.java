package com.android.selfhelpgroup_androidapp.approvedOrders.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.adapter.ApprovedProductAdapter;
import com.android.selfhelpgroup_androidapp.approvedOrders.viewmodel.ApprovedOrderViewModel;
import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrder;
import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrderResponse;
import com.android.selfhelpgroup_androidapp.data.model.CompletedRequest;
import com.android.selfhelpgroup_androidapp.data.model.Message;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;
import com.android.selfhelpgroup_androidapp.util.NetworkUtil;
import com.android.selfhelpgroup_androidapp.util.PrintInvoice;
import com.android.selfhelpgroup_androidapp.util.SessionManager;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApprovedOrderDetails extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @Inject
    ServiceApi serviceApi;

    ApprovedOrder approvedOrder;
    TextView instituteName,departmentName,instituteLocation;
    RecyclerView recyclerView;
    Button completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_order_details);
        ((BaseApplication)getApplication()).getNetworkComponent().inject(ApprovedOrderDetails.this);
        Intent intent=getIntent();
        approvedOrder= (ApprovedOrder) intent.getSerializableExtra("Order");
        init();

        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completed.setEnabled(false);
                if(NetworkUtil.isNetworkConnected(ApprovedOrderDetails.this)){
                    Call<Message> call=serviceApi.completed("Bearer "+ new SessionManager(ApprovedOrderDetails.this).getToken(),new CompletedRequest(approvedOrder.getId()));
                    call.enqueue(new Callback<Message>() {
                        @Override
                        public void onResponse(Call<Message> call, Response<Message> response) {
                            if(response.code()==401) new SessionManager(ApprovedOrderDetails.this).logoutUser();
                            else if(response.isSuccessful()){
                                Toast.makeText(ApprovedOrderDetails.this, "अनुरोध भेज दिया गया है", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                completed.setEnabled(true);
                                try {
                                    Log.i("animesh",response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Message> call, Throwable t) {
                            Log.i("animesh",t.getMessage());
                            completed.setEnabled(true);
                        }
                    });
                }else{
                    Toast.makeText(ApprovedOrderDetails.this, R.string.check_internet, Toast.LENGTH_SHORT).show();
                    completed.setEnabled(true);
                }
            }
        });


    }
    public void init(){
        instituteName=findViewById(R.id.instituteName);
        instituteLocation=findViewById(R.id.instituteLocation);
        departmentName=findViewById(R.id.departmentName);
        recyclerView=findViewById(R.id.recyclerView);
        completed=findViewById(R.id.orderCompleted);

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