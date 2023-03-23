package com.example.assigment.adapter;

import android.app.Activity;
import android.content.Context;
import android.icu.text.NumberFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assigment.R;
import com.example.assigment.model.Sach;

import java.util.ArrayList;
import java.util.Locale;

public class Recycle_TokTok_SanPham extends RecyclerView.Adapter<Recycle_TokTok_SanPham.ViewHolder> {
    private Context context;
    private ArrayList<Sach> ds;
    public Recycle_TokTok_SanPham(Context context, ArrayList<Sach> ds)
    {
        this.context = context;
        this.ds = ds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.recycle_thongtin_sach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        holder.hinhsach.setImageResource(context.getResources().getIdentifier(ds.get(position).getImages(),"mipmap",context.getPackageName()));
        holder.logo_nxb.setImageResource(context.getResources().getIdentifier(ds.get(position).getImagesNXB(),"mipmap",context.getPackageName()));
        Glide.with(context).load(context.getResources().getIdentifier(ds.get(position).getImagesTacGia(),"mipmap",context.getPackageName())).circleCrop().into(holder.hinh_tg);
        holder.tensach.setText(ds.get(position).getTensach());
        holder.tentacgia.setText(ds.get(position).getTentacgia());
        String tien = nf.format(ds.get(position).getGiathue());
        holder.giathue.setText(tien.replace("$","").substring(0,tien.indexOf(".")-1)+ " VNĐ");
        holder.tentacgia.setText(ds.get(holder.getAdapterPosition()).getTentacgia());
        holder.tentheloai.setText(ds.get(holder.getAdapterPosition()).getTenmaloai());
        holder.tenNXB.setText(ds.get(holder.getAdapterPosition()).getNxb());
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hinhsach,logo_nxb,hinh_tg;
        TextView giathue,tensach,tenNXB,tentacgia,tentheloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhsach = itemView.findViewById(R.id.hinhsach);
            logo_nxb = itemView.findViewById(R.id.logo_nxb);
            hinh_tg = itemView.findViewById(R.id.hinh_tg);
            giathue = itemView.findViewById(R.id.giathue);
            tensach = itemView.findViewById(R.id.tensach);
            tenNXB = itemView.findViewById(R.id.tenNXB);
            tentacgia = itemView.findViewById(R.id.tentacgia);
            tentheloai = itemView.findViewById(R.id.tentheloai);

        }
    }
}
