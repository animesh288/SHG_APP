package com.android.selfhelpgroup_androidapp.approvedOrders.viewmodel;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrder;
import com.android.selfhelpgroup_androidapp.data.model.ApprovedOrderResponse;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;
import com.android.selfhelpgroup_androidapp.util.NetworkUtil;
import com.android.selfhelpgroup_androidapp.util.SessionManager;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApprovedOrderViewModel extends AndroidViewModel {

    private MutableLiveData<List<ApprovedOrder>> liveData;
    ProgressDialog progressDialog;

    @Inject
    Retrofit retrofit;

    @Inject
    ServiceApi serviceApi;

    public ApprovedOrderViewModel(@NonNull Application application) {
        super(application);
        liveData=new MutableLiveData<>();
        ((BaseApplication)getApplication()).getNetworkComponent().inject(ApprovedOrderViewModel.this);
    }
    public MutableLiveData<List<ApprovedOrder>> getLiveData(Context context){
        createCall(context);
        return liveData;
    }
    private void createCall(Context context) {
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        if(NetworkUtil.isNetworkConnected(context)){
            Call<ApprovedOrderResponse> call=serviceApi.getApprovedOrders("Bearer "+ new SessionManager(context).getToken());
            call.enqueue(new Callback<ApprovedOrderResponse>() {
                @Override
                public void onResponse(Call<ApprovedOrderResponse> call, Response<ApprovedOrderResponse> response) {
                    if(response.code()==401) new SessionManager(context).logoutUser();
                    else if(response.isSuccessful()){
                        liveData.postValue(response.body().getProducts());
                        progressDialog.dismiss();
                    }else{
                        try {
                            Log.i("animesh",response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ApprovedOrderResponse> call, Throwable t) {
                    Log.i("animesh",t.getMessage());
                    progressDialog.dismiss();
                }
            });
        }else{
            Toast.makeText(context, R.string.check_internet, Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }
}
