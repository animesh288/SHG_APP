package com.android.selfhelpgroup_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.selfhelpgroup_androidapp.data.model.CompletedOrderResponse;
import com.android.selfhelpgroup_androidapp.data.model.Profile;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;
import com.android.selfhelpgroup_androidapp.util.NetworkUtil;
import com.android.selfhelpgroup_androidapp.util.SessionManager;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @Inject
    ServiceApi serviceApi;

    TextView name,contact,location;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ((BaseApplication)getApplication()).getNetworkComponent().inject(this);

        init();
        createCall();

    }

    private void createCall() {
        {
            pd=new ProgressDialog(this);
            pd.setMessage("loading...");
            pd.setCancelable(false);
            pd.show();
            if(NetworkUtil.isNetworkConnected(this)){
                Call<Profile> call=serviceApi.getProfile("Bearer "+ new SessionManager(this).getToken());
                call.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if(response.code()==401) new SessionManager(ProfileActivity.this).logoutUser();
                        else if(response.isSuccessful()){
                            name.setText("नाम : "+response.body().getName());
                            contact.setText("फ़ोन नंबर : "+response.body().getContact());
                            location.setText("स्थान : "+response.body().getLocation());
                            pd.dismiss();
                        }else{
                            try {
                                Log.i("animesh",response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        Log.i("animesh",t.getMessage());
                        pd.dismiss();
                    }
                });
            }else{
                Toast.makeText(this, R.string.check_internet, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        }
    }

    private void init() {
        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        location=findViewById(R.id.location);
        pd=new ProgressDialog(ProfileActivity.this);
    }
}