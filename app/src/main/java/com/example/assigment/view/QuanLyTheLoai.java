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
import com.example.assigment.adapter.Listview_TheLoai;
import com.example.assigment.adapter.Recycle_List_TacGia;
import com.example.assigment.dao.TacGiaDAo;
import com.example.assigment.dao.TheLoaiDao;
import com.example.assigment.model.TacGia;
import com.example.assigment.model.TheLoai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLyTheLoai extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView recyclerView;
    private FloatingActionButton btn_add;
    private TheLoaiDao theLoaiDao;
    private Listview_TheLoai Listview_TheLoai;
    private ArrayList<TheLoai> ds;
    private TheLoai theLoai_item = new TheLoai();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_the_loai);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Quản lý thể loại");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.rec_list);
        btn_add = findViewById(R.id.btn_add_phieumuon);
        theLoaiDao = new TheLoaiDao(this);
        getData();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_theloai(null);
            }
        });

        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                theLoai_item = (TheLoai) adapterView.getAdapter().getItem(i);

                dialog_menu(theLoai_item);
                return true;
            }
        });
    }
    public void getData()
    {
        ds = theLoaiDao.getAll();
        Listview_TheLoai = new Listview_TheLoai(this,ds);
        recyclerView.setAdapter(Listview_TheLoai);
    }
    public void add_theloai(TheLoai theLoai_ne){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_tacgia,null);
        builder.setView(view);
        Button btn_capnhat = view.findViewById(R.id.btn_comfrim);
        Button btn_xoa = view.findViewById(R.id.btn_cancel);
        ImageView avatar = view.findViewById(R.id.avatar);
        avatar.setVisibility(View.GONE);
        EditText tentacgia = view.findViewById(R.id.tentacgia);
        if(theLoai_ne != null)
        {
            tentacgia.setText(theLoai_ne.getTentheloai());
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(theLoai_ne == null)
                {
                    TheLoai theLoai = new TheLoai();
                    theLoai.setTentheloai(tentacgia.getText().toString());
                    Boolean check = theLoaiDao.addtheloai(theLoai);
                    if(check)
                    {
                        Toast.makeText(QuanLyTheLoai.this, "Thêm thể loại thành công", Toast.LENGTH_SHORT).show();
                        getData();
                    }else {
                        Toast.makeText(QuanLyTheLoai.this, "Thêm thể loại thất bại", Toast.LENGTH_SHORT).show();

                    }
                }else {

                    theLoai_ne.setTentheloai(tentacgia.getText().toString());
                    Boolean check = theLoaiDao.updateTheLoai(theLoai_ne);
                    if(check)
                    {
                        Toast.makeText(QuanLyTheLoai.this, "Cập nhật thể loại thành công", Toast.LENGTH_SHORT).show();
                        getData();
                    }else {
                        Toast.makeText(QuanLyTheLoai.this, "Cập nhật thể loại thất bại", Toast.LENGTH_SHORT).show();

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
    public void dialog_menu(TheLoai theLoai_ne2){
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
                add_theloai(theLoai_ne2);
                alertDialog.dismiss();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa_ne(theLoai_ne2.getMatheloai());
                alertDialog.dismiss();

            }
        });
    }
    public void xoa_ne(Integer id_theloai) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.diaglog_comfirm, null);
        TextView noidung = view.findViewById(R.id.noidung);
        Button comfrim = view.findViewById(R.id.btn_comfrim);
        Button cancel = view.findViewById(R.id.btn_cancel);
        noidung.setText("Bạn xác nhận xóa thể loại? Đồng thời sẽ xóa luôn những dữ liệu liên quan");
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        comfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = theLoaiDao.datele(id_theloai);
                if (check) {
                    Toast.makeText(QuanLyTheLoai.this, "Đã xóa thể loại", Toast.LENGTH_SHORT).show();
                    getData();
                }else {
                    Toast.makeText(QuanLyTheLoai.this, "Lỗi không xóa thể loại", Toast.LENGTH_SHORT).show();

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