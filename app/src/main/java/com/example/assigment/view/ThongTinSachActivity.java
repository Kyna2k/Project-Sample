package com.example.assigment.view;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.example.assigment.R;
import com.example.assigment.adapter.Recycle_TokTok_SanPham;
import com.example.assigment.dao.SachDAO;
import com.example.assigment.model.Sach;

import java.util.ArrayList;

public class ThongTinSachActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    Toolbar toolbar;
    SachDAO sachDAO;
    ArrayList<Sach> ds;
    ArrayList<Sach> ds_Add_toktok = new ArrayList<>();
    int i;
    int tong_dau;
    Recycle_TokTok_SanPham recycle_tokTok_sanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_sach);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        sachDAO = new SachDAO(this);
        //CÔNG thức from dinhnt with love
        viewPager2 = findViewById(R.id.list_toktok_sanpham);
        ds = sachDAO.getAll_Toktok();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        i = bundle.getInt("itemsanpham",0);
        recycle_tokTok_sanPham = new Recycle_TokTok_SanPham(this,ds);
        viewPager2.setAdapter(recycle_tokTok_sanPham);
        viewPager2.setCurrentItem(i,false);
    }
}