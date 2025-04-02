package com.example.tlucontact.CBNV;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.R;

public class CBNV_ViewHolder extends RecyclerView.ViewHolder {
    private ImageView imv_cbnv;
    private TextView txt_name;
    private TextView txt_position;
    private TextView txt_phonenumber;
    private TextView txt_email;
    private TextView txt_donvi;

    public CBNV_ViewHolder(@NonNull View itemView) {
        super(itemView);
        imv_cbnv = itemView.findViewById(R.id.imv_cbnv);
        txt_name = itemView.findViewById(R.id.txt_name_cbnv);
        txt_position = itemView.findViewById(R.id.txt_position_cbnv);
        txt_phonenumber = itemView.findViewById(R.id.txt_phonenumber_cbnv);
        txt_email = itemView.findViewById(R.id.txt_email_cbnv);
        txt_donvi = itemView.findViewById(R.id.txt_donvi_cbnv);
    }

    public void bind(CBNV cbnv) {
        imv_cbnv.setImageResource(cbnv.getImv_cbnv_avatar());
        txt_name.setText(cbnv.getName());
        txt_position.setText(cbnv.getPosition());
        txt_phonenumber.setText(cbnv.getPhone_number());
        txt_email.setText(cbnv.getEmail());
        txt_donvi.setText(cbnv.getDonvi());
    }
}
