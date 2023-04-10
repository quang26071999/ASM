package com.example.asm.controller;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static final String PREFERENCE_NAME = "MyPreference";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_URL = "url";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static void setUsername(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public static String getUsername(Context context) {
        return getSharedPreferences(context).getString(KEY_USERNAME, "");
    }

    public static void setPassword(Context context, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public static String getPassword(Context context) {
        return getSharedPreferences(context).getString(KEY_PASSWORD, "");
    }

    public static void setName(Context context, String name) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public static String getName(Context context) {
        return getSharedPreferences(context).getString(KEY_NAME, "");
    }

    public static void setUrl(Context context, String url) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_URL, url);
        editor.apply();
    }

    public static String getUrl(Context context) {
        return getSharedPreferences(context).getString(KEY_URL, "");
    }

    public static void clearUserData(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_PASSWORD);
        editor.remove(KEY_NAME);
        editor.remove(KEY_URL);
        editor.apply();
    }
}

