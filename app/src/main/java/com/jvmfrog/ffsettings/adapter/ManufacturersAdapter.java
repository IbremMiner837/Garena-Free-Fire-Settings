package com.jvmfrog.ffsettings.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.ui.fragment.DevicesFragment;
import com.jvmfrog.ffsettings.utils.FragmentUtils;
import com.jvmfrog.ffsettings.utils.NavigationUtils;
import com.jvmfrog.ffsettings.utils.SharedPreferencesUtils;

import java.util.ArrayList;

public class ManufacturersAdapter extends RecyclerView.Adapter<ManufacturersAdapter.holder> {

    private final ArrayList<String> arrayList;

    public ManufacturersAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ManufacturersAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, parent, false);

        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManufacturersAdapter.holder holder, int position) {
        holder.device_name.setText(arrayList.get(position));

        holder.itemView.setOnClickListener(view -> {
            Bundle finalBundle = new Bundle();
            String[] manufacturers = {
                    "samsung", "iphone", "xiaomi", "redmi", "oppo", "huawei",
                    "poco", "honor", "lg", "zte", "vivo", "motorola",
                    "realme", "oneplus"
            };

            String[] fake_manufacturers = {
                    "sumsang", "irhone", "xioami", "rebmi"
            };

            if (!SharedPreferencesUtils.getBoolean(view.getContext(), "isFakeMobileDeviceNames")) {
                finalBundle.putString("device", manufacturers[position]);
            } else {
                finalBundle.putString("device", fake_manufacturers[position]);
            }

            NavigationUtils.navigateWithNavHost(
                    (FragmentActivity) view.getContext(),
                    R.id.nav_host_fragment,
                    R.id.action_manufacturerFragment_to_devicesFragment,
                    finalBundle);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.toArray().length;
    }

    public static class holder extends RecyclerView.ViewHolder {
        TextView device_name;
        public holder(@NonNull View itemView) {
            super(itemView);
            device_name = itemView.findViewById(R.id.categories);
        }
    }
}
