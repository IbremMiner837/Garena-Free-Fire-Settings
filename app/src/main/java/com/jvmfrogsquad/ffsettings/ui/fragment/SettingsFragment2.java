package com.jvmfrogsquad.ffsettings.ui.fragment;

import android.os.Bundle;
import com.jvmfrogsquad.ffsettings.R;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment2 extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}