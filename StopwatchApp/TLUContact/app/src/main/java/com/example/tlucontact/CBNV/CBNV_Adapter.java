package com.example.tlucontact.CBNV;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.R;

public class CBNV_Adapter extends RecyclerView.Adapter<CBNV_ViewHolder> {

    private CBNV[] cbnvs;
    private OnItemClickListener listener;

    public CBNV_Adapter(CBNV[] cbnvs) {
        this.cbnvs = cbnvs;
    }

    public interface OnItemClickListener {
        void onItemClick(CBNV cbnv);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CBNV_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cbnv, parent, false);
        return new CBNV_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CBNV_ViewHolder holder, int position) {
        CBNV cbnv = cbnvs[position];
        holder.bind(cbnv);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(cbnv);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cbnvs.length;
    }
}
