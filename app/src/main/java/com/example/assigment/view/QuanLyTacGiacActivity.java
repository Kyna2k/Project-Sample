package com.example.assigment.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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
import com.example.assigment.adapter.Recycle_List_TacGia;
import com.example.assigment.dao.TacGiaDAo;
import com.example.assigment.model.TacGia;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLyTacGiacActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView recyclerView;
    private FloatingActionButton btn_add;
    private TacGiaDAo tacGiaDAo;
    private Recycle_List_TacGia recycle_list_tacGia;
    private ArrayList<TacGia> ds;
    private TacGia tacGia_item = new TacGia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tac_giac);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Quản lý tác giả");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.rec_list);
        btn_add = findViewById(R.id.btn_add_phieumuon);
        tacGiaDAo = new TacGiaDAo(this);
        getData();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_tacgia(null);
            }
        });

        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                tacGia_item = (TacGia) adapterView.getAdapter().getItem(i);

                dialog_menu(tacGia_item);
                return true;
            }
        });

    }
    public void getData()
    {
        ds = tacGiaDAo.getAll();
        recycle_list_tacGia = new Recycle_List_TacGia(this,ds);
        recyclerView.setAdapter(recycle_list_tacGia);
    }
    public void add_tacgia(TacGia tacGia_ne){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_tacgia,null);
        builder.setView(view);
        Button btn_capnhat = view.findViewById(R.id.btn_comfrim);
        Button btn_xoa = view.findViewById(R.id.btn_cancel);
        ImageView avatar = view.findViewById(R.id.avatar);
        EditText tentacgia = view.findViewById(R.id.tentacgia);
        if(tacGia_ne != null)
        {
            Glide.with(this).load(getResources().getIdentifier(tacGia_ne.getImages(),"mipmap",getPackageName())).circleCrop().into(avatar);
            tentacgia.setText(tacGia_ne.getTentacgia());
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tacGia_ne == null)
                {
                    TacGia tacGia = new TacGia();
                    tacGia.setTentacgia(tentacgia.getText().toString());
                    tacGia.setImages("man");
                    Boolean check = tacGiaDAo.addTacgia(tacGia);
                    if(check)
                    {
                        Toast.makeText(QuanLyTacGiacActivity.this, "Thêm tác giả thành công", Toast.LENGTH_SHORT).show();
                        getData();
                    }else {
                        Toast.makeText(QuanLyTacGiacActivity.this, "Thêm tác giả thất bại", Toast.LENGTH_SHORT).show();

                    }
                }else {

                    tacGia_ne.setTentacgia(tentacgia.getText().toString());
                    tacGia_ne.setImages(tacGia_ne.getImages());
                    Boolean check = tacGiaDAo.updateTacGia(tacGia_ne);
                    if(check)
                    {
                        Toast.makeText(QuanLyTacGiacActivity.this, "Cập nhật tác giả thành công", Toast.LENGTH_SHORT).show();
                        getData();
                    }else {
                        Toast.makeText(QuanLyTacGiacActivity.this, "Cập nhật tác giả thất bại", Toast.LENGTH_SHORT).show();

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
    public void dialog_menu(TacGia tacGia_ne2){
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
                add_tacgia(tacGia_ne2);
                alertDialog.dismiss();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa_ne(tacGia_ne2.getMatacgia());
                alertDialog.dismiss();

            }
        });
    }
    public void xoa_ne(Integer id_tacgia) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.diaglog_comfirm, null);
        TextView noidung = view.findViewById(R.id.noidung);
        Button comfrim = view.findViewById(R.id.btn_comfrim);
        Button cancel = view.findViewById(R.id.btn_cancel);
        noidung.setText("Bạn xác nhận xóa  tác giả? Đồng thề sẽ xóa luôn các dữ liệu liên quan");
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        comfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = tacGiaDAo.delateTacgia(id_tacgia);
                if (check) {
                    Toast.makeText(QuanLyTacGiacActivity.this, "Đã xóa tác giả", Toast.LENGTH_SHORT).show();
                    getData();
                }else {
                    Toast.makeText(QuanLyTacGiacActivity.this, "Lỗi không xóa tác giả", Toast.LENGTH_SHORT).show();

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