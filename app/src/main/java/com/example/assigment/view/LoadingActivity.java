package com.example.assigment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.assigment.R;

public class LoadingActivity extends AppCompatActivity {
    ImageView loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        loading = findViewById(R.id.loading);
        Glide.with(this).load(R.mipmap.load).into(loading);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(android.R.color.transparent));
        window.setNavigationBarColor(this.getResources().getColor(android.R.color.transparent));
        window.getDecorView().setSystemUiVisibility(0);
        window.setBackgroundDrawable(this.getResources().getDrawable(R.mipmap.background_main1));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingActivity.this,LoginActivity.class));
                finish();
            }
        },(long) Math.floor(Math.random()*3000)+1000);
    }
}