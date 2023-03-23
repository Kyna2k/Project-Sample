package com.example.assigment.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.R;
import com.example.assigment.model.PhieuMuon;
import com.example.assigment.view.QuanLyPhieuMuonActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Recycle_list_phieumuon extends RecyclerView.Adapter<Recycle_list_phieumuon.ViewHolder>{
    private Context context;
    private ArrayList<PhieuMuon> ds;
    private Integer type;
    public Recycle_list_phieumuon(Context context, ArrayList<PhieuMuon> ds,Integer type)
    {
        this.context = context;
        this.ds = ds;
        this.type = type;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.recycle_list_phieumuon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        holder.tensach.setText(ds.get(position).getSach().getTensach());
        holder.tentacgia.setText(ds.get(position).getSach().getTentacgia());
        String tien = nf.format(ds.get(position).getSach().getGiathue());
        holder.giathue.setText(tien.replace("$","").substring(0,tien.indexOf(".")-1)+ " VNĐ");
        holder.hinhsach.setImageResource(context.getResources().getIdentifier(ds.get(position).getSach().getImages(),"mipmap",context.getPackageName()));
        holder.hinhNXB.setImageResource(context.getResources().getIdentifier(ds.get(position).getSach().getImagesNXB(),"mipmap",context.getPackageName()));
        holder.id.setText(String.valueOf(ds.get(position).getId()));
        holder.username.setText(ds.get(position).getUser().getUsername());
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        date.setTime(ds.get(position).getNgaytra());
        if(date.getYear() == 1970)
        {
            holder.ngaytra.setText("Ngày trả: " + "chưa có");
        }else {
            holder.ngaytra.setText("Ngày trả: " + dateFormat.format(date));
        }
        date.setTime(ds.get(position).getNgaythue());
        holder.ngaymuon.setText("Ngày mượn: " + dateFormat.format(date));
        if(ds.get(position).getTrangthai() == 1)
        {
            holder.trangthai.setText("Đã trả sách");
            holder.trangthai.setTextColor(Color.parseColor("#11998e"));
            holder.title.setBackground(context.getDrawable(R.drawable.background));
        }else {
            holder.trangthai.setText("Chưa trả sách");
            holder.trangthai.setTextColor(Color.parseColor("#A83700"));
            holder.title.setBackground(context.getDrawable(R.drawable.background_title_ne));

        }
        holder.soluong.setText("");
        if(type != 2)
        {
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((QuanLyPhieuMuonActivity)context).setTrangThai_ne(ds.get(holder.getAdapterPosition()).getId(),holder.getAdapterPosition());
                }
            });


            holder.item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    ((QuanLyPhieuMuonActivity)context).dialog_menu(ds.get(holder.getAdapterPosition()).getId(),holder.getAdapterPosition());
                    Log.e("check", "onLongClick: "+view.getX() +" "+ view.getY() );
                    return true;
                }
            });
        }

        holder.thuthu_dua.setText("ID: " +String.valueOf(ds.get(position).getMaQuanLyDua()));
        if(ds.get(position).getMaQuanLyNhan() == 0)
        {
            holder.thuthu_nhan.setText("ID: ");
        }else {
            holder.thuthu_nhan.setText("ID: " +String.valueOf(ds.get(position).getMaQuanLyNhan()));
        }
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View header;
        CardView item;
        ImageView hinhsach,hinhNXB;
        TextView id,tensach,tentacgia,giathue,soluong,username,ngaymuon,ngaytra,title,trangthai,thuthu_dua,thuthu_nhan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            hinhsach = header.findViewById(R.id.hinhsach);
            id = header.findViewById(R.id.xephang);
            tensach = header.findViewById(R.id.tensach);
            tentacgia = header.findViewById(R.id.tentacgia);
            giathue = header.findViewById(R.id.giathue);
            soluong = header.findViewById(R.id.soluong);
            username = itemView.findViewById(R.id.username);
            ngaymuon = itemView.findViewById(R.id.ngaymuon);
            ngaytra = itemView.findViewById(R.id.ngaytra);
            trangthai = itemView.findViewById(R.id.trangthai);
            hinhNXB = header.findViewById(R.id.hinhNXB);
            item =itemView.findViewById(R.id.item);
            title = itemView.findViewById(R.id.title_ne);
            thuthu_dua = itemView.findViewById(R.id.thuthu_dua);
            thuthu_nhan = itemView.findViewById(R.id.thuthu_nhan);
        }
    }
}
