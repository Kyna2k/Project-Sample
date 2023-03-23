package com.example.assigment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.assigment.R;
import com.example.assigment.adapter.Recycle_list_phieumuon;
import com.example.assigment.dao.PhieuMuonDAO;
import com.example.assigment.model.PhieuMuon;

import java.util.ArrayList;

public class XemPhieuMuonActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Recycle_list_phieumuon recycle_list_phieumuon;
    private PhieuMuonDAO phieuMuonDAO;
    private RecyclerView recyclerView;
    private ArrayList<PhieuMuon> ds;
    private Integer id_user,id_sach_ne,id,type_ne;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_phieu_muon);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Quản lý phiếu mượn");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.rec_list);
        phieuMuonDAO = new PhieuMuonDAO(this);
        sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        id = sharedPreferences.getInt("id",-1);
        type_ne = sharedPreferences.getInt("type",-1);
        getData(null);
    }
    private void getData(Integer vitri)
    {
        ds = phieuMuonDAO.getALl(id);
        recycle_list_phieumuon = new Recycle_list_phieumuon(this,ds,type_ne);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recycle_list_phieumuon);
        if(vitri != null)
        {
            recyclerView.scrollToPosition(vitri);

        }
    }
}