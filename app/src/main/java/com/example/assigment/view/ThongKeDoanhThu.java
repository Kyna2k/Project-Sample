package com.example.assigment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.icu.text.NumberFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assigment.R;
import com.example.assigment.dao.PhieuMuonDAO;

import java.util.Locale;

public class ThongKeDoanhThu extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText ngay,ngay_tra;
    private Calendar myCalendar= Calendar.getInstance();
    private Calendar myCalendar2= Calendar.getInstance();
    private Long time_ne1,time_ne2;
    private Button btn_checkdoanhthu;
    private TextView giatien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);
        toolbar = findViewById(R.id.toolbar);
        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(this);
        toolbar.setTitle("Thống kê doanh thu");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(android.R.color.transparent));
        window.setNavigationBarColor(this.getResources().getColor(android.R.color.transparent));
        window.getDecorView().setSystemUiVisibility(0);
        window.setBackgroundDrawable(this.getResources().getDrawable(R.mipmap.background_main1));
        myCalendar2.set(Calendar.HOUR_OF_DAY,1);
        myCalendar2.set(Calendar.MINUTE,0);
        myCalendar2.set(Calendar.SECOND,0);
        myCalendar2.set(Calendar.MILLISECOND,0);
        time_ne1 = myCalendar.getTimeInMillis();
        myCalendar2.set(Calendar.HOUR_OF_DAY,0);
        myCalendar2.set(Calendar.MINUTE,0);
        myCalendar2.set(Calendar.SECOND,0);
        myCalendar2.set(Calendar.MILLISECOND,0);
        time_ne1 = myCalendar2.getTimeInMillis();
        ngay = findViewById(R.id.nhapngay);
        ngay_tra = findViewById(R.id.ngaytra);
        giatien = findViewById(R.id.giatien);
        btn_checkdoanhthu = findViewById(R.id.btn_checkdoanhthu);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ThongKeDoanhThu.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        DatePickerDialog.OnDateSetListener date_tra =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH,month);
                myCalendar2.set(Calendar.DAY_OF_MONTH,day);
                updateLabel2();
            }
        };
        ngay_tra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ThongKeDoanhThu.this,date_tra,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btn_checkdoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLabel();
                updateLabel2();
                NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
                String tien = nf.format(phieuMuonDAO.thongkedoanhthu((long)time_ne1,(long)time_ne2));
                giatien.setText(tien.replace("$","").substring(0,tien.indexOf(".")-1)+ " VNĐ");
                Log.d("check", "onClick: " + (phieuMuonDAO.thongkedoanhthu(1664582401000l,1667260801000l)));
            }
        });
    }
    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        myCalendar.set(Calendar.HOUR_OF_DAY,0);
        myCalendar.set(Calendar.MINUTE,0);
        myCalendar.set(Calendar.SECOND,0);
        myCalendar.set(Calendar.MILLISECOND,0);
        ngay.setText(dateFormat.format(myCalendar.getTime()));
        Log.i("conmeno", "updateLabel2: " + myCalendar.getTimeInMillis());
        time_ne1 = myCalendar.getTimeInMillis();
    }
    private void updateLabel2(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        myCalendar2.set(Calendar.HOUR_OF_DAY,0);
        myCalendar2.set(Calendar.MINUTE,0);
        myCalendar2.set(Calendar.SECOND,0);
        myCalendar2.set(Calendar.MILLISECOND,0);
        Log.i("conmeno", "updateLabel2: " + myCalendar2.getTimeInMillis());
        ngay_tra.setText(dateFormat.format(myCalendar2.getTime()));
        time_ne2 = myCalendar2.getTimeInMillis();
    }
}