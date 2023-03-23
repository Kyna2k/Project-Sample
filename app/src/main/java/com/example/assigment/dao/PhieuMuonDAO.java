package com.example.assigment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment.DbHelper.DbHelper;
import com.example.assigment.model.PhieuMuon;
import com.example.assigment.model.Sach;
import com.example.assigment.model.User;

import java.util.ArrayList;

public class PhieuMuonDAO {
    private DbHelper dbHelper;
    public PhieuMuonDAO(Context context){
        dbHelper = DbHelper.getInstance(context);
    }
    public ArrayList<PhieuMuon> getALl(Integer id)
    {
        Cursor cursor;
        ArrayList<PhieuMuon> ds = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        if(id == null)
        {
            cursor = database.rawQuery("  SELECT MaPhieuMuon,MaQuanLyNhan,MaQuanLyDua,MaMember,PHIEUMUON.Masach,ngaythue,ngaytra,trangthai,tensach,SACH.images,GiaThue,tentacgia,USER.username,NXB.images,USER.mauser,SACH.masach " +
                    "  FROM PHIEUMUON INNER JOIN SACH on PHIEUMUON.masach = SACH.masach " +
                    "  INNER JOIN TACGIA on SACH.matacgia = TACGIA.matacgia " +
                    "  INNER JOIN NXB on SACH.manxb = NXB.maNXB " +
                    "  INNER JOIN USER ON USER.mauser = PHIEUMUON.mamember;",null);
        }else {
            cursor = database.rawQuery("   SELECT MaPhieuMuon,MaQuanLyNhan,MaQuanLyDua,MaMember,PHIEUMUON.Masach,ngaythue,ngaytra,trangthai,tensach,SACH.images,GiaThue,tentacgia,USER.username,NXB.images,USER.mauser,SACH.masach " +
                    "  FROM PHIEUMUON INNER JOIN SACH on PHIEUMUON.masach = SACH.masach " +
                    "  INNER JOIN TACGIA on SACH.matacgia = TACGIA.matacgia " +
                    "  INNER JOIN NXB on SACH.manxb = NXB.maNXB " +
                    "  INNER JOIN USER ON USER.mauser = PHIEUMUON.mamember Where MaMember = ?",new String[]{String.valueOf(id)});
        }
        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    PhieuMuon phieuMuon = new PhieuMuon();
                    User user = new User();
                    Sach sach = new Sach();
                    phieuMuon.setId(cursor.getInt(0));
                    phieuMuon.setMaQuanLyNhan(cursor.getInt(1));
                    phieuMuon.setMaQuanLyDua(cursor.getInt(2));
                    phieuMuon.setMaMember(cursor.getInt(3));
                    phieuMuon.setMasach(cursor.getInt(4));
                    phieuMuon.setNgaythue(cursor.getLong(5));
                    phieuMuon.setNgaytra(cursor.getLong(6));
                    phieuMuon.setTrangthai(cursor.getInt(7));
                    sach.setTensach(cursor.getString(8));
                    sach.setImages(cursor.getString(9));
                    sach.setGiathue(cursor.getInt(10));
                    sach.setTentacgia(cursor.getString(11));
                    user.setUsername(cursor.getString(12));
                    sach.setImagesNXB(cursor.getString(13));
                    user.setId(cursor.getInt(14));
                    sach.setMasach(cursor.getInt(15));
                    phieuMuon.setSach(sach);
                    phieuMuon.setUser(user);
                    ds.add(phieuMuon);
                    cursor.moveToNext();
                }
                database.setTransactionSuccessful();
            }else {
                throw new Exception();
            }
        }catch (Exception E)
        {
            Log.e("loiphieumuon",E +" "  );
        }finally {
            database.endTransaction();
        }
        return ds;
    }
    public boolean themPhieuMuon(PhieuMuon phieuMuon)
    {
        Boolean result = false;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put("MaQuanLyDua",phieuMuon.getMaQuanLyDua());
            values.put("MaMember",phieuMuon.getMaMember());
            values.put("Masach",phieuMuon.getMasach());
            values.put("ngaythue",phieuMuon.getNgaythue());
            values.put("trangthai",phieuMuon.getTrangthai());
            values.put("MaQuanLyNhan",phieuMuon.getMaQuanLyNhan());
            values.put("ngaytra",phieuMuon.getNgaytra());
            long row = database.insertOrThrow("PHIEUMUON",null,values);
            result = row >=1;
            database.setTransactionSuccessful();
        }catch (Exception e)
        {

        }finally {
            database.endTransaction();
        }

        return result;
    }
    public boolean capnhatPhieuMuon(PhieuMuon phieuMuon)
    {
        Boolean result = false;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put("MaQuanLyDua",phieuMuon.getMaQuanLyDua());
            values.put("MaMember",phieuMuon.getMaMember());
            values.put("Masach",phieuMuon.getMasach());
            values.put("ngaythue",phieuMuon.getNgaythue());
            values.put("trangthai",phieuMuon.getTrangthai());
            values.put("MaQuanLyNhan",phieuMuon.getMaQuanLyNhan());
            values.put("ngaytra",phieuMuon.getNgaytra());
            long row = database.update("PHIEUMUON",values,"MaPhieuMuon = ?",new String[]{String.valueOf(phieuMuon.getId())});
            result = row >=1;
            database.setTransactionSuccessful();
        }catch (Exception e)
        {

        }finally {
            database.endTransaction();
        }

        return result;
    }
    public PhieuMuon getPhieuMuon(Integer id)
    {
        Cursor cursor;
        PhieuMuon phieuMuon = new PhieuMuon();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        cursor = database.rawQuery("  SELECT MaPhieuMuon,MaQuanLyNhan,MaQuanLyDua,MaMember,PHIEUMUON.Masach,ngaythue,ngaytra,trangthai,tensach,SACH.images,GiaThue,tentacgia,USER.username,NXB.images,USER.mauser,SACH.masach " +
                "  FROM PHIEUMUON INNER JOIN SACH on PHIEUMUON.masach = SACH.masach " +
                "  INNER JOIN TACGIA on SACH.matacgia = TACGIA.matacgia " +
                "  INNER JOIN NXB on SACH.manxb = NXB.maNXB " +
                "  INNER JOIN USER ON USER.mauser = PHIEUMUON.mamember where MaPhieuMuon = ?",new String[]{String.valueOf(id)});
        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                User user = new User();
                Sach sach = new Sach();
                phieuMuon.setId(cursor.getInt(0));
                phieuMuon.setMaQuanLyNhan(cursor.getInt(1));
                phieuMuon.setMaQuanLyDua(cursor.getInt(2));
                phieuMuon.setMaMember(cursor.getInt(3));
                phieuMuon.setMasach(cursor.getInt(4));
                phieuMuon.setNgaythue(cursor.getLong(5));
                phieuMuon.setNgaytra(cursor.getLong(6));
                phieuMuon.setTrangthai(cursor.getInt(7));
                sach.setTensach(cursor.getString(8));
                sach.setImages(cursor.getString(9));
                sach.setGiathue(cursor.getInt(10));
                sach.setTentacgia(cursor.getString(11));
                user.setUsername(cursor.getString(12));
                sach.setImagesNXB(cursor.getString(13));
                user.setId(cursor.getInt(14));
                sach.setMasach(cursor.getInt(15));
                phieuMuon.setSach(sach);
                phieuMuon.setUser(user);
                database.setTransactionSuccessful();
            }else {
                throw new Exception();
            }
        }catch (Exception E)
        {
            Log.e("loiphieumuon",E +" "  );
        }finally {
            database.endTransaction();
        }
        return phieuMuon;
    }
    public boolean deletePhieumuon(Integer id)
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Integer row = database.delete("PHIEUMUON","MaPhieuMuon = ?",new String[]{String.valueOf(id)});
        if(row > 0)
        {
            return true;
        }else {
            return false;
        }
    }
    public Integer thongkedoanhthu(Long batdau, Long kethu)
    {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select Sum(GiaThue) FROM PHIEUMUON INNER JOIN SACH on PHIEUMUON.masach = SACH.Masach " +
                "WHERE ngaythue BETWEEN  ? and  ?",new String[]{String.valueOf(batdau),String.valueOf(kethu)});
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
