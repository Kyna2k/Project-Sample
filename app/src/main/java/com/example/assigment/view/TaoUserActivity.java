package com.example.assigment.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.assigment.R;
import com.example.assigment.dao.UserDAO;
import com.example.assigment.model.User;

public class TaoUserActivity extends AppCompatActivity {
    ImageView btn_cancel;
    EditText username,password,repassword,name,email;
    Button btn_dangky;
    UserDAO userDAO;
    Spinner selection_type;
    private TextView title_signin;
    private String[] data_spinner = {"ADMIN","THỦ THƯ","MEMBER"};
    private Integer id_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_user);
        btn_cancel = findViewById(R.id.btn_cancel);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        name = findViewById(R.id.hoten);
        email = findViewById(R.id.email);
        title_signin = findViewById(R.id.title_signin);
        btn_dangky = findViewById(R.id.btn_dangky);
        selection_type = findViewById(R.id.selection_type);
        userDAO = new UserDAO(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,data_spinner);
        selection_type.setAdapter(arrayAdapter);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        selection_type.setSelection(2);
        selection_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                title_signin.setText("Helo!\n" + selection_type.getSelectedItem());
                switch (i)
                {
                    case 0:
                        title_signin.setTextColor(Color.parseColor("#200122"));
                    break;
                    case 1:
                        title_signin.setTextColor(Color.parseColor("#FFE000"));

                        break;
                    case 2:
                        title_signin.setTextColor(Color.parseColor("#22C086"));

                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean input_check = true;
                EditText[] items = {username,password,name,email};
                for(EditText item : items )
                {
                    input_check = check_edt(item);
                    if(!input_check)
                    {
                        break;
                    }
                }
                if( input_check &&password.getText().toString().equals(repassword.getText().toString()))
                {
                    id_type = selection_type.getSelectedItemPosition();
                    String username_in = username.getText().toString();
                    String password_in = password.getText().toString();
                    String name_in = name.getText().toString();
                    String email_in = email.getText().toString();
                    User user = new User(name_in,email_in,"avatar0",username_in,password_in,id_type);
                    Boolean check = userDAO.dangky(user);
                    dangky(check);
                }
                else {
                    Toast.makeText(TaoUserActivity.this, "Bạn đang bỏ trống thông tin hoặc nhập lại mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
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
                    Toast.makeText(TaoUserActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else
                {
                    Toast.makeText(TaoUserActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }

            }
        },(long) Math.floor(Math.random()*2000)+1000);
    }
    private boolean check_edt(EditText input)
    {
        if(input.getText().toString().equals(""))
        {
            return false;
        }
        return true;
    }
}