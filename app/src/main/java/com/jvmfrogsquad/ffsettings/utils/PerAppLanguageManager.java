package com.jvmfrogsquad.ffsettings.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import com.jvmfrogsquad.ffsettings.R;

public class PerAppLanguageManager {
    public static final String PREFERENCE_NAME = "shared_preference";
    public static final int PREFERENCE_MODE = Context.MODE_PRIVATE;
    public static final String FIRST_TIME_MIGRATION = "first_time_migration";
    public static final String SELECTED_LANGUAGE = "selected_language";
    public static final String STATUS_DONE = "status_done";
    int CHECKED_ITEM = 0;

    public AlertDialog firstLanguageChoice(Context context) {
        String[] option = {"System", "English", "Russian", "System", "English", "Russian", "System", "English", "Russian", "System", "English", "Russian"};
        String[] locale = {"uk", "en-US", "ru"};
        return new MaterialAlertDialogBuilder(context)
                .setIcon(R.drawable.baseline_language_24)
                .setTitle("Choose language")
                //.setMessage("Welcome, please choice your language")
                .setSingleChoiceItems(option, CHECKED_ITEM, (dialog, which) -> {
                    CHECKED_ITEM = which;
                })
                .setPositiveButton("Set", (dialog, which) -> {
                    setLanguage(locale[CHECKED_ITEM]);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    public void setLanguage(String language) {
        LocaleListCompat appLocale = LocaleListCompat.forLanguageTags(language);
        AppCompatDelegate.setApplicationLocales(appLocale);
    }
}
