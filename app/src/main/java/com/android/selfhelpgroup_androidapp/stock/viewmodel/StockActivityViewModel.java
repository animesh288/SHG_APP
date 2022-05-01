package com.android.selfhelpgroup_androidapp.stock.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.selfhelpgroup_androidapp.data.modal.Product;
import com.android.selfhelpgroup_androidapp.data.modal.ProductResponse;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;
import com.android.selfhelpgroup_androidapp.util.BaseApplication;
import com.android.selfhelpgroup_androidapp.util.NetworkUtil;
import com.android.selfhelpgroup_androidapp.util.SessionManager;
import com.android.selfhelpgroup_androidapp.util.Token;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StockActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Product>> liveData;

    @Inject
    Retrofit retrofit;

    ServiceApi serviceApi;


    public StockActivityViewModel(@NonNull Application application) {
        super(application);

        liveData=new MutableLiveData<>();
        ((BaseApplication)getApplication()).getNetworkComponent().inject(this);
    }

    public MutableLiveData<List<Product>> getLiveData(Context context){
        if(serviceApi==null){
            serviceApi=retrofit.create(ServiceApi.class);
            createCall(context);
        }
        return liveData;
    }

    private void createCall(Context context) {
        if(NetworkUtil.isNetworkConnected(context)){
            Call<ProductResponse> call=serviceApi.getProducts("Bearer "+ new SessionManager(context).getToken());
            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if (response.isSuccessful()){
                        if(response.body()!=null){
                            liveData.postValue(response.body().getProducts());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {

                }
            });
        }
    }
}
