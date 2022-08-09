package com.jvmfrog.ffsettings.utils;

import android.app.UiModeManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

import com.google.android.gms.common.util.SharedPreferencesUtils;

public class OtherUtils {

    public void copyTextToClipboard(Context context, String text) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setText(text);
        } else {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Copied Text", text);
            clipboardManager.setPrimaryClip(clipData);
        }
    }
}
