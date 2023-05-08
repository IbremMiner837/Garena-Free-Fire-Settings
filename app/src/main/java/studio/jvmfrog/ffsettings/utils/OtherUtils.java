package studio.jvmfrog.ffsettings.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class OtherUtils {
    private Context context;

    public OtherUtils(Context context) {
        this.context = context;
    }

    public void copyTextToClipboard(String text) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setText(text);
        } else {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Copied Text", text);
            clipboardManager.setPrimaryClip(clipData);
        }
    }

    public void reviewAppInGooglePlay() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(android.net.Uri.parse("market://details?id=" + context.getPackageName()));
        context.startActivity(intent);
    }
}
