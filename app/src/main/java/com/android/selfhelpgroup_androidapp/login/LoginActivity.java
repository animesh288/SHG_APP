package com.android.selfhelpgroup_androidapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.model.LoginRequest;
import com.android.selfhelpgroup_androidapp.data.model.LoginResponse;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;
import com.android.selfhelpgroup_androidapp.util.NetworkUtil;
import com.android.selfhelpgroup_androidapp.util.SessionManager;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText phone_number;
    private Button submitbtn;
    private SessionManager sessionManager;
    private ProgressDialog pd;

    @Inject
    Retrofit retrofit;

    @Inject
    ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        init();

        ((BaseApplication)getApplication()).getNetworkComponent().inject(this);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(NetworkUtil.isNetworkConnected(LoginActivity.this)) {
                    pd.show();
                    login();
                }else{
                    Toast.makeText(LoginActivity.this, "Internet not Connected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login() {
        String phone=phone_number.getText().toString();
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setContact(phone);

        Call<LoginResponse> call=serviceApi.getLoginResponse(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                pd.dismiss();
                if(response.isSuccessful()){
                    LoginResponse loginResponse=response.body();
                    sessionManager.saveShgId(loginResponse.getShgId());
                    sessionManager.saveContact(phone);
                    startActivity(new Intent(LoginActivity.this,OtpActivity.class));
                    finish();
                }
                else Toast.makeText(LoginActivity.this, "अमान्य फ़ोन नंबर", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        phone_number=findViewById(R.id.phone_number);
        submitbtn=findViewById(R.id.submitbtn);
        sessionManager=new SessionManager(LoginActivity.this);
        pd=new ProgressDialog(this);
        pd.setCancelable(false);
    }
}