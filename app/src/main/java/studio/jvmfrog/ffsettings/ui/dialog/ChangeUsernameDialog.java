package studio.jvmfrog.ffsettings.ui.dialog;

import android.app.Activity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import studio.jvmfrog.ffsettings.R;
import studio.jvmfrog.ffsettings.utils.SharedPreferencesUtils;

public class ChangeUsernameDialog {

    public static void showDialog(Activity activity) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(new ContextThemeWrapper(activity, R.style.Theme_FFSettings_MaterialAlertDialog));
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.change_user_name_layout, null);

        builder.setTitle(R.string.enter_user_name);
        builder.setIcon(R.drawable.ic_outline_account_circle_24);
        builder.setView(dialogView);

        TextInputEditText usernameInput = dialogView.findViewById(R.id.userNameInput);
        TextView username = activity.findViewById(R.id.welcome_and_user_name);

        builder.setPositiveButton(R.string.save, (dialog, which) -> {
            SharedPreferencesUtils.saveString(activity, "user_name", usernameInput.getText().toString());
            username.setText(activity.getString(R.string.welcome) + "," + "\n" + usernameInput.getText().toString() + "!");
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
