package com.jvmfrogsquad.ffsettings.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import com.jvmfrogsquad.ffsettings.BuildConfig;
import com.jvmfrogsquad.ffsettings.R;

public class AboutAdsDialog {
    private Context context;

    public AboutAdsDialog(Context context) {
        this.context = context;
    }

    public MaterialAlertDialogBuilder materialAlertDialogBuilder() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);

        builder.setTitle("About Ads.");
        builder.setMessage("Реклама помогает" + " " + context.getString(R.string.app_name) + " " + "оставаться бесплатным."
        + "\nНо, Вы всегда можете скачать Pro версию, в которой нет рекламы.");
        builder.setPositiveButton("Понятно", (dialog, which) -> {dialog.dismiss();});
        builder.setNeutralButton("Pro версия", (dialog, which) -> {
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)));
            } catch (android.content.ActivityNotFoundException anfe) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)));
            }
        });

        return builder;
    }
}
