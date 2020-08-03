package com.amrita.walkover_test.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private static final String KEY_PREF_NAME = "walkover";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_IS_LOGGED_IN = "isloggedin";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private int PRIVATE_MODE = 0;

    @SuppressLint("CommitPrefEdits")
    public SharedPrefs(Context context) {
        preferences = context.getSharedPreferences(KEY_PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public String getUserName() {
        return preferences.getString(KEY_USER_NAME, "user");
    }

    public boolean getIsLoggedIn() {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setUserName(String userName){
        editor.putString(KEY_USER_NAME, userName);
        editor.commit();
    }

    public void setIsLoggedIn(boolean value){
        editor.putBoolean(KEY_IS_LOGGED_IN, value);
        editor.commit();
    }

}
