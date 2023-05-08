package studio.jvmfrog.ffsettings.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String key, Boolean defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        return prefs.getBoolean(key, defaultValue);
    }

    public static void saveInteger(Context context, String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInteger(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }

    public static int getInteger(Context context, String key, Integer defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        return prefs.getInt(key, defaultValue);
    }

    public static void saveString(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        return prefs.getString(key, defaultValue);
    }
}
