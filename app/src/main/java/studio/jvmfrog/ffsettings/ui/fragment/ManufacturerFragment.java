package studio.jvmfrog.ffsettings.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.databinding.FragmentManufacturerBinding;

import studio.jvmfrog.ffsettings.adapter.ManufacturerAdapter;
import studio.jvmfrog.ffsettings.ui.dialog.ChangeUsernameDialog;
import studio.jvmfrog.ffsettings.utils.CustomTabUtil;
import studio.jvmfrog.ffsettings.utils.ManufacturerManager;
import studio.jvmfrog.ffsettings.utils.SharedPreferencesUtils;

public class ManufacturerFragment extends Fragment {
    private FragmentManufacturerBinding binding;
    private ManufacturerManager manager;
    private LinearProgressIndicator indicator;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManufacturerBinding.inflate(inflater, container, false);
        indicator = requireActivity().findViewById(R.id.progressIndicator);
        manager = new ManufacturerManager();
        manager.updateAdapterData(requireActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String name = SharedPreferencesUtils.getString(requireActivity(), "user_name");
        StringBuilder userName = new StringBuilder(getString(R.string.welcome) + "," + "\n" + name + "!");
        StringBuilder defaultUserName = new StringBuilder(getString(R.string.welcome) + "," + "\n" + getString(R.string.user_name) + "!");

        binding.welcomeAndUserName.setText(name.equals("") ? defaultUserName : userName);

        manager.isRequestFinished().observe(getViewLifecycleOwner(), aBoolean -> {
            if (!aBoolean) {
                indicator.setVisibility(View.VISIBLE);
                binding.shimmerLayout.startShimmer();
                binding.shimmerLayout.setVisibility(View.VISIBLE);
                binding.recview.setVisibility(View.GONE);
            } else {
                indicator.setVisibility(View.GONE);
                binding.shimmerLayout.stopShimmer();
                binding.shimmerLayout.setVisibility(View.GONE);
                binding.recview.setVisibility(View.VISIBLE);
                binding.recview.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
                binding.recview.setAdapter(new ManufacturerAdapter(this, manager.getManufacturersSet()));
            }
        });

        binding.setUserNameBtn.setOnClickListener(view1 -> ChangeUsernameDialog.showDialog(getActivity()));
        binding.googleFormBtn.setOnClickListener(view1 -> new CustomTabUtil().OpenCustomTab(getActivity(), "https://t.me/freefiresettingsapp", R.color.md_theme_light_onSecondary));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}