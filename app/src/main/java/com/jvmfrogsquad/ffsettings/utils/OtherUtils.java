package com.jvmfrogsquad.ffsettings.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

public class OtherUtils {
    private Context context;

    public OtherUtils(Context context) {
        this.context = context;
    }

    public void copyTextToClipboard(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Copied Text", text);
        clipboardManager.setPrimaryClip(clipData);
    }

    public void reviewAppInGooglePlay() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(android.net.Uri.parse("market://details?id=" + context.getPackageName()));
        context.startActivity(intent);
    }
}
