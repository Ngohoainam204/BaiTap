package com.example.tlucontact.DBDV;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.R;

public class DBDV_ViewHolder extends RecyclerView.ViewHolder {
    private ImageView imv_dbdv;
    private TextView txt_name;
    private TextView txt_phonenumber;
    private TextView txt_address;
    public DBDV_ViewHolder(@NonNull View itemView) {
        super(itemView);
        imv_dbdv = itemView.findViewById(R.id.imv_dbdv);
        txt_name = itemView.findViewById(R.id.txt_name_dbdv);
        txt_phonenumber = itemView.findViewById(R.id.txt_phonenumber_dbdv);
        txt_address = itemView.findViewById(R.id.txt_address_dbdv);

    }
    public void bind(DBDV dbdv){
        imv_dbdv.setImageResource(dbdv.getImv_dbdv_avatar());
        txt_name.setText(dbdv.getName());
        txt_phonenumber.setText(dbdv.getPhone_number());
        txt_address.setText(dbdv.getAddress());
    }
}
