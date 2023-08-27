package studio.jvmfrog.ffsettings;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import studio.jvmfrog.ffsettings.utils.SharedPreferencesUtils;

public class MyApplication extends Application {
    public static MyApplication instance = null;

    public static MyApplication getInstance() {
        if (instance == null)
            instance = new MyApplication();
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setNightMode();
    }

    public void setNightMode() {
        int nightMode = SharedPreferencesUtils.getInteger(this, "nightMode", 0);
        int[] mode = {AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, AppCompatDelegate.MODE_NIGHT_NO, AppCompatDelegate.MODE_NIGHT_YES};
        AppCompatDelegate.setDefaultNightMode(mode[nightMode]);
    }
}
