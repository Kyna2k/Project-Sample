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
import com.example.assigment.model.Sach;

import java.util.ArrayList;
import java.util.Locale;

public class Recycle_List_Sach extends RecyclerView.Adapter<Recycle_List_Sach.ViewHolder>{
    private Context context;
    private ArrayList<Sach> ds;
    public Recycle_List_Sach(Context context,ArrayList<Sach> ds)
    {
        this.context = context;
        this.ds = ds;
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
        holder.soluong.setText("SL: "+ String.valueOf(ds.get(position).getSomuon()));
        String dem  = String.valueOf(position+1);
        holder.xephang.setText(dem);
        holder.xephang.setTextColor(Color.parseColor("#000000"));
        holder.xephang.setTextSize(18);
        if(dem.equals("1"))
        {
            holder.xephang.setTextColor(Color.parseColor("#E6A519"));
            holder.xephang.setTextSize(35);
        }else if(dem.equals("2")){
            holder.xephang.setTextColor(Color.parseColor("#A9A9A9"));
            holder.xephang.setTextSize(30);
        }else if(dem.equals("3"))
        {
            holder.xephang.setTextColor(Color.parseColor("#cd7f32"));
            holder.xephang.setTextSize(25);
        }
        holder.btn_moreinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_moreinfo,null);
                builder.setView(view1);
                ImageView hinhtacgia = view1.findViewById(R.id.hinhTacGia);
                ImageView hinhNXB = view1.findViewById(R.id.logo_nxb);
                TextView tentacgia = view1.findViewById(R.id.tentacgia_new2);
                TextView tentheloai = view1.findViewById(R.id.tentheloai);
                TextView tenNXB = view1.findViewById(R.id.tenNXB);
                TextView tentacgia_ne = view1.findViewById(R.id.tentacgia_ne);

                hinhtacgia.setImageResource(context.getResources().getIdentifier(ds.get(holder.getAdapterPosition()).getImagesTacGia(),"mipmap",context.getPackageName()));
                hinhNXB.setImageResource(context.getResources().getIdentifier(ds.get(holder.getAdapterPosition()).getImagesNXB(),"mipmap",context.getPackageName()));
                tentacgia.setText("Tên tác giả: "+ds.get(holder.getAdapterPosition()).getTentacgia());
                tentheloai.setText("Thể loại: "+ds.get(holder.getAdapterPosition()).getTenmaloai());
                tenNXB.setText("Nhà xuất bản: "+ds.get(holder.getAdapterPosition()).getNxb());
                tentacgia_ne.setText(ds.get(holder.getAdapterPosition()).getTentacgia());
                AlertDialog dialog =builder.create();
                dialog.show();
            }
        });
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
        }
    }
}
