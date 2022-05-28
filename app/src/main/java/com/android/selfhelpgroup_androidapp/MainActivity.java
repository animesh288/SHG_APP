package com.android.selfhelpgroup_androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.android.selfhelpgroup_androidapp.home.ui.HomeActivity;
import com.android.selfhelpgroup_androidapp.login.LoginActivity;
import com.android.selfhelpgroup_androidapp.util.SessionManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i1=new Intent(MainActivity.this, LoginActivity.class);

                startActivity(i1);

                finish();
            }
        }, 1000);
    }
}
