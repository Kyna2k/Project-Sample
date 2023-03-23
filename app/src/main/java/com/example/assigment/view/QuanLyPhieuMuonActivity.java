package com.example.assigment.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.assigment.R;
import com.example.assigment.adapter.Recycle_list_phieumuon;
import com.example.assigment.dao.PhieuMuonDAO;
import com.example.assigment.dao.SachDAO;
import com.example.assigment.dao.UserDAO;
import com.example.assigment.model.PhieuMuon;
import com.example.assigment.model.Sach;
import com.example.assigment.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class QuanLyPhieuMuonActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Recycle_list_phieumuon recycle_list_phieumuon;
    private PhieuMuonDAO phieuMuonDAO;
    private RecyclerView recyclerView;
    private ArrayList<PhieuMuon> ds;
    private FloatingActionButton btn_add;
    private Integer id_user,id_sach_ne,id,type_ne;
    private PhieuMuon phieuMuon2;
    private Calendar myCalendar= Calendar.getInstance();
    private Calendar myCalendar2= Calendar.getInstance();
    private TextView id_ne,type,username,tenuser,email;
    private ImageView hinhsach,btn_moreinfo,hinhNXB;
    private TextView xephang,tensach,tentacgia,giathue,soluong;
    private ImageView avatar;
    private UserDAO userDAO;
    private SachDAO sachDAO;
    private Long time_ne1,time_ne2;
    private SharedPreferences sharedPreferences;
    private EditText ngay,ngay_tra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phieu_muon);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Quản lý phiếu mượn");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        int x = 2;
        userDAO = new UserDAO(this);
        sachDAO =new SachDAO(this);
        recyclerView = findViewById(R.id.rec_list);
        phieuMuonDAO = new PhieuMuonDAO(this);
        btn_add = findViewById(R.id.btn_add_phieumuon);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogMuonPhieu(null,null);
            }
        });
        sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        id = sharedPreferences.getInt("id",-1);
        type_ne = sharedPreferences.getInt("type",-1);
        getData(null);
    }
    private void getData(Integer vitri)
    {
        ds = phieuMuonDAO.getALl(null);
        recycle_list_phieumuon = new Recycle_list_phieumuon(this,ds,type_ne);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recycle_list_phieumuon);
        if(vitri != null)
        {
            recyclerView.scrollToPosition(vitri);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void dialogMuonPhieu(Integer id_phieumuon,Integer position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_phieumuon,null);
        builder.setView(view);
        View chon_member = view.findViewById(R.id.thongtinmember);
        View chon_sach =view.findViewById(R.id.thongtinsach);
        Guideline guideline2 =view.findViewById(R.id.guideline2);
        id_ne = chon_member.findViewById(R.id.id_ne);
        type = chon_member.findViewById(R.id.type);
        username = chon_member.findViewById(R.id.username);
        tenuser = chon_member.findViewById(R.id.tenuser);
        email = chon_member.findViewById(R.id.email);
        avatar = chon_member.findViewById(R.id.avatar);
        hinhsach = chon_sach.findViewById(R.id.hinhsach);
        hinhNXB = chon_sach.findViewById(R.id.hinhNXB);
        xephang = chon_sach.findViewById(R.id.xephang);
        tensach = chon_sach.findViewById(R.id.tensach);
        tentacgia = chon_sach.findViewById(R.id.tentacgia);
        giathue = chon_sach.findViewById(R.id.giathue);
        btn_moreinfo = chon_sach.findViewById(R.id.btn_moreinfo);
        btn_moreinfo.setVisibility(View.GONE);
        ngay = view.findViewById(R.id.nhapngay);
        ngay_tra = view.findViewById(R.id.ngaytra);
        Button btn_them = view.findViewById(R.id.btn_comfrim);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        View btn_chonmember = chon_member.findViewWithTag("card_thongtinuser");
        CardView btn_chonsach = chon_sach.findViewWithTag("chonlaysach");
        btn_chonsach.setCardElevation(5f);
        if(id_phieumuon == null)
        {
            btn_them.setText("Thêm phiếu");
            id_ne.setText("");
            type.setText("Vui lòng chọn member");
            username.setText("Chưa có thông tin member");
            tensach.setText("Vui lòng chọn sách");
            ngay.setVisibility(View.GONE);
            guideline2.setGuidelinePercent(0);
            myCalendar= Calendar.getInstance();
            updateLabel();
        }else {
            phieuMuon2 = phieuMuonDAO.getPhieuMuon(id_phieumuon);
            getMember(phieuMuon2.getUser().getId());
            getSachMuon(phieuMuon2.getMasach());
            myCalendar.setTimeInMillis(phieuMuon2.getNgaythue());
            myCalendar2.setTimeInMillis(phieuMuon2.getNgaytra());
            id_user = phieuMuon2.getUser().getId();
            id_sach_ne = phieuMuon2.getMasach();
            updateLabel();
            updateLabel2();
            btn_them.setText("Cập nhật");
        }
        btn_chonmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLyPhieuMuonActivity.this,QuanLyUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("lay",888);
                intent.putExtras(bundle);
                getData.launch(intent);
            }
        });
        btn_chonsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLyPhieuMuonActivity.this,QuanLySachActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("lay_sach",999);
                intent.putExtras(bundle);
                getData.launch(intent);
            }
        });
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
                new DatePickerDialog(QuanLyPhieuMuonActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
                new DatePickerDialog(QuanLyPhieuMuonActivity.this,date_tra,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLabel();
                updateLabel2();
                PhieuMuon phieuMuon = new PhieuMuon();
                phieuMuon.setMasach(id_sach_ne);
                phieuMuon.setTrangthai(0);
                phieuMuon.setMaQuanLyDua(id);
                phieuMuon.setMaMember(id_user);
                phieuMuon.setNgaythue(time_ne1);
                phieuMuon.setNgaytra(time_ne2);
                if(id_phieumuon == null)
                {
                    phieuMuon.setMaQuanLyNhan(null);
                    boolean check = phieuMuonDAO.themPhieuMuon(phieuMuon);
                    if(check)
                    {
                        getData(position);
                        Toast.makeText(QuanLyPhieuMuonActivity.this, "Đã thêm phiếu mượn", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(QuanLyPhieuMuonActivity.this, "Lỗi không thêm được phiếu mượn", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    phieuMuon2.setMasach(id_sach_ne);
                    phieuMuon2.setMaQuanLyDua(id);
                    phieuMuon2.setMaMember(id_user);
                    phieuMuon2.setNgaythue(time_ne1);
                    phieuMuon2.setNgaytra(time_ne2);
                    boolean check = phieuMuonDAO.capnhatPhieuMuon(phieuMuon2);
                    if(check)
                    {

                        getData(position);
                        Toast.makeText(QuanLyPhieuMuonActivity.this, "Đã cập nhật phiếu mượn", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(QuanLyPhieuMuonActivity.this, "Lỗi không cập nhật được phiếu mượn", Toast.LENGTH_SHORT).show();
                    }
                }

                alertDialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    Bundle bundle = intent.getExtras();
                    switch (result.getResultCode())
                    {
                        case 1809:
                            id_user = bundle.getInt("id_user",-1);
                            getMember(id_user);
                            break;
                        case 2000:
                            id_sach_ne = bundle.getInt("id_sach",-1);
                            getSachMuon(id_sach_ne);
                        default:
                            break;
                    }
                }
            });
    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        ngay.setText(dateFormat.format(myCalendar.getTime()));
        time_ne1 = myCalendar.getTimeInMillis();
    }
    private void updateLabel2(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        ngay_tra.setText(dateFormat.format(myCalendar2.getTime()));
        time_ne2 = myCalendar2.getTimeInMillis();
    }
    private void getMember(Integer id_member)
    {
        User user_member = userDAO.getUser(id_member);
        Glide.with(QuanLyPhieuMuonActivity.this).load(getResources().getIdentifier(user_member.getAvatar(),"mipmap",getPackageName())).circleCrop().into(avatar);
        id_ne.setText(String.valueOf(user_member.getId()));
        username.setText(user_member.getEmail());
        tenuser.setText(user_member.getHoTen());
        email.setText(user_member.getEmail());
    }
    private void getSachMuon(Integer id_sach_muon)
    {
        Sach sach = sachDAO.getSach(id_sach_muon);
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        tensach.setText(sach.getTensach());
        tentacgia.setText(sach.getTentacgia());
        String tien = nf.format(sach.getGiathue());
        giathue.setText(tien.replace("$","").substring(0,tien.indexOf(".")-1)+ " VNĐ");
        hinhsach.setImageResource(getResources().getIdentifier(sach.getImages(),"mipmap",getPackageName()));
        hinhNXB.setImageResource(getResources().getIdentifier(sach.getImagesNXB(),"mipmap",getPackageName()));
        xephang.setText(String.valueOf(sach.getMasach()));
    }
    public void setTrangThai_ne(Integer id_phieumuon_xacnhan,Integer position)
    {
        PhieuMuon phieuMuon_ne = phieuMuonDAO.getPhieuMuon(id_phieumuon_xacnhan);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.diaglog_comfirm,null);
        TextView noidung = view.findViewById(R.id.noidung);
        Button comfrim = view.findViewById(R.id.btn_comfrim);
        Button cancel = view.findViewById(R.id.btn_cancel);
        noidung.setText("Bạn xác nhận đã thu lại sách?");
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        comfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phieuMuon_ne.setTrangthai(1);
                phieuMuon_ne.setMaQuanLyNhan(id);
                Boolean check = phieuMuonDAO.capnhatPhieuMuon(phieuMuon_ne);
                if(check)
                {
                    alertDialog.dismiss();
                    Toast.makeText(QuanLyPhieuMuonActivity.this, "Đã thu lại sách  thành công", Toast.LENGTH_SHORT).show();
                    getData(position);
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

    public void dialog_menu(Integer phieuMuon_delamgi,Integer position){
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
                dialogMuonPhieu(phieuMuon_delamgi,position);
                alertDialog.dismiss();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa_ne(phieuMuon_delamgi,position);
                alertDialog.dismiss();

            }
        });
    }
    public void xoa_ne(Integer id_phieumuon_xacnhan,Integer position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.diaglog_comfirm, null);
        TextView noidung = view.findViewById(R.id.noidung);
        Button comfrim = view.findViewById(R.id.btn_comfrim);
        Button cancel = view.findViewById(R.id.btn_cancel);
        noidung.setText("Bạn xác nhận xóa phiếu mượn?");
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        comfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = phieuMuonDAO.deletePhieumuon(id_phieumuon_xacnhan);
                if (check) {
                    Toast.makeText(QuanLyPhieuMuonActivity.this, "Đã xóa phiếu mượn", Toast.LENGTH_SHORT).show();
                    getData(position-1);
                }else {
                    Toast.makeText(QuanLyPhieuMuonActivity.this, "Lỗi không xóa được phiếu", Toast.LENGTH_SHORT).show();

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