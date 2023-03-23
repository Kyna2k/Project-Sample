package com.example.assigment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assigment.R;
import com.example.assigment.adapter.Listview_NXB;
import com.example.assigment.adapter.Listview_TheLoai;
import com.example.assigment.adapter.Recycle_List_TacGia;
import com.example.assigment.dao.NXBDAO;
import com.example.assigment.dao.SachDAO;
import com.example.assigment.dao.TacGiaDAo;
import com.example.assigment.dao.TheLoaiDao;
import com.example.assigment.model.NXB;
import com.example.assigment.model.Sach;
import com.example.assigment.model.TacGia;
import com.example.assigment.model.TheLoai;

import java.util.ArrayList;

public class AddSachAcitivty extends AppCompatActivity {
    private Toolbar toolbar;
    private TheLoaiDao  theLoaiDao;
    private TacGiaDAo tacGiaDAo;
    private NXBDAO nxbdao;
    private ArrayList<TacGia> ds_tacgia;
    private ArrayList<NXB> ds_nxb;
    private ArrayList<TheLoai> ds_thelaoi;
    private Recycle_List_TacGia list_tacGia;
    private Listview_TheLoai list_theLoai;
    private Listview_NXB list_nxb;
    private Spinner spinner_tacgia,spinner_theloai,spinner_nxb;
    private EditText tensach,giathue;
    private SachDAO sachDAO;
    private Integer id_sach = 0;
    private ImageView them_hinsach;
    private Sach sachsua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sach_acitivty);
        toolbar = findViewById(R.id.toolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        spinner_tacgia = findViewById(R.id.tacgiac);
        spinner_theloai = findViewById(R.id.theloai);
        spinner_nxb = findViewById(R.id.nxb);
        them_hinsach = findViewById(R.id.them_hinsach);
        Button btn_themsach = findViewById(R.id.btn_themsach);
        tensach = findViewById(R.id.tensach);
        giathue = findViewById(R.id.giathue);
        sachDAO = new SachDAO(this);
        theLoaiDao = new TheLoaiDao(this);
        tacGiaDAo = new TacGiaDAo(this);
        nxbdao = new NXBDAO(this);
        ds_nxb = nxbdao.getAll();
        ds_tacgia = tacGiaDAo.getAll();
        ds_thelaoi = theLoaiDao.getAll();
        list_tacGia = new Recycle_List_TacGia(this,ds_tacgia);
        list_nxb = new Listview_NXB(this,ds_nxb);
        list_theLoai = new Listview_TheLoai(this,ds_thelaoi);
        spinner_nxb.setAdapter(list_nxb);
        spinner_theloai.setAdapter(list_theLoai);
        spinner_tacgia.setAdapter(list_tacGia);
        if(bundle != null)
        {
            id_sach = bundle.getInt("select__");
            toolbar.setTitle("Cập nhật sách");
            sachsua = sachDAO.getSach(id_sach);
            tensach.setText(sachsua.getTensach());
            giathue.setText(String.valueOf(sachsua.getGiathue()));
            them_hinsach.setImageResource(getResources().getIdentifier(sachsua.getImages(),"mipmap",getPackageName()));
            spinner_tacgia.setSelection(sachsua.getMatacgia()-1);
            spinner_theloai.setSelection(sachsua.getMaloaisach()-1);
            spinner_nxb.setSelection(sachsua.getMaNXB()-1);
            btn_themsach.setText("Cập nhật sách");
        }else {
            toolbar.setTitle("Thêm sách");
        }
        btn_themsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sach sach = new Sach();
                sach.setTensach(tensach.getText().toString());
                sach.setMatacgia(((TacGia)spinner_tacgia.getSelectedItem()).getMatacgia());
                sach.setMaloaisach(((TheLoai)spinner_theloai.getSelectedItem()).getMatheloai());
                sach.setMaNXB(((NXB)spinner_nxb.getSelectedItem()).getMaNXB());
                sach.setGiathue(Integer.valueOf(giathue.getText().toString()));
                if(bundle == null)
                {
                    sach.setImages("book000");
                    boolean check = sachDAO.addSach(sach);
                    if(check)
                    {
                        Toast.makeText(AddSachAcitivty.this, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AddSachAcitivty.this, "Thêm sách thất bại", Toast.LENGTH_SHORT).show();

                    }

                }else {
                    sach.setMasach(id_sach);
                    sach.setImages(sachsua.getImages());
                    boolean check = sachDAO.updateSach(sach);
                    if(check)
                    {
                        Toast.makeText(AddSachAcitivty.this, "Cập nhật sách thành công", Toast.LENGTH_SHORT).show();
                    }else {Toast.makeText(AddSachAcitivty.this, "Cập nhật sách thất bại", Toast.LENGTH_SHORT).show();

                    }
                }

                finish();
            }
        });


    }
}