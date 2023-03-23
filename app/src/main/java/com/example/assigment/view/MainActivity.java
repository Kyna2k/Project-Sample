package com.example.assigment.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assigment.R;
import com.example.assigment.adapter.ViewPager_Main;
import com.example.assigment.dao.SachDAO;
import com.example.assigment.dao.UserDAO;
import com.example.assigment.model.Sach;
import com.example.assigment.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View headerView;
    private ActionBarDrawerToggle drawerToggle;
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 layoutchinh;
    private UserDAO userDAO;
    private SharedPreferences sharedPreferences;
    private Integer id;
    private User user;
    private ImageView avatar;
    private Integer type_user;
    private TextView noidung_helo;
    private ViewPager_Main viewPager_apdater;
    private final int CODE_Result_Password = 0000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawelayout);
        navigationView = (NavigationView) findViewById(R.id.menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        bottomNavigationView = findViewById(R.id.nav_duoi);
        layoutchinh = findViewById(R.id.main);
        avatar = findViewById(R.id.avatar);
        sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        userDAO = new UserDAO(this);
        id = sharedPreferences.getInt("id",-1);
        type_user = sharedPreferences.getInt("type",-1);
        viewPager_apdater = new ViewPager_Main(this);
        layoutchinh.setAdapter(viewPager_apdater);
        bottomNavigationView = findViewById(R.id.nav_duoi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        layoutchinh.setCurrentItem(0);
                        break;
                    case R.id.top10:
                        layoutchinh.setCurrentItem(1);
                        break;
                    case R.id.canhan:
                        layoutchinh.setCurrentItem(2);

                }
                return true;
            }
        });
        layoutchinh.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position)
                {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.home);

                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.top10);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.canhan);
                        break;
                }
            }
        });
        Menu menu_item = navigationView.getMenu();
        switch (type_user)
        {
            case 1:
                menu_item.getItem(4).setVisible(false);
                break;
            case 2:
                menu_item.getItem(2).setVisible(false);
                menu_item.getItem(3).setVisible(false);
                menu_item.getItem(4).setVisible(false);
                menu_item.getItem(5).setVisible(false);
                break;
            default:
                break;

        }
        navigationView.setCheckedItem(R.id.home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.xem_phieumuon:
                        startActivity(new Intent(MainActivity.this,XemPhieuMuonActivity.class));
                        break;
                    case R.id.quanly_phieumuon:
                        startActivity(new Intent(MainActivity.this,QuanLyPhieuMuonActivity.class));
                        break;
                    case R.id.quanly_sach:
                        startActivity(new Intent(MainActivity.this,HomeQuanLySachActivity.class));

                        break;
                    case R.id.quanly_user:
                        startActivity(new Intent(MainActivity.this,QuanLyUserActivity.class));

                        break;
                    case R.id.xem_thongke:
                        startActivity(new Intent(MainActivity.this,ThongKeDoanhThu.class));

                        break;
                    case R.id.log_out:
                        finish();
                        break;
                    default:
                        break;


                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = userDAO.getUser(id);
        Glide.with(this).load(this.getResources().getIdentifier(user.getAvatar(),"mipmap",this.getPackageName())).circleCrop().into(avatar);
        headerView = navigationView.getHeaderView(0);
        ImageView avatar_header = headerView.findViewById(R.id.avatar_user);
        TextView name = headerView.findViewById(R.id.name);
        TextView email = headerView.findViewById(R.id.email);
        Glide.with(this).load(this.getResources().getIdentifier(user.getAvatar(),"mipmap",this.getPackageName())).circleCrop().into(avatar_header);
        name.setText(user.getHoTen());
        email.setText(user.getEmail());

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
    public ArrayList<Sach> getAllSach()
    {
        SachDAO sachDAO = new SachDAO(this);
        ArrayList<Sach> ds = sachDAO.getAll();
        return ds;
    }
    public ArrayList<Sach> getTop()
    {
        SachDAO sachDAO = new SachDAO(this);
        ArrayList<Sach> ds = sachDAO.getTop();
        return ds;
    }
    public User getUser()
    {
        return user;
    }
    public void capnhatthongtin(){
       getData.launch(new Intent(this,UpdataProfileAcitivity.class));
    }
    public void doimatkhau()
    {
        Intent intent = new Intent(this,ChangePassActivity.class);
        getData.launch(intent);
    }
    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            switch (result.getResultCode())
            {
                case 555:
                    finish();
                    break;
                case 666:
                    break;
                default:
            }
        }
    });


}