package com.example.tlucontact.DBDV;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.R;

public class DBDV_Adapter extends RecyclerView.Adapter<DBDV_ViewHolder> {
    private DBDV[] dbdvs;
    private OnItemClickListener listener;

    public DBDV_Adapter(DBDV[] dbdvs) {
        this.dbdvs = dbdvs;
    }

    public interface OnItemClickListener {
        void onItemClick(DBDV dbdv);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DBDV_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dbdv, parent, false);
        return new DBDV_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DBDV_ViewHolder holder, int position) {
        DBDV dbdv = dbdvs[position];
        holder.bind(dbdv);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(dbdv);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dbdvs.length;
    }
}
