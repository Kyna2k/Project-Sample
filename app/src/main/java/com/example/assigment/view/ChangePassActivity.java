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

public class ChangePassActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    EditText pass_cu, pass_moi,repass_moi;
    Button btn_capnhat;
    UserDAO userDAO;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changle_pass);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Đổi mật khẩu");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        Integer id = sharedPreferences.getInt("id",-1);
        userDAO = new UserDAO(this);
        user = userDAO.getUser(id);
        pass_cu = findViewById(R.id.password_cu);
        pass_moi = findViewById(R.id.password_moi);
        repass_moi = findViewById(R.id.repassword_moi);
        btn_capnhat = findViewById(R.id.btn_doimatkhau);
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass_cu.getText().toString().equals(user.getPassword()))
                {
                    String data_passmoi = pass_moi.getText().toString();
                    String data_repassmoi = repass_moi.getText().toString();
                    if(data_passmoi.equals(data_repassmoi))
                    {
                        user.setPassword(pass_moi.getText().toString());
                        dialogxacnhan(user);
                    }else {
                        Toast.makeText(ChangePassActivity.this, "Mật khẩu mới nhập lại không đúng với mật khẩu mới", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ChangePassActivity.this, "Vui lòng nhập đúng mật khẩu cũ", Toast.LENGTH_SHORT).show();
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
        noidung.setText("Bạn có đồng ý đổi mật khẩu? Sau khí đổi hệ thống sẽ tự động đăng xuất");
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
                    Toast.makeText(ChangePassActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    setResult(555,new Intent());
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