package com.android.selfhelpgroup_androidapp.home.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.approvedOrders.ui.ApprovedOrderActivity;
import com.android.selfhelpgroup_androidapp.data.model.SliderData;
import com.android.selfhelpgroup_androidapp.home.ImageUrls;
import com.android.selfhelpgroup_androidapp.home.adapter.SliderAdapter;
import com.android.selfhelpgroup_androidapp.login.LoginActivity;
import com.android.selfhelpgroup_androidapp.orders.ui.OrdersActivity;
import com.android.selfhelpgroup_androidapp.stock.ui.StockActivity;
import com.android.selfhelpgroup_androidapp.util.SessionManager;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ImageView stock,orders,approved,logout;
    AlertDialog alertDialog;
    AlertDialog.Builder ab;
    SliderView sliderView;
    ArrayList<SliderData> sliderDataArrayList;
    SliderAdapter adapter;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        initAlertDialog();

        initSlider();

        Log.i("animesh",new SessionManager(HomeActivity.this).getToken());

        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,StockActivity.class));
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OrdersActivity.class));
            }
        });

        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ApprovedOrderActivity.class));

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });
    }

    private void initSlider() {

        sliderDataArrayList=new ArrayList<>();
        sliderDataArrayList.add(new SliderData(ImageUrls.url1));
        sliderDataArrayList.add(new SliderData(ImageUrls.url2));
        sliderDataArrayList.add(new SliderData(ImageUrls.url3));

        adapter=new SliderAdapter(this,sliderDataArrayList);

        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        sliderView.setSliderAdapter(adapter);

        sliderView.setScrollTimeInSec(3);

        sliderView.setAutoCycle(true);

        sliderView.startAutoCycle();
    }

    private void initView(){
        stock=findViewById(R.id.stock);
        orders=findViewById(R.id.orders);
        approved=findViewById(R.id.approved);
        logout=findViewById(R.id.logout);
        sliderView=findViewById(R.id.slider);
        description=findViewById(R.id.description);

        description.setMovementMethod(new ScrollingMovementMethod());
    }

    private void initAlertDialog() {
        ab=new AlertDialog.Builder(HomeActivity.this).setMessage("LogOut ?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alertDialog=ab.create();
    }

    private void logout() {
        new SessionManager(HomeActivity.this).deleteUser();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
    }
}