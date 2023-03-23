package com.example.assigment.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.assigment.R;
import com.example.assigment.adapter.Listview_NXB;
import com.example.assigment.adapter.Recycle_List_TacGia;
import com.example.assigment.dao.NXBDAO;
import com.example.assigment.dao.TacGiaDAo;
import com.example.assigment.model.NXB;
import com.example.assigment.model.TacGia;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLyNXB extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView recyclerView;
    private FloatingActionButton btn_add;
    private NXBDAO nxbdao;
    private Listview_NXB recycle_list_tacGia;
    private ArrayList<NXB> ds = new ArrayList<>();
    private TacGia tacGia_item = new TacGia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nxb);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Quản lý NXB");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.rec_list);
        btn_add = findViewById(R.id.btn_add_phieumuon);
        nxbdao = new NXBDAO(this);
        getData();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_nxb(null);
            }
        });

        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                NXB nxb_item = (NXB) adapterView.getAdapter().getItem(i);
                dialog_menu(nxb_item);
                return true;
            }
        });
    }
    public void getData()
    {
        ds = nxbdao.getAll();
        recycle_list_tacGia = new Listview_NXB(this,ds);
        recyclerView.setAdapter(recycle_list_tacGia);
    }
    public void add_nxb(NXB nxb_ne){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_tacgia,null);
        builder.setView(view);
        Button btn_capnhat = view.findViewById(R.id.btn_comfrim);
        Button btn_xoa = view.findViewById(R.id.btn_cancel);
        ImageView avatar = view.findViewById(R.id.avatar);
        EditText tentacgia = view.findViewById(R.id.tentacgia);
        if(nxb_ne != null)
        {
            Glide.with(this).load(getResources().getIdentifier(nxb_ne.getImages(),"mipmap",getPackageName())).circleCrop().into(avatar);
            tentacgia.setText(nxb_ne.getTenNXB());
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nxb_ne == null)
                {
                    NXB nxb = new NXB();
                    nxb.setTenNXB(tentacgia.getText().toString());
                    nxb.setImages("nxb0");
                    Boolean check = nxbdao.addnxb(nxb);
                    if(check)
                    {
                        Toast.makeText(QuanLyNXB.this, "Thêm NXB thành công", Toast.LENGTH_SHORT).show();
                        getData();
                    }else {
                        Toast.makeText(QuanLyNXB.this, "Thêm NXB thất bại", Toast.LENGTH_SHORT).show();

                    }
                }else {

                    nxb_ne.setTenNXB(tentacgia.getText().toString());
                    nxb_ne.setImages(nxb_ne.getImages());
                    Boolean check = nxbdao.updateNXB(nxb_ne);
                    if(check)
                    {
                        Toast.makeText(QuanLyNXB.this, "Cập nhật NXBthành công", Toast.LENGTH_SHORT).show();
                        getData();
                    }else {
                        Toast.makeText(QuanLyNXB.this, "Cập nhật NXB thất bại", Toast.LENGTH_SHORT).show();

                    }
                }
                alertDialog.dismiss();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
    }
    public void dialog_menu(NXB nxb_ne2){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.toi_ghet_fontend,null);
        builder.setView(view);
        TextView btn_capnhat = view.findViewById(R.id.option_1);
        TextView btn_xoa = view.findViewById(R.id.option_2);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_nxb(nxb_ne2);
                alertDialog.dismiss();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa_ne(nxb_ne2.getMaNXB());
                alertDialog.dismiss();

            }
        });
    }
    public void xoa_ne(Integer id_nxb) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.diaglog_comfirm, null);
        TextView noidung = view.findViewById(R.id.noidung);
        Button comfrim = view.findViewById(R.id.btn_comfrim);
        Button cancel = view.findViewById(R.id.btn_cancel);
        noidung.setText("Bạn xác nhận xóa NXB? Đồng thề sẽ xóa luôn các dữ liệu liên quan");
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        comfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = nxbdao.datele(id_nxb);
                if (check) {
                    Toast.makeText(QuanLyNXB.this, "Đã xóa NXB", Toast.LENGTH_SHORT).show();
                    getData();
                }else {
                    Toast.makeText(QuanLyNXB.this, "Lỗi không xóa NXB", Toast.LENGTH_SHORT).show();

                }
                alertDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}