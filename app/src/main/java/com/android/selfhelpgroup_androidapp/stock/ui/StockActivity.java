package com.android.selfhelpgroup_androidapp.stock.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.selfhelpgroup_androidapp.AddProductActivity;
import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.model.Product;
import com.android.selfhelpgroup_androidapp.stock.adapter.ProductAdapter;
import com.android.selfhelpgroup_androidapp.stock.listener.ProductClickListener;
import com.android.selfhelpgroup_androidapp.stock.viewmodel.StockActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StockActivity extends AppCompatActivity implements ProductClickListener {

    StockActivityViewModel stockActivityViewModel;
    RecyclerView recyclerView;
    List<Product> productList;
    FloatingActionButton addProduct;
    ProductAdapter productAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        init();

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StockActivity.this, AddProductActivity.class));
                finish();
                }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startActivity(getIntent());
                finish();
                overridePendingTransition(0, 0);
            }
        });
        getProducts();

    }

    private void getProducts() {
        stockActivityViewModel.getLiveData(this).observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if(products!=null) {
                    productList = products;
                    recyclerView.setLayoutManager(new LinearLayoutManager(StockActivity.this));
                    productAdapter = new ProductAdapter(StockActivity.this, StockActivity.this);
                    productAdapter.setContext(StockActivity.this);
                    productAdapter.setProductList(productList);
                    recyclerView.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
//        startActivity(new Intent(StockActivity.this, EditProductActivity.class));
    }

    @Override
    public void onDeleteClick(int position) {
        if(productAdapter!=null){
            productAdapter.showDialog(position);
        }
    }
    private void init(){
        addProduct=findViewById(R.id.addproductbtn);
        stockActivityViewModel=new ViewModelProvider(this).get(StockActivityViewModel.class);
        recyclerView=findViewById(R.id.recyclerView);
        swipeRefreshLayout=findViewById(R.id.swipeProduct);
        pd=new ProgressDialog(this);
    }
}