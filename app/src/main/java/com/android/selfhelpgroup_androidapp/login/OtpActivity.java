package com.android.selfhelpgroup_androidapp.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.selfhelpgroup_androidapp.data.model.LoginRequest;
import com.android.selfhelpgroup_androidapp.data.model.LoginResponse;
import com.android.selfhelpgroup_androidapp.firebase_services.FirebaseToken;
import com.android.selfhelpgroup_androidapp.home.ui.HomeActivity;
import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.model.OtpRequest;
import com.android.selfhelpgroup_androidapp.data.model.OtpResponse;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;
import com.android.selfhelpgroup_androidapp.util.NetworkUtil;
import com.android.selfhelpgroup_androidapp.util.SessionManager;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OtpActivity extends AppCompatActivity {

    private EditText otp;
    private Button submitotp;
    private TextView resendotp;
    private SessionManager sessionManager;
    ProgressDialog pd;
    private TextView text2;

    @Inject
    Retrofit retrofit;

    @Inject
    ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_screen);

        init();

        ((BaseApplication)getApplication()).getNetworkComponent().inject(this);

        submitotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkUtil.isNetworkConnected(OtpActivity.this)){
                    pd.show();
                    login();
                }else{
                    Toast.makeText(OtpActivity.this, "Internet not connected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkUtil.isNetworkConnected(OtpActivity.this)){
                    pd.show();
                    resend();
                }else{
                    Toast.makeText(OtpActivity.this,"Internet not connected",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resend() {

        if(serviceApi==null) serviceApi=retrofit.create(ServiceApi.class);

        LoginRequest loginRequest=new LoginRequest(sessionManager.getContact());
        Call<LoginResponse> call=serviceApi.getLoginResponse(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                pd.dismiss();
                if(!response.isSuccessful())
                    Toast.makeText(OtpActivity.this, "can't send otp", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(OtpActivity.this, "can't send otp", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login() {
        String sotp=otp.getText().toString();
        OtpRequest otpRequest=new OtpRequest();
        otpRequest.setOtp(sotp);
        otpRequest.setShgId(sessionManager.getShgId());
        otpRequest.setDeviceToken(new FirebaseToken().getFirebaseToken());

        if(serviceApi==null) serviceApi= retrofit.create(ServiceApi.class);
        Call<OtpResponse> call=serviceApi.getOtpResponse(otpRequest);

        call.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                if(response.isSuccessful()){
                    OtpResponse otpResponse=response.body();
                    sessionManager.saveToken(otpResponse.getToken());
                    startActivity(new Intent(OtpActivity.this, HomeActivity.class));
                    finish();
                }else{
                    Toast.makeText(OtpActivity.this, "incorrect otp", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                Toast.makeText(OtpActivity.this, "incorrect otp", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    private void init(){
        text2=findViewById(R.id.text2);
        otp=findViewById(R.id.otp);
        submitotp=findViewById(R.id.submitbtn);
        resendotp=findViewById(R.id.resend);
        sessionManager=new SessionManager(OtpActivity.this);
        pd=new ProgressDialog(OtpActivity.this);

        text2.setText(getResources().getString(R.string.otp_sent_1)+" "+sessionManager.getContact()+" "+getResources().getString(R.string.otp_sent_2));
    }
}
