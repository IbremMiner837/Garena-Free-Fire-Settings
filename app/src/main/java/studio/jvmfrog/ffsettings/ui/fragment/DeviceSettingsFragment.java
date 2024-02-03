package studio.jvmfrog.ffsettings.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import studio.jvmfrog.ffsettings.R;
import studio.jvmfrog.ffsettings.databinding.FragmentDeviceSettingsBinding;
import studio.jvmfrog.ffsettings.utils.OtherUtils;

public class DeviceSettingsFragment extends Fragment {

    private FragmentDeviceSettingsBinding binding;
    private Float dpi = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeviceSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle finalBundle = new Bundle();
        finalBundle.putAll(getArguments());

        if (finalBundle.getFloat("dpi") == 0) {
            binding.textViewDPI.setVisibility(View.GONE);
        } else {
            dpi = finalBundle.getFloat("dpi");
            binding.textViewDPI.setText(getString(R.string.dpi) + ":" + " " + (int) finalBundle.getFloat("dpi"));
        }


        binding.reviewSlider.setValue((float) finalBundle.getFloat("review"));
        binding.collimatorSlider.setValue((float) finalBundle.getFloat("collimator"));
        binding.x2ScopeSlider.setValue((float) finalBundle.getFloat("x2_scope"));
        binding.x4ScopeSlider.setValue((float) finalBundle.getFloat("x4_scope"));
        binding.sniperScope.setValue((float) finalBundle.getFloat("sniper_scope"));
        binding.freeLookSlider.setValue((float) finalBundle.getFloat("free_review"));
        binding.fireButtonSlider.setValue((float) finalBundle.getFloat("fire_button"));

        if (finalBundle.getString("settings_source_url") == null || finalBundle.getString("settings_source_url").equals("null") || finalBundle.getString("settings_source_url").equals(""))
            binding.sourceButton.setVisibility(View.GONE);

        binding.sourceButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalBundle.getString("settings_source_url")));
            requireActivity().startActivity(intent);
        });

        binding.copyButton.setOnClickListener(view1 -> {
            try {
                new OtherUtils(getActivity()).copyTextToClipboard(getString(R.string.dpi) + ":" + " " + dpi + "\n" +
                                getString(R.string.review) + ":" + " " + (int) finalBundle.getFloat("review") + "\n" +
                                getString(R.string.collimator) + ":" + " " + (int) finalBundle.getFloat("collimator") + "\n" +
                                getString(R.string.x2_scope) + ":" + " " + (int) finalBundle.getFloat("x2_scope") + "\n" +
                                getString(R.string.x4_scope) + ":" + " " + (int) finalBundle.getFloat("x4_scope") + "\n" +
                                getString(R.string.sniper_scope) + ":" + " " + (int) finalBundle.getFloat("sniper_scope") + "\n" +
                                getString(R.string.free_review) + ":" + " " + (int) finalBundle.getFloat("free_review") + "\n" +
                                getString(R.string.fire_button) + ":" + " " + (int) finalBundle.getFloat("fire_button") + "\n" +
                                getString(R.string.source) + " " + finalBundle.getString("settings_source_url"));
                Toast.makeText(getActivity(), "Скопировано", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Ошибка: " + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}