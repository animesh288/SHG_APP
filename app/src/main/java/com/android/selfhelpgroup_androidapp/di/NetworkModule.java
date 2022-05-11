package com.android.selfhelpgroup_androidapp.di;

import com.android.selfhelpgroup_androidapp.network.Constants;
import com.android.selfhelpgroup_androidapp.network.ServiceApi;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public ServiceApi provideServiceApi(){
        return provideRetrofit().create(ServiceApi.class);
    }
}
