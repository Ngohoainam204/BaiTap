package com.example.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private ImageView imvAvatar;
    private TextView txtName;
    private TextView txtSid;
    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        imvAvatar = itemView.findViewById(R.id.img_student_ava);
        txtName = itemView.findViewById(R.id.txt_student_name);
        txtSid = itemView.findViewById(R.id.txt_student_Sid);

    }
    public void bind(Student student){
        imvAvatar.setImageResource(student.getAvatar());
        txtName.setText(student.getFullname());
        txtSid.setText(student.getSid());
    }

    }

