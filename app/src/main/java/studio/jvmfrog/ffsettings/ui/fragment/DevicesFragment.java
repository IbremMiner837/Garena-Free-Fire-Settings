package studio.jvmfrog.ffsettings.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import studio.jvmfrog.ffsettings.R;
import studio.jvmfrog.ffsettings.databinding.FragmentDevicesBinding;

import studio.jvmfrog.ffsettings.adapter.DevicesAdapter;
import studio.jvmfrog.ffsettings.utils.SensitivitiesManager;

public class DevicesFragment extends Fragment {
    private FragmentDevicesBinding binding;
    private SensitivitiesManager manager;
    private LinearProgressIndicator indicator;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDevicesBinding.inflate(inflater, container, false);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        indicator = requireActivity().findViewById(R.id.progressIndicator);
        manager = new SensitivitiesManager();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle finalBundle = new Bundle();
        finalBundle.putAll(getArguments());
        manager.updateAdapterData(requireActivity(), finalBundle.getString("model"));

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
                binding.recview.setLayoutManager(new GridLayoutManager(requireActivity(), 1));
                binding.recview.setAdapter(new DevicesAdapter(this, manager.getSensitivitiesSet()));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}