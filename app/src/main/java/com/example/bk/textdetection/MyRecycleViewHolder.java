package com.example.bk.textdetection;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bk.textdetection.Interface.IItemClickListener;

public class MyRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView txt_id, txt_hoten, txt_diachi, txt_ngaysinh, txt_ngaydangky, txt_quequan, txt_cmnd;
    IItemClickListener itemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MyRecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_id = itemView.findViewById(R.id.txt_id);
        txt_hoten = itemView.findViewById(R.id.txt_hovaten);
        txt_diachi = itemView.findViewById(R.id.txt_diachi);
        txt_ngaysinh = itemView.findViewById(R.id.txt_ngaysinh);
        txt_ngaydangky = itemView.findViewById(R.id.txt_ngaydangky);
        txt_quequan = itemView.findViewById(R.id.txt_quequan);
        txt_cmnd = itemView.findViewById(R.id.txt_cmnd);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
