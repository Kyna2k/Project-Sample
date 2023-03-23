package com.example.assigment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.assigment.R;

public class HomeQuanLySachActivity extends AppCompatActivity {
    private CardView btn_quanlysach,btn_quanlytheloai,btn_quanlynxb,btn_quanlytacgia;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach2);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(android.R.color.transparent));
        window.setNavigationBarColor(this.getResources().getColor(android.R.color.transparent));
        window.getDecorView().setSystemUiVisibility(0);
        window.setBackgroundDrawable(this.getResources().getDrawable(R.mipmap.back_ground_quanlysach));
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Quản lý về sách");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btn_quanlynxb = findViewById(R.id.btn_nxb);
        btn_quanlytacgia = findViewById(R.id.btn_tacgia);
        btn_quanlytheloai = findViewById(R.id.btn_loaisach);
        btn_quanlysach = findViewById(R.id.btn_sach);
        btn_quanlysach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeQuanLySachActivity.this,QuanLySachActivity.class));
            }
        });
        btn_quanlytacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeQuanLySachActivity.this,QuanLyTacGiacActivity.class));

            }
        });
        btn_quanlynxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeQuanLySachActivity.this,QuanLyNXB.class));

            }
        });
        btn_quanlytheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeQuanLySachActivity.this,QuanLyTheLoai.class));

            }
        });
    }
}