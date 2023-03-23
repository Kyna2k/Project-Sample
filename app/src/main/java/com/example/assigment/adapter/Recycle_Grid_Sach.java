package com.example.assigment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.R;
import com.example.assigment.dao.SachDAO;
import com.example.assigment.model.Sach;
import com.example.assigment.view.ThongTinSachActivity;

import java.util.ArrayList;
import java.util.Locale;

public class Recycle_Grid_Sach extends RecyclerView.Adapter<Recycle_Grid_Sach.ViewHolder> {
    private Context context;
    private ArrayList<Sach> ds;
    public Recycle_Grid_Sach(Context context,ArrayList<Sach> ds)
    {
        this.context = context;
        this.ds = ds;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.recycle_grid_sanpham,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        holder.tensach.setText(ds.get(position).getTensach());
        holder.tentacgia.setText(ds.get(position).getTentacgia());
        String tien = nf.format(ds.get(position).getGiathue());
        holder.giathue.setText(tien.replace("$","").substring(0,tien.indexOf(".")-1)+ " VNĐ");
        holder.hinh.setImageResource(context.getResources().getIdentifier(ds.get(position).getImages(),"mipmap",context.getPackageName()));
        holder.grid_sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ThongTinSachActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("itemsanpham",holder.getAdapterPosition());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tensach, tentacgia,giathue;
        CardView grid_sanpham;
        ImageView hinh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tensach = itemView.findViewById(R.id.tensach);
            tentacgia = itemView.findViewById(R.id.tentacgia);
            giathue = itemView.findViewById(R.id.giathue);
            hinh = itemView.findViewById(R.id.hinhsach);
            grid_sanpham = itemView.findViewById(R.id.grid_sanpham);
        }
    }
}
