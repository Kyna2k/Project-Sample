package com.example.assigment.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.R;
import com.example.assigment.method.call_back;
import com.example.assigment.method.call_back2;
import com.example.assigment.model.Sach;

import java.util.ArrayList;
import java.util.Locale;

public class Recycle_List_Sach_verNGeo extends RecyclerView.Adapter<Recycle_List_Sach_verNGeo.ViewHolder>{
    private Context context;
    private ArrayList<Sach> ds;
    private call_back2 call_back_ne;
    public Recycle_List_Sach_verNGeo(Context context, ArrayList<Sach> ds,call_back2 call_back_ne)
    {
        this.context = context;
        this.ds = ds;
        this.call_back_ne = call_back_ne;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.recycle_list_sanpham,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        holder.tensach.setText(ds.get(position).getTensach());
        holder.tentacgia.setText(ds.get(position).getTentacgia());
        String tien = nf.format(ds.get(position).getGiathue());
        holder.giathue.setText(tien.replace("$","").substring(0,tien.indexOf(".")-1)+ " VNĐ");
        holder.hinhsach.setImageResource(context.getResources().getIdentifier(ds.get(position).getImages(),"mipmap",context.getPackageName()));
        holder.hinhNXB.setImageResource(context.getResources().getIdentifier(ds.get(position).getImagesNXB(),"mipmap",context.getPackageName()));
        holder.soluong.setText("Thể loại: "+ds.get(position).getTenmaloai());
        holder.xephang.setText(String.valueOf(ds.get(position).getMasach()));
        holder.list_car.setCardElevation(5f);
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hinhsach,btn_moreinfo,hinhNXB;
        TextView xephang,tensach,tentacgia,giathue,soluong;
        CardView list_car;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhsach = itemView.findViewById(R.id.hinhsach);
            btn_moreinfo = itemView.findViewById(R.id.btn_moreinfo);
            hinhNXB = itemView.findViewById(R.id.hinhNXB);
            xephang = itemView.findViewById(R.id.xephang);
            tensach = itemView.findViewById(R.id.tensach);
            tentacgia = itemView.findViewById(R.id.tentacgia);
            giathue = itemView.findViewById(R.id.giathue);
            soluong = itemView.findViewById(R.id.soluong);
            list_car = itemView.findViewById(R.id.list_car);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    call_back_ne.camonban_thanhcong2(xephang);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    call_back_ne.yeuem_muon_ngannam(xephang);
                    return true;
                }
            });
        }
    }
}
