package com.android.selfhelpgroup_androidapp.orders.viewmodel;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

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

public class OrderActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Order>> liveData;

    @Inject
    Retrofit retrofit;

    ServiceApi serviceApi;

    public OrderActivityViewModel(@NonNull Application application) {
        super(application);
        liveData=new MutableLiveData<>();
        ((BaseApplication)getApplication()).getNetworkComponent().inject(OrderActivityViewModel.this);
    }

    public MutableLiveData<List<Order>> getLiveData(Context context){
        if(serviceApi==null){
            serviceApi=retrofit.create(ServiceApi.class);
            createCall(context);
        }
        return liveData;
    }

    private void createCall(Context context) {
        if(NetworkUtil.isNetworkConnected(context)){
            Call<OrderResponse> call=serviceApi.getOrders("Bearer "+ new SessionManager(context).getToken());
            call.enqueue(new Callback<OrderResponse>() {
                @Override
                public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                    if(response.code()==401){
                        new SessionManager(context).deleteUser();
                    }
                    if(response.isSuccessful()){
                        liveData.postValue(response.body().getOrders());
                    }else{
                        try {
                            Log.i("animesh",response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<OrderResponse> call, Throwable t) {
                    Log.i("animesh",t.getMessage());
                }
            });
        }
    }
}
