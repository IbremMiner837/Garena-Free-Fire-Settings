package com.jvmfrogsquad.ffsettings.ads;

import android.app.Activity;
import android.content.Context;

import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm.OnConsentFormDismissedListener;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentInformation.PrivacyOptionsRequirementStatus;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;

public final class UserMessagePlatformManager {
    private static UserMessagePlatformManager instance;
    private final ConsentInformation consentInformation;

    /** Private constructor */
    private UserMessagePlatformManager(Context context) {
        this.consentInformation = UserMessagingPlatform.getConsentInformation(context);
    }

    /** Public constructor */
    public static UserMessagePlatformManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserMessagePlatformManager(context);
        }

        return instance;
    }

    /** Interface definition for a callback to be invoked when consent gathering is complete. */
    public interface OnConsentGatheringCompleteListener {
        void consentGatheringComplete(FormError error);
    }

    /** Helper variable to determine if the app can request ads. */
    public boolean canRequestAds() {
        return consentInformation.canRequestAds();
    }

    /** Helper variable to determine if the privacy options form is required. */
    public boolean isPrivacyOptionsRequired() {
        return consentInformation.getPrivacyOptionsRequirementStatus() == PrivacyOptionsRequirementStatus.REQUIRED;
    }

    /**
     * Helper method to call the UMP SDK methods to request consent information and load/present a
     * consent form if necessary.
     */
    public void gatherConsent(Activity activity, OnConsentGatheringCompleteListener onConsentGatheringCompleteListener) {

        ConsentRequestParameters consentRequestParameters = new ConsentRequestParameters.Builder()
                .setAdMobAppId("ca-app-pub-3943668970239539~3707627327")
                .setTagForUnderAgeOfConsent(false)
                .build();

        // For testing purposes, you can force a DebugGeography of EEA or NOT_EEA.
        ConsentDebugSettings debugSettings = new ConsentDebugSettings.Builder(activity)
                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
                .addTestDeviceHashedId("TEST-DEVICE-HASHED-ID")
                .build();

        // Requesting an update to consent information should be called on every app launch.
        consentInformation.requestConsentInfoUpdate(activity, consentRequestParameters,
                () -> UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                        activity,
                        formError -> {
                            // Consent has been gathered.
                            onConsentGatheringCompleteListener.consentGatheringComplete(formError);
                        }),
                requestConsentError -> onConsentGatheringCompleteListener.consentGatheringComplete(requestConsentError)
        );
    }

    /** Helper method to call the UMP SDK method to present the privacy options form. */
    public void showPrivacyOptionsForm(Activity activity, OnConsentFormDismissedListener onConsentFormDismissedListener) {
        UserMessagingPlatform.showPrivacyOptionsForm(activity, onConsentFormDismissedListener);
    }
}