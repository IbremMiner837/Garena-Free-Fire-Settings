package com.jvmfrogsquad.ffsettings.utils;

import android.app.Activity;
import android.util.Log;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.jvmfrogsquad.ffsettings.R;

public class RemoteConfigManager {
    public static RemoteConfigManager instance = null;

    private static final String TAG = "FIREBASE REMOTE CONFIG";
    private static final long CACHE_EXPIRATION_SECONDS = 3600;
    private static final Integer DEFAULTS_RESOURCE_NAME = R.xml.default_remote_configs;

    private static final String ADMOB_APP_ID_KEY = "admob_app_id";
    private static final String APP_OPEN_AD_ID_KEY = "app_open_ad_id";
    private static final String BANNER_AD_ID_KEY = "banner_ad_id";
    public static RemoteConfigManager.ConfigFetchCallback ConfigFetchCallback;

    private final FirebaseRemoteConfig mFirebaseRemoteConfig;

    public static RemoteConfigManager getInstance() {
        if (instance == null)
            instance = new RemoteConfigManager();
        return instance;
    }

    public RemoteConfigManager() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(CACHE_EXPIRATION_SECONDS)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(DEFAULTS_RESOURCE_NAME);
        instance = this;
    }

    public void fetchConfig(Activity activity, final ConfigFetchCallback callback) {
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Fetch succeeded");
                        if (mFirebaseRemoteConfig.getInfo().getFetchTimeMillis() == 0) {
                            Log.d(TAG, "Remote config is empty, using defaults");
                            //callback.onFetchComplete();
                        } else {
                            //callback.onFetchComplete();
                        }
                    } else {
                        Log.e(TAG, "Fetch failed");
                        Log.d(TAG, "Using defaults due to fetch failure");
                        //callback.onFetchFailed();
                    }
                });
    }

    public String getString(String key) {
        return mFirebaseRemoteConfig.getString(key);
    }

    public boolean getBoolean(String key) {
        return mFirebaseRemoteConfig.getBoolean(key);
    }

    public String getAdMobAppId() {
        return mFirebaseRemoteConfig.getString(ADMOB_APP_ID_KEY);
    }

    public String getAppOpenAdId() {
        return mFirebaseRemoteConfig.getString(APP_OPEN_AD_ID_KEY);
    }

    public String getBannerAdId() {
        return mFirebaseRemoteConfig.getString(BANNER_AD_ID_KEY);
    }

    public interface ConfigFetchCallback {
        void onFetchComplete();
        void onFetchFailed();
    }
}