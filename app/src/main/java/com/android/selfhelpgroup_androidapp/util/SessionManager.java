package com.android.selfhelpgroup_androidapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    Context context;
    private static String USER_TOKEN="user_token";
    private static String SHG_ID="shgId";
    private static String CONTACT="contact";
    private SharedPreferences sh;

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
}
