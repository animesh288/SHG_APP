package com.android.selfhelpgroup_androidapp.itemBid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.model.BidRequest;
import com.android.selfhelpgroup_androidapp.data.model.BidSubRequest;
import com.android.selfhelpgroup_androidapp.data.model.Item;
import com.android.selfhelpgroup_androidapp.data.model.Message;
import com.android.selfhelpgroup_androidapp.data.model.Order;
import com.android.selfhelpgroup_androidapp.itemBid.adapter.ItemBidAdapter;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;
import com.android.selfhelpgroup_androidapp.util.NetworkUtil;
import com.android.selfhelpgroup_androidapp.util.SessionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BidActivity extends AppCompatActivity {

    Order order;
    List<Item> items;
    RecyclerView recyclerView;
    ItemBidAdapter itemBidAdapter;
    Button submitBtn;
    BidRequest bidRequest;

    @Inject
    Retrofit retrofit;

    @Inject
    ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);

        Intent intent=getIntent();
        order= (Order) intent.getSerializableExtra("Order");
        items=order.getItems();

        ((BaseApplication)getApplication()).getNetworkComponent().inject(BidActivity.this);


        submitBtn=findViewById(R.id.submitbutton);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(BidActivity.this));
        itemBidAdapter=new ItemBidAdapter(items,this);
        recyclerView.setAdapter(itemBidAdapter);
        itemBidAdapter.notifyDataSetChanged();
        bidRequest=new BidRequest();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bidRequest.setProducts(itemBidAdapter.getBidSubRequests());
                bidRequest.setOrderId(order.getId());
                createCall();
            }
        });


    }

    private void createCall() {
        ArrayList<BidSubRequest> local=new ArrayList<>();
        for(BidSubRequest x:bidRequest.getProducts()){
            if(x.getQuantity()>0){
                local.add(new BidSubRequest(x.getProductId(),x.getQuantity(),x.getUnitPrice()));
            }
        }
        if(local==null || local.size()==0){
            Toast.makeText(this, "add product", Toast.LENGTH_SHORT).show();
            return;
        }
        bidRequest.setProducts(local);
        if(NetworkUtil.isNetworkConnected(this)){
            serviceApi=retrofit.create(ServiceApi.class);
            Call<Message> call=serviceApi.bidProduct("Bearer "+ new SessionManager(this).getToken(),bidRequest);
            call.enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(BidActivity.this, "success", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        try {
                            Toast.makeText(BidActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Toast.makeText(BidActivity.this, "call failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}