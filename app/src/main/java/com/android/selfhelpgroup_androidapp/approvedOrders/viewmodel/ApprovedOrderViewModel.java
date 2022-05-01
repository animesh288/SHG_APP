package com.android.selfhelpgroup_androidapp.approvedOrders.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.selfhelpgroup_androidapp.data.modal.ApprovedOrder;
import com.android.selfhelpgroup_androidapp.data.modal.ApprovedOrderResponse;
import com.android.selfhelpgroup_androidapp.data.modal.Order;
import com.android.selfhelpgroup_androidapp.data.modal.OrderResponse;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;
import com.android.selfhelpgroup_androidapp.util.NetworkUtil;
import com.android.selfhelpgroup_androidapp.util.SessionManager;
import com.android.selfhelpgroup_androidapp.util.Token;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApprovedOrderViewModel extends AndroidViewModel {

    private MutableLiveData<List<ApprovedOrder>> liveData;

    @Inject
    Retrofit retrofit;

    ServiceApi serviceApi;

    public ApprovedOrderViewModel(@NonNull Application application) {
        super(application);
        liveData=new MutableLiveData<>();
        ((BaseApplication)getApplication()).getNetworkComponent().inject(ApprovedOrderViewModel.this);
    }
    public MutableLiveData<List<ApprovedOrder>> getLiveData(Context context){
        if(serviceApi==null){
            serviceApi=retrofit.create(ServiceApi.class);
            createCall(context);
        }
        return liveData;
    }
    private void createCall(Context context) {
        if(NetworkUtil.isNetworkConnected(context)){
            Call<ApprovedOrderResponse> call=serviceApi.getApprovedOrders("Bearer "+ new SessionManager(context).getToken());
            call.enqueue(new Callback<ApprovedOrderResponse>() {
                @Override
                public void onResponse(Call<ApprovedOrderResponse> call, Response<ApprovedOrderResponse> response) {
                    if(response.isSuccessful()){
                        liveData.postValue(response.body().getProducts());
                    }else{
                        try {
                            Log.i("animesh",response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApprovedOrderResponse> call, Throwable t) {
                    Log.i("animesh",t.getMessage());
                }
            });
        }else{
            Toast.makeText(context, "No internet", Toast.LENGTH_SHORT).show();
        }
    }
}
