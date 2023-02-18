package com.jvmfrog.ffsettings.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.model.SensitivityModel;
import com.jvmfrog.ffsettings.utils.UnityAdsManager;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

import java.util.List;

public class DevicesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Fragment fragment;
    private final List<SensitivityModel> models;
    private static final int VIEW_TYPE_DEFAULT = 0;
    private static final int VIEW_TYPE_BANNER = 1;

    public DevicesAdapter(Fragment fragment, List<SensitivityModel> models) {
        this.fragment = fragment;
        this.models = models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_BANNER:
                View default_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item, parent, false);
                return new BannerViewHolder(default_view);
            case VIEW_TYPE_DEFAULT:
                View banner_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_name_item, parent, false);
                return new DeviceNameViewHolder(banner_view);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SensitivityModel model = models.get(position);
        if (model == null)
            return;
        else if (viewHolder.getItemViewType() == VIEW_TYPE_BANNER) {
            BannerViewHolder holder = (BannerViewHolder) viewHolder;
            BannerView banner = new BannerView(fragment.requireActivity(), "Banner_Android", new UnityBannerSize(320, 50));
            banner.setGravity(RelativeLayout.CENTER_IN_PARENT);
            banner.load();
            banner.setListener(new BannerView.Listener() {
                @Override
                public void onBannerLoaded(BannerView bannerAdView) {
                    super.onBannerLoaded(bannerAdView);
                    holder.bannerAdContainer.setVisibility(View.VISIBLE);
                }
            });
            holder.bannerAdContainer.addView(banner);
        } else if (viewHolder.getItemViewType() == VIEW_TYPE_DEFAULT) {
            DeviceNameViewHolder holder = (DeviceNameViewHolder) viewHolder;
            StringBuilder deviceName = new StringBuilder(model.getManufacturerName() + " " + model.getDeviceName());
            holder.device_name.setText(deviceName);
            holder.itemView.setOnClickListener(v -> {
                //UnityAdsManager.instance.showInterstitialAd();
                Bundle finalBundle = new Bundle();
                finalBundle.putFloat("review", models.get(position).getReview());
                finalBundle.putFloat("collimator", models.get(position).getCollimator());
                finalBundle.putFloat("x2_scope", models.get(position).getX2Scope());
                finalBundle.putFloat("x4_scope", models.get(position).getX4Scope());
                finalBundle.putFloat("sniper_scope", models.get(position).getSniperScope());
                finalBundle.putFloat("free_review", models.get(position).getFreeReview());
                finalBundle.putFloat("dpi", models.get(position).getDpi());
                finalBundle.putFloat("fire_button", models.get(position).getFireButton());
                finalBundle.putString("settings_source_url", models.get(position).getSettingsSourceUrl());
                NavController navController = Navigation.findNavController(fragment.requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_devicesFragment_to_deviceSettingsFragment, finalBundle);
            });
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        int itemCount = getItemCount();
        if (itemCount < 8) {
            // Если меньше 8 элементов, то добавляем баннер в конец после элемента VIEW_TYPE_DEFAULT
            if (position == itemCount - 1) {
                return VIEW_TYPE_BANNER;
            } else {
                return VIEW_TYPE_DEFAULT;
            }
        } else if (position == 0) {
            // Первый элемент всегда типа VIEW_TYPE_DEFAULT
            return VIEW_TYPE_DEFAULT;
        } else if ((position + 1) % 8 == 0 && position != itemCount - 1) {
            // Добавляем баннер через каждые 8 элементов, кроме последнего элемента
            return VIEW_TYPE_BANNER;
        } else {
            // Остальные элементы типа VIEW_TYPE_DEFAULT
            return VIEW_TYPE_DEFAULT;
        }
    }

    public static class DeviceNameViewHolder extends RecyclerView.ViewHolder {
        TextView device_name;
        public DeviceNameViewHolder(@NonNull View itemView) {
            super(itemView);
            device_name = itemView.findViewById(R.id.categories);
        }
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        FrameLayout bannerAdContainer;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerAdContainer = itemView.findViewById(R.id.banner_ad_container);
        }
    }
}
