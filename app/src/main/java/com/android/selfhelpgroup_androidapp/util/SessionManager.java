package com.android.selfhelpgroup_androidapp.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.android.selfhelpgroup_androidapp.R;
import com.android.selfhelpgroup_androidapp.login.LoginActivity;

public class SessionManager {
    Context context;
    private static String USER_TOKEN="user_token";
    private static String SHG_ID="shgId";
    private static String CONTACT="contact";
    private SharedPreferences sh;
    private AlertDialog.Builder builder;

    public SessionManager(Context context) {
        this.context = context;
        sh=context.getSharedPreferences("MyPref",Context.MODE_PRIVATE);
    }

    public void saveToken(String token){
        SharedPreferences.Editor editor= sh.edit();
        editor.putString(USER_TOKEN,token);
        editor.apply();
    }

    public String getToken(){
        return sh.getString(USER_TOKEN,null);
    }

    public void saveShgId(String id){
        SharedPreferences.Editor editor=sh.edit();
        editor.putString(SHG_ID,id);
        editor.apply();
    }

    public String getShgId(){
        return sh.getString(SHG_ID,null);
    }

    public void saveContact(String contact){
        SharedPreferences.Editor editor= sh.edit();
        editor.putString(CONTACT,contact);
        editor.apply();
    }

    public String getContact(){
        return sh.getString(CONTACT,null);
    }
    public void deleteUser(){
        SharedPreferences.Editor editor= sh.edit();
        editor.putString(SHG_ID,null);
        editor.putString(USER_TOKEN,null);
        editor.putString(CONTACT,null);
        editor.apply();
    }

    public void logoutUser(){
        builder=new AlertDialog.Builder(context);
        builder.setMessage(R.string.relogin_message);
        builder.setCancelable(false);
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUser();
                context.startActivity(new Intent(context,LoginActivity.class));
                ((Activity)context).finish();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
    
}
