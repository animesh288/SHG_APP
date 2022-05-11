package com.android.selfhelpgroup_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.selfhelpgroup_androidapp.data.model.Message;
import com.android.selfhelpgroup_androidapp.data.model.Product;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.stock.ui.StockActivity;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;
import com.android.selfhelpgroup_androidapp.util.NetworkUtil;
import com.android.selfhelpgroup_androidapp.util.SessionManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddProductActivity extends AppCompatActivity {


    String[] types=new String[]{"packed","loose"};
    String[] units=new String[]{"kg","dozen"};
    AutoCompleteTextView textView,unit;
    EditText name,mfg,exp,quantity,price;
    Button submit;
    Product product;
    ProgressDialog pd;
    SessionManager sessionManager;
    String unitType="kg";


    @Inject
    Retrofit retrofit;

    @Inject
    ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        textView=findViewById(R.id.type);
        name=findViewById(R.id.name);
        mfg=findViewById(R.id.mfg);
        exp=findViewById(R.id.exp);
        quantity=findViewById(R.id.quantity);
        submit=findViewById(R.id.addproductbtn);
        unit=findViewById(R.id.unit);

        product=new Product();
        pd=new ProgressDialog(this);
        sessionManager=new SessionManager(this);

        ((BaseApplication)getApplication()).getNetworkComponent().inject(this);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.dropdown_item,types);
        ArrayAdapter<String> unitAdapter=new ArrayAdapter<>(this,R.layout.dropdown_item,units);

        textView.setAdapter(adapter);
        unit.setAdapter(unitAdapter);

        mfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(AddProductActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mfg.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(AddProductActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        exp.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        textView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product.setType(position==0?"packed":"loose");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitType=units[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkUtil.isNetworkConnected(AddProductActivity.this)) {
                    addproduct();

                }else{
                    Toast.makeText(AddProductActivity.this, "Internet not Connected", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void addproduct() {
        if(TextUtils.isEmpty(name.getText())){
            Toast.makeText(this, "add product name", Toast.LENGTH_SHORT).show();
        }else{
            product.setName(name.getText().toString());
        }
        if(TextUtils.isEmpty(quantity.getText())){
            Toast.makeText(this, "add product quantity", Toast.LENGTH_SHORT).show();
        }else{
            product.setQuantity(Integer.valueOf(quantity.getText().toString()));
        }
        product.setPrice(24);
        try {
            product.setManufacturingDate((new SimpleDateFormat("dd/MM/yyyy")).parse(mfg.getText().toString()));
        }catch (Exception e){
            product.setManufacturingDate(null);
        }
        try {
            product.setExpiryDate((new SimpleDateFormat("dd/MM/yyyy")).parse(exp.getText().toString()));
        }catch (Exception e){
            product.setExpiryDate(null);
        }
        product.setType(textView.getText().toString());
        product.setUnit(unit.getText().toString());

        if(TextUtils.isEmpty(product.getName()) || TextUtils.isEmpty(product.getType()) || TextUtils.isEmpty(product.getQuantity()+"")){
            Toast.makeText(this, "add all product details", Toast.LENGTH_SHORT).show();
        }else{
            if(product.getType().equals(types[0])){
                if(product.getExpiryDate()==null || product.getManufacturingDate()==null||TextUtils.isEmpty(product.getManufacturingDate().toString()) || TextUtils.isEmpty(product.getExpiryDate().toString())){
                    Toast.makeText(this, "manufacturing and expiry date required", Toast.LENGTH_SHORT).show();
                }else{
                    pd.show();
                    createCall();
                }
            }else{
                if(TextUtils.isEmpty(product.getUnit())){
                    Toast.makeText(this, "unit required", Toast.LENGTH_SHORT).show();
                }else{
                    pd.show();
                    createCall();
                }
            }
        }


    }

    private void createCall() {
        if(NetworkUtil.isNetworkConnected(this)){
            serviceApi=retrofit.create(ServiceApi.class);
            Call<Message> call=serviceApi.addProduct("Bearer "+ new SessionManager(this).getToken(),product);
            call.enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(AddProductActivity.this, "product added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddProductActivity.this,StockActivity.class));
                        finish();
                    }else{
                        try {
                            Toast.makeText(AddProductActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        } catch (IOException e) {
                            pd.dismiss();
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Toast.makeText(AddProductActivity.this, "call failed", Toast.LENGTH_SHORT).show();
                    pd.dismiss();

                }
            });
        }else{
            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
        }
    }
}