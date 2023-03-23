package com.example.assigment.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.assigment.R;
import com.example.assigment.dao.UserDAO;
import com.example.assigment.model.User;

public class CapNhatUserActivity extends AppCompatActivity {
    ImageView btn_cancel,avatar;
    EditText username,password,repassword,name,email;
    Button btn_dangky;
    UserDAO userDAO;
    Integer id_doi;
    TextView title_signin;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_user);
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        id_doi = bundle.getInt("doitaikhoang",-1);
        btn_cancel = findViewById(R.id.btn_cancel);
        avatar = findViewById(R.id.avatar);
        password = findViewById(R.id.password);
        name = findViewById(R.id.hoten);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        btn_dangky = findViewById(R.id.btn_dangky);
        title_signin = findViewById(R.id.title_signin);
        userDAO = new UserDAO(this);
        user = userDAO.getUser(id_doi);
        Glide.with(this).load(getResources().getIdentifier(user.getAvatar(),"mipmap",getPackageName())).circleCrop().into(avatar);
        password.setText(user.getPassword());
        name.setText(user.getHoTen());
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        username.setEnabled(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password_in = password.getText().toString();
                String name_in = name.getText().toString();
                String email_in = email.getText().toString();
                user.setHoTen(name_in);
                user.setPassword(password_in);
                user.setEmail(email_in);
                dialogxacnhan(user);
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
        noidung.setText("Đồng ý cập nhật tài khoảng");
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
                    Toast.makeText(CapNhatUserActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
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