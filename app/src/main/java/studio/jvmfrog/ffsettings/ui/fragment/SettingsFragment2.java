package studio.jvmfrog.ffsettings.ui.fragment;

import android.os.Bundle;
import studio.jvmfrog.ffsettings.R;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment2 extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}