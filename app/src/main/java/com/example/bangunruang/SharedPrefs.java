package com.example.bangunruang;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefs {
    private SharedPreferences preferences;

    private final String keyUserType = "User type";

    public SharedPrefs(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUserType(String type) {
        preferences.edit().putString(keyUserType, type).apply();
    }

    public String getKeyUserType() {
        return preferences.getString(keyUserType, "murid");
    }
}
