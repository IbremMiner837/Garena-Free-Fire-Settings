package studio.jvmfrog.ffsettings.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import studio.jvmfrog.ffsettings.model.*;
import java.util.List;

import studio.jvmfrog.ffsettings.R;

public class NoNameAdapter extends RecyclerView.Adapter<NoNameAdapter.NoNameViewHolder> {
    private static final int VIEW_TYPE_TEXT_VIEW = 0;
    private static final int VIEW_TYPE_SLIDER_VIEW = 1;
    private static final int VIEW_TYPE_LINK_VIEW = 2;

    private final List<NoNameModel> list;

    public NoNameAdapter(List<NoNameModel> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull NoNameAdapter.NoNameViewHolder holder, int position) {
        //
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public NoNameAdapter.NoNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_name_item, parent, false);
        return new NoNameAdapter.NoNameViewHolder(view);
    }

    public static class NoNameViewHolder extends RecyclerView.ViewHolder {
        TextView manufacturerName;
        public NoNameViewHolder(@NonNull View itemView) {
            super(itemView);
            manufacturerName = itemView.findViewById(R.id.categories);
        }
    }
}
