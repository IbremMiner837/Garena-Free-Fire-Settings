package com.jvmfrog.ffsettings.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.databinding.FragmentSettingsBinding;
import com.jvmfrog.ffsettings.utils.SharedPreferencesUtils;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container,false);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.appThemeRadioGroup.check(SharedPreferencesUtils.getInteger(requireContext(), "checkedButton", R.id.setFollowSystemTheme));
        binding.dynamicColorsSwitch.setEnabled(DynamicColors.isDynamicColorAvailable());
        binding.dynamicColorsSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "useDynamicColors"));
        int[] setNightModeDescription = {R.string.auto_theme_description, R.string.system_theme_description, R.string.light_theme_description, R.string.night_theme_description};
        binding.themeDescription.setText(setNightModeDescription[SharedPreferencesUtils.getInteger(requireContext(), "nightMode", 1)]);

        binding.appThemeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.setAutoTheme:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkedButton", R.id.setAutoTheme);
                    SharedPreferencesUtils.saveInteger(requireContext(), "nightMode", 0);
                    requireActivity().recreate();
                    break;
                case R.id.setFollowSystemTheme:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkedButton", R.id.setFollowSystemTheme);
                    SharedPreferencesUtils.saveInteger(requireContext(), "nightMode", 1);
                    requireActivity().recreate();
                    break;
                case R.id.setLightTheme:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkedButton", R.id.setLightTheme);
                    SharedPreferencesUtils.saveInteger(requireContext(), "nightMode", 2);
                    requireActivity().recreate();
                    break;
                case R.id.setNightTheme:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkedButton", R.id.setNightTheme);
                    SharedPreferencesUtils.saveInteger(requireContext(), "nightMode", 3);
                    requireActivity().recreate();
                    break;
            }
        });

        binding.dynamicColorsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferencesUtils.saveBoolean(requireContext(), "useDynamicColors", isChecked);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}