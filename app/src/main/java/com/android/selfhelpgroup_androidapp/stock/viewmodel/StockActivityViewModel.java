package com.android.selfhelpgroup_androidapp.stock.viewmodel;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

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

import java.io.IOException;
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

    @Inject
    ServiceApi serviceApi;

    ProgressDialog progressDialog;


    public StockActivityViewModel(@NonNull Application application) {
        super(application);

        liveData=new MutableLiveData<>();
        ((BaseApplication)getApplication()).getNetworkComponent().inject(this);
    }

    public MutableLiveData<List<Product>> getLiveData(Context context){
        createCall(context);
        return liveData;
    }

    private void createCall(Context context) {
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("loading...");
        progressDialog.show();
        if(NetworkUtil.isNetworkConnected(context)){
            Call<ProductResponse> call=serviceApi.getProducts("Bearer "+ new SessionManager(context).getToken());
            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    if(response.code()==401) new SessionManager(context).logoutUser();
                    else if (response.isSuccessful()){
                        if(response.body()!=null){
                            liveData.postValue(response.body().getProducts());
                            progressDialog.dismiss();
                        }
                    }else{
                        try {
                            Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }else{
            Toast.makeText(context, "no internet", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }
}
