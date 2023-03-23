package com.example.assigment.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment.R;
import com.example.assigment.adapter.Recycle_List_Sach;
import com.example.assigment.adapter.Recycle_List_Sach_verNGeo;
import com.example.assigment.adapter.Recycle_List_User;
import com.example.assigment.dao.SachDAO;
import com.example.assigment.method.call_back;
import com.example.assigment.method.call_back2;
import com.example.assigment.model.Sach;
import com.example.assigment.model.TacGia;
import com.example.assigment.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLySachActivity extends AppCompatActivity implements call_back2 {
    private Toolbar toolbar;
    private Integer lay_code = 0;
    private SachDAO sachDAO;
    private RecyclerView recyclerView;
    private Recycle_List_Sach_verNGeo list_sach;
    private ArrayList<Sach> ds = new ArrayList<>();
    private FloatingActionButton btn_add_sach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach);
        toolbar = findViewById(R.id.toolbar);
        sachDAO = new SachDAO(this);
        Intent get = getIntent();
        Bundle bundle = get.getExtras();
        recyclerView = findViewById(R.id.rec_list);
        btn_add_sach = findViewById(R.id.btn_add_sach);
        getData();
        if(bundle != null)
        {
            lay_code = bundle.getInt("lay_sach",-1);
        }
        if(lay_code == 999)
        {
            toolbar.setTitle("Chọn sách");

        }else {
            toolbar.setTitle("Quản lý sách");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        btn_add_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLySachActivity.this,AddSachAcitivty.class));
            }
        });
    }
    private void getData()
    {
        ds = sachDAO.getAll_Toktok();
        list_sach = new Recycle_List_Sach_verNGeo(this,ds,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(list_sach);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void camonban_thanhcong2(TextView id) {
        if(lay_code == 999)
        {
            Intent dua = new Intent();
            Bundle dua_data = new Bundle();
            dua_data.putInt("id_sach",Integer.valueOf(id.getText().toString()));
            dua.putExtras(dua_data);
            setResult(2000,dua);
            finish();
        }
    }

    @Override
    public void yeuem_muon_ngannam(TextView id) {
        dialog_menu(Integer.parseInt(id.getText().toString()));
    }

    public void dialog_menu(Integer id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.toi_ghet_fontend, null);
        builder.setView(view);
        TextView btn_capnhat = view.findViewById(R.id.option_1);
        TextView btn_xoa = view.findViewById(R.id.option_2);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLySachActivity.this, AddSachAcitivty.class);
                Bundle bundle = new Bundle();
                bundle.putInt("select__", id);
                intent.putExtras(bundle);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa_ne(id);
                alertDialog.dismiss();

            }
        });
    }
    public void xoa_ne(Integer id_sach) {
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
                Boolean check = sachDAO.datele(id_sach);
                if (check) {
                    Toast.makeText(QuanLySachActivity.this, "Đã xóa sách", Toast.LENGTH_SHORT).show();
                    getData();
                }else {
                    Toast.makeText(QuanLySachActivity.this, "Lỗi không xóa sách", Toast.LENGTH_SHORT).show();

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