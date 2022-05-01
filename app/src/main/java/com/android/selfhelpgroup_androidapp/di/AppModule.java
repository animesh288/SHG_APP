package com.android.selfhelpgroup_androidapp.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application mApplication){
        this.mApplication=mApplication;
    }

    @Provides
    @Singleton
    public Application provideApplicationContext(){
        return mApplication;
    }
}
