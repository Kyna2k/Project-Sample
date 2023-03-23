package com.example.assigment.view;

import static com.example.assigment.model.ServiceAPI_User.Base_Service;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.example.assigment.model.user2;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {
    ImageView btn_cancel;
    EditText username,password,repassword,name,email;
    Button btn_dangky;
    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn_cancel = findViewById(R.id.btn_cancel);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        name = findViewById(R.id.hoten);
        email = findViewById(R.id.email);
        btn_dangky = findViewById(R.id.btn_dangky);
        userDAO = new UserDAO(this);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_in = username.getText().toString();
                String password_in = password.getText().toString();
                String name_in = name.getText().toString();
                String email_in = email.getText().toString();
                CapNhatUser(new user2(username_in,password_in,name_in,email_in));
            }
        });

    }
    private void dangky(Boolean check)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.CustomDialog);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.loading_dialog,null);
        ImageView load_toktok = view.findViewById(R.id.load_toktok);
        TextView noidung = view.findViewById(R.id.noidung);
        noidung.setText("Đăng ký");
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
                    alertDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else
                {
                    Toast.makeText(SignInActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }

            }
        },(long) Math.floor(Math.random()*2000)+1000);
    }
    private void CapNhatUser(user2 user){

        ServiceAPI_User requestInterface = new Retrofit.Builder()
                .baseUrl(Base_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI_User.class);

        new CompositeDisposable().add(requestInterface.capnhatuser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );

    }

    private void handleResponse(Integer integer) {
        Log.e("huyvippro", "handleResponse: " + integer );
        if(integer > 0)
        {
            Toast.makeText(this, "cập nhật thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "cập nhật thất bại", Toast.LENGTH_SHORT).show();

        }
    }
    private void handleError(Throwable throwable) {
        Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
    }



}