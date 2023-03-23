package com.example.assigment.view;

import static com.example.assigment.model.ServiceAPI_User.Base_Service;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.assigment.model.ServiceAPI_User;
import com.example.assigment.model.User;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btn_dangnhap;
    TextView dangky;

    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        dangky = findViewById(R.id.btn_dangky);
        userDAO = new UserDAO(this);
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DangNhapAPi(username.getText().toString(),password.getText().toString());
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                boolean check = userDAO.Login(user);
                dangnhap(check);
            }
        });
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignInActivity.class));
            }
        });
    }
    private void dangnhap(Boolean check)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.CustomDialog);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.loading_dialog,null);
        ImageView load_toktok = view.findViewById(R.id.load_toktok);
        Glide.with(this).load(R.mipmap.load_tiktok).into(load_toktok);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(check)
                {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    alertDialog.dismiss();
                }else
                {
                    Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }

            }
        },(long) Math.floor(Math.random()*2000)+1000);
    }
//    private void DangNhapAPi(String username, String password)
//    {
//        ServiceAPI_User requestInterface = new Retrofit.Builder()
//                .baseUrl(Base_Service)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build().create(ServiceAPI_User.class);
//        new CompositeDisposable().add(requestInterface.getUser(username,password).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io()).subscribe(this::handleReponse,this::hardleErro));
//    }
//
//    private void handleReponse(User user) {
//        Toast.makeText(this, "Đăng Nhập THành công", Toast.LENGTH_SHORT).show();
//    }
//    private void hardleErro(Throwable error) {
//        Toast.makeText(this, "Đăng Nhập thất bại", Toast.LENGTH_SHORT).show();
//
//    }


}