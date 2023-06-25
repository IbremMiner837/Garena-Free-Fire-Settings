package studio.jvmfrog.ffsettings.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import studio.jvmfrog.ffsettings.BuildConfig;

import studio.jvmfrog.ffsettings.R;
import studio.jvmfrog.ffsettings.databinding.FragmentAboutAppBinding;
import studio.jvmfrog.ffsettings.utils.CustomTabUtil;
import studio.jvmfrog.ffsettings.utils.BugReportHelper;
import studio.jvmfrog.ffsettings.utils.OtherUtils;

public class AboutAppFragment extends Fragment {

    private FragmentAboutAppBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutAppBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.appVersionBtn.setText(getString(R.string.version) + ": " + BuildConfig.VERSION_NAME + "(" + BuildConfig.VERSION_CODE + ")");
        binding.translateAppBtn.setOnClickListener(view1 -> new CustomTabUtil().OpenCustomTab(getActivity(), getString(R.string.crowdin), R.color.md_theme_light_onSecondary));
        binding.donateBtn.setOnClickListener(v -> new CustomTabUtil().OpenCustomTab(getActivity(), "https://www.donationalerts.com/r/ibremminer837", R.color.md_theme_light_onSecondary));
        binding.sourceCodeBtn.setOnClickListener(v -> new CustomTabUtil().OpenCustomTab(getActivity(), getString(R.string.source_code_url), R.color.md_theme_light_onSecondary));
        binding.ibragimBtn.setOnClickListener(v -> new CustomTabUtil().OpenCustomTab(getActivity(), getString(R.string.ibragim_url), R.color.md_theme_light_onSecondary));
        binding.mailBtn.setOnClickListener(v -> BugReportHelper.sendEmail(getActivity()));
        binding.rateBtn.setOnClickListener(v -> new OtherUtils(getActivity()).reviewAppInGooglePlay());
        binding.vkGroupBtn.setOnClickListener(v -> new CustomTabUtil().OpenCustomTab(getActivity(), getString(R.string.JVMFrog), R.color.md_theme_light_onSecondary));
        binding.telegramBtn.setOnClickListener(v -> new CustomTabUtil().OpenCustomTab(getActivity(), "https://t.me/freefiresettingsapp", R.color.md_theme_light_onSecondary));
        binding.otherAppsBtn.setOnClickListener(view1 -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:JVMFrog")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.GOOGLE_PLAY))));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}