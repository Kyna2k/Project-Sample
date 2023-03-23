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
import com.example.assigment.adapter.Recycle_List_User;
import com.example.assigment.dao.UserDAO;
import com.example.assigment.method.call_back;
import com.example.assigment.model.PhieuMuon;
import com.example.assigment.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLyUserActivity extends AppCompatActivity implements call_back {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private Recycle_List_User recycle_list_user;
    private ArrayList<User> ds = new ArrayList<>();
    private UserDAO userDAO;
    private Integer lay_code = 0;
    private FloatingActionButton btn_themuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_user);
        toolbar = findViewById(R.id.toolbar);
        Intent get = getIntent();
        Bundle bundle = get.getExtras();
        recyclerView = findViewById(R.id.rec_list);
        btn_themuser = findViewById(R.id.btn_add_user);
        userDAO = new UserDAO(this);
        getData();

        if(bundle != null)
        {
            lay_code = bundle.getInt("lay",-1);
        }
        if(lay_code == 888)
        {
            toolbar.setTitle("Chọn user");

        }else {
            toolbar.setTitle("Quản lý user");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        btn_themuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuanLyUserActivity.this,TaoUserActivity.class));
                getData();
            }
        });

    }
    private void getData()
    {
        ds = userDAO.getALL();
        recycle_list_user = new Recycle_List_User(this,ds,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recycle_list_user);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void camonban_thanhcong(TextView id) {
        if(lay_code == 888)
        {
            Intent dua = new Intent();
            Bundle dua_data = new Bundle();
            dua_data.putInt("id_user",Integer.valueOf(id.getText().toString()));
            dua.putExtras(dua_data);
            setResult(1809,dua);
            finish();
        }

    }

    @Override
    public void lancuoi_toi_code_fontend(TextView id) {
        dialog_menu(Integer.parseInt(id.getText().toString()));

    }

    public void dialog_menu(Integer id_user){
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
                Intent intent = new Intent(QuanLyUserActivity.this,CapNhatUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("doitaikhoang",id_user);
                intent.putExtras(bundle);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa_ne(id_user);
                alertDialog.dismiss();

            }
        });
    }
    public void xoa_ne(Integer id_user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.diaglog_comfirm, null);
        TextView noidung = view.findViewById(R.id.noidung);
        Button comfrim = view.findViewById(R.id.btn_comfrim);
        Button cancel = view.findViewById(R.id.btn_cancel);
        noidung.setText("Bạn xác nhận xóa user?");
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        comfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = userDAO.deleteUser(id_user);
                if (check) {
                    getData();
                    Toast.makeText(QuanLyUserActivity.this, "Đã xóa user", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(QuanLyUserActivity.this, "Lỗi không xóa được user", Toast.LENGTH_SHORT).show();

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