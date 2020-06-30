package com.example.findacar.service;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionService {

    private static String TAG = "SessionManager";
    private static SessionService sInstance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;

    public static final String LOGGED_IN_PREF = "logged_in_status";
    public static final String EMAIL = "email";

    private SessionService(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        }
    }

    public static synchronized SessionService getInstance(Context context) {
        if (sInstance == null) sInstance = new SessionService(context);
        return sInstance;
    }

    public boolean exists(String key) {
        try {
            if (key != null && sharedPreferences != null && sharedPreferences.contains(key)) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void remove(String key) {
        try {
            if (sharedPreferences != null && key != null) {
                sharedPreferencesEditor = sharedPreferences.edit();
                if (sharedPreferences.contains(key)) {
                    sharedPreferencesEditor.remove(key);
                    sharedPreferencesEditor.apply();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBooleanValue(String key, boolean value) {
        try {
            if (sharedPreferences != null && key != null) {
                sharedPreferencesEditor = sharedPreferences.edit();
                if (sharedPreferences.contains(key)) sharedPreferencesEditor.remove(key);
                sharedPreferencesEditor.putBoolean(key, value);
                sharedPreferencesEditor.apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean getBooleanValue(String key) {
        try {
            return sharedPreferences.getBoolean(key, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertStringValue(String key, String value) {
        try {
            if (sharedPreferences != null && key != null) {
                sharedPreferencesEditor = sharedPreferences.edit();
                if (sharedPreferences.contains(key)) sharedPreferencesEditor.remove(key);
                sharedPreferencesEditor.putString(key, value);
                sharedPreferencesEditor.apply();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public String getStringValue(String key) {
        try {
            if (key != null && sharedPreferences != null) {
                return sharedPreferences.getString(key, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
