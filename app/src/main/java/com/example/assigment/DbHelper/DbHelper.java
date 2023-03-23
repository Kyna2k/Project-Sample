package com.example.assigment.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "2tr6";
    private static final Integer DB_Version = 1;
    private static DbHelper newDbHelper;
    public static synchronized DbHelper getInstance(Context context)
    {
        if(newDbHelper == null)
        {
            newDbHelper = new DbHelper(context);
        }
        return newDbHelper;
    }
    public DbHelper(Context context)
    {
        super(context,DB_Name,null,DB_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Create_T_Tacgia = "Create table TACGIA(matacgia integer primary key autoincrement,images text, tentacgia text not null)";
        sqLiteDatabase.execSQL(Create_T_Tacgia);
        String Create_T_NXB = "Create table NXB(maNXB integer primary key autoincrement,images text,tenNXB text not null)";
        sqLiteDatabase.execSQL(Create_T_NXB);
        String Create_T_LoaiSach = "Create table LOAISACH(maloaisach integer primary key autoincrement,tenloaisach text not null)";
        sqLiteDatabase.execSQL(Create_T_LoaiSach);
        String Create_T_Sach = "Create table SACH(Masach integer primary key autoincrement, tensach text not null, images text,GiaThue Integer not null," +
                "matacgia integer,maNXB integer,maloaisach integer,foreign key(matacgia) references TACGIA(matacgia),foreign key(maNXB) references NXB(maNXB),foreign key(maloaisach) references LOAISACH(maloaisach))";
        sqLiteDatabase.execSQL(Create_T_Sach);
        String Create_T_User ="Create table USER(Mauser integer primary key autoincrement, hoten text not null, email text not null, avatar text, username text unique not null, password text not null, type integer not null)";
        sqLiteDatabase.execSQL(Create_T_User);
        String Create_T_Phieumuon = "Create table PHIEUMUON(MaPhieuMuon integer primary key autoincrement, MaQuanLyNhan integer,MaQuanLyDua integer, MaMember integer, Masach integer, ngaythue real, ngaytra real, trangthai integer not null," +
                "foreign key(MaQuanLyNhan) references USER(Mauser),foreign key(MaQuanLyDua) references USER(Mauser),foreign key(MaMember) references USER(Mauser),foreign key(Masach) references SACH(Masach))";
        sqLiteDatabase.execSQL(Create_T_Phieumuon);
        sqLiteDatabase.execSQL("INSERT INTO TACGIA VALUES(1,'hinh1','Nathan Phan'),(2,'hinh2','Huy Dep Trai')");
        sqLiteDatabase.execSQL("INSERT INTO NXB VALUES(1,'anhnxb','Nathan Phan'),(2,'anhnxb2','Huy Dep Trai')");
        sqLiteDatabase.execSQL("INSERT INTO LOAISACH VALUES(1,'Tình Yêu'),(2,'Tiêu Thuyết')");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES(1,'Lược sử loài người','hinh3',20000,1,1,1),(2,'Cậu bé may mắn và câu chuyện con cá','hinh3',20000,2,2,2)");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES(3,'Đắc nhân tâm','hinh7',50000,1,1,1),(4,'Tôi Yêu Em','avatar0',50000,2,2,2)");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES(5,'Mỗi lần vấp ngã là một lần trưởng thành','hinh8',6000,1,1,1),(6,'Tuổi trẻ băng khoang','hinh9',60000,2,2,2)");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES(7,'Sự Đẹp Trai của Nathan Phan','hinh3',6000,1,1,1),(8,'Dinhnt.com','sach15',60000,2,2,2)");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES(9,'Gọi cho tôi','hinh3',60000,1,1,1),(10,'Anh bạn à! Chúng ta giản hòa chứ','hinh9',60000,2,2,2)");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES(11,'Không Yêu Ngọc Anh','hinh5',60000,1,1,1),(12,'Your Mom','hinh7',60000,2,2,2)");
        sqLiteDatabase.execSQL("INSERT INTO USER VALUES(1,'Nathan Phan','vippro@gmail.com','avatar1','admin','admin',0),(2,'Huy Rain','huyrain@gmail.com','avatar2','huy124','huy123',1)" +
                ",(3,'Huy Phan','huyphan@gmail.com','avatar3','member123','member123',2),(4,'Huy Ngu','huyngu@gmail.com','avatar0','huy133','huy133',2),(6,'Huy Tèo','huytèo@gmail.com','avatar0','member133','member123',2)," +
                "(8,'Huy CHó','huycho@gmail.com','avatar0','member333','member333',2),(5,'Ngoc Anh','ngocanh@gmail.com','avatar0','ngocanh123','ngocanh123',2),(10,'Tri Dinh','dinhnt@gmail.com','avatar3','dinhnt','dinhnt',2)");
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(1,NULL,2,3,1,1664582401000,1667260799000,0),(2,4,2,3,2,1654041601000,1656719999000,1)");
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(3,NULL,2,3,1,1664582401000,1667260799000,0),(4,4,2,3,1,1654041601000,1656719999000,1)");
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(5,NULL,2,3,2,1664582401000,1667260799000,0),(6,4,2,3,3,1654041601000,1656719999000,1)");
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(7,NULL,2,4,4,1664582401000,1667260799000,0),(8,4,2,5,5,1654041601000,1656719999000,1)");
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(9,NULL,2,6,6,1664582401000,1667260799000,0),(10,4,2,3,7,1654041601000,1656719999000,1)");
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(11,NULL,2,8,8,1619827201000,1622419201000,0),(12,4,2,3,9,1619827201000,1622419201000,1)");
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(13,NULL,2,10,10,1619827201000,1622419201000,0),(14,4,2,3,11,1619827201000,1622419201000,1)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i!=i1)
        {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TACGIA");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NXB");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USER");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(sqLiteDatabase);
        }
    }
}
