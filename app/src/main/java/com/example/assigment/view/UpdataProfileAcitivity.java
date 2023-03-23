package com.example.assigment.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment.R;
import com.example.assigment.dao.UserDAO;
import com.example.assigment.model.User;

public class UpdataProfileAcitivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    EditText email, name;
    Button btn_capnhat;
    UserDAO userDAO;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_profile_acitivity);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Cập nhật thông tin");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        Integer id = sharedPreferences.getInt("id",-1);
        userDAO = new UserDAO(this);
        user = userDAO.getUser(id);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        email.setText(user.getEmail());
        name.setText(user.getHoTen());
        btn_capnhat = findViewById(R.id.btn_capnhat);
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String re_email = email.getText().toString();
                String re_name = name.getText().toString();
                if(!re_email.equals("") && !re_name.equals(""))
                {
                    user.setHoTen(re_name);
                    user.setEmail(re_email);
                    dialogxacnhan(user);
                }else {
                    Toast.makeText(UpdataProfileAcitivity.this, "Vui lòng không bỏ trống thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void dialogxacnhan(User user1)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.diaglog_comfirm,null);
        TextView noidung = view.findViewById(R.id.noidung);
        Button comfrim = view.findViewById(R.id.btn_comfrim);
        Button cancel = view.findViewById(R.id.btn_cancel);
        noidung.setText("Bạn có đồng ý đổi thông tin không?");
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        comfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = userDAO.changePass(user1);
                if(check)
                {
                    alertDialog.dismiss();
                    Toast.makeText(UpdataProfileAcitivity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                    setResult(666,new Intent());
                    finish();
                }
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