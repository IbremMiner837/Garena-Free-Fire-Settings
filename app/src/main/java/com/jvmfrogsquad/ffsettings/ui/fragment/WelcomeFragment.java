package com.jvmfrogsquad.ffsettings.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
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

import com.jvmfrogsquad.ffsettings.R;
import com.jvmfrogsquad.ffsettings.databinding.FragmentWelcomeBinding;
import com.jvmfrogsquad.ffsettings.ui.MainActivity;
import com.jvmfrogsquad.ffsettings.utils.SharedPreferencesUtils;

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
                // Обработка клика по "Terms of Service"
                // Добавьте свой код обработки события
            }
        };

        ClickableSpan privacyPolicyClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Обработка клика по "Privacy Policy"
                // Добавьте свой код обработки события
            }
        };

        spannableString.setSpan(termsClickableSpan, termsStart, termsEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(privacyPolicyClickableSpan, privacyPolicyStart, privacyPolicyEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Установка SpannableString в TextView
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