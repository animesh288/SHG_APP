package com.android.selfhelpgroup_androidapp.util;

import android.app.Application;

import com.android.selfhelpgroup_androidapp.di.AppComponent;
import com.android.selfhelpgroup_androidapp.di.AppModule;
import com.android.selfhelpgroup_androidapp.di.DaggerAppComponent;
import com.android.selfhelpgroup_androidapp.di.NetworkModule;

public class BaseApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent= DaggerAppComponent.builder().appModule(new AppModule(this)).networkModule(new NetworkModule()).build();
    }

    public AppComponent getNetworkComponent(){
        return appComponent;
    }
}
