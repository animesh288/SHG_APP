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

import com.android.selfhelpgroup_androidapp.home.ui.HomeActivity;
import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.modal.OtpRequest;
import com.android.selfhelpgroup_androidapp.data.modal.OtpResponse;
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

    @Inject
    Retrofit retrofit;

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
                    pd.dismiss();
                }else{
                    Toast.makeText(OtpActivity.this, "Internet not connected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login() {
        String sotp=otp.getText().toString();
        OtpRequest otpRequest=new OtpRequest();
        otpRequest.setOtp(sotp);
        otpRequest.setShgId(sessionManager.getShgId());

        ServiceApi serviceApi= retrofit.create(ServiceApi.class);
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
                }
            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                Toast.makeText(OtpActivity.this, "incorrect otp", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        otp=findViewById(R.id.otp);
        submitotp=findViewById(R.id.submitbtn);
        resendotp=findViewById(R.id.resend);
        sessionManager=new SessionManager(OtpActivity.this);
        pd=new ProgressDialog(OtpActivity.this);
    }
}
