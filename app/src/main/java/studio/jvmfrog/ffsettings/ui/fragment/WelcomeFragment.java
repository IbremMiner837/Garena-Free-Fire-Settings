package studio.jvmfrog.ffsettings.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import studio.jvmfrog.ffsettings.R;
import studio.jvmfrog.ffsettings.databinding.FragmentWelcomeBinding;
import studio.jvmfrog.ffsettings.ui.MainActivity;
import studio.jvmfrog.ffsettings.utils.OtherUtils;
import studio.jvmfrog.ffsettings.utils.SharedPreferencesUtils;

public class WelcomeFragment extends Fragment {
    private FragmentWelcomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String message = getString(R.string.privacy_policy_message);
        SpannableString spannableString = new SpannableString(message);

        int termsStart = message.indexOf("Terms of Service");
        int termsEnd = termsStart + "Terms of Service".length();

        int privacyPolicyStart = message.indexOf("Privacy Policy");
        int privacyPolicyEnd = privacyPolicyStart + "Privacy Policy".length();

        ClickableSpan termsClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                new MaterialAlertDialogBuilder(requireContext())
                        .setIcon(R.drawable.ic_round_gpp_maybe_24px)
                        .setTitle(R.string.privacy_policy)
                        .setMessage(Html.fromHtml(new OtherUtils(requireContext()).readFileFromAssets("terms_condition.html"), Html.FROM_HTML_MODE_LEGACY))
                        .setPositiveButton("OK", null)
                        .create().show();
            }
        };

        ClickableSpan privacyPolicyClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                new MaterialAlertDialogBuilder(requireContext())
                        .setIcon(R.drawable.ic_round_gpp_maybe_24px)
                        .setTitle(R.string.privacy_policy)
                        .setMessage(Html.fromHtml(new OtherUtils(requireContext()).readFileFromAssets("privacy_policy.html"), Html.FROM_HTML_MODE_LEGACY))
                        .setPositiveButton("OK", null)
                        .create().show();
            }
        };

        spannableString.setSpan(termsClickableSpan, termsStart, termsEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(privacyPolicyClickableSpan, privacyPolicyStart, privacyPolicyEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.termsUseMessage.setText(spannableString);
        binding.termsUseMessage.setMovementMethod(LinkMovementMethod.getInstance());

        binding.startBtn.setOnClickListener(v -> {
            SharedPreferencesUtils.writeBoolean(requireActivity(), "isFirstOpen", true);
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(intent);
            requireActivity().finish();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}