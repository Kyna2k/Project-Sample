package com.example.assigment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment.DbHelper.DbHelper;
import com.example.assigment.model.Sach;
import com.example.assigment.model.TheLoai;

import java.util.ArrayList;

public class SachDAO {
    private DbHelper dbHelper;
    public SachDAO(Context context)
    {
        dbHelper = DbHelper.getInstance(context);
    }
    public ArrayList<Sach> getAll()
    {
        ArrayList<Sach> ds = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        Cursor cursor = database.rawQuery("SELECT masach,tensach,SACH.images,giathue,tg.tentacgia,nxb.tennxb,ls.tenloaisach FROM SACH INNER JOIN TACGIA tg on SACH.matacgia = tg.matacgia INNER JOIN NXB nxb on SACH.manxb = nxb.maNXB INNER JOIN LOAISACH ls on SACH.maloaisach = ls.maloaisach",null);
        try{
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    Sach sach = new Sach();
                    sach.setMasach(cursor.getInt(0));
                    sach.setTensach(cursor.getString(1));
                    sach.setImages(cursor.getString(2));
                    sach.setGiathue(cursor.getInt(3));
                    sach.setTentacgia(cursor.getString(4));
                    sach.setNxb(cursor.getString(5));
                    sach.setTenmaloai(cursor.getString(6));
                    ds.add(sach);
                    cursor.moveToNext();
                }
                database.setTransactionSuccessful();
            }else {
                throw new Exception();
            }

        }catch (Exception e)
        {
            Log.e("Loilaysach", e+"" );
        }finally {
            database.endTransaction();
        }
        return ds;
    }
    public ArrayList<Sach> getTop()
    {
        ArrayList<Sach> ds = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        Cursor cursor = database.rawQuery("SELECT s.masach, s.tensach, s.images,s.giathue,tg.tentacgia,nxb.tenNXB,nxb.images,ls.tenloaisach, COUNT(pm.masach),tg.images " +
                "from SACH s INNER JOIN TACGIA tg on s.matacgia = tg.matacgia " +
                "INNER JOIN NXB nxb ON s.manxb = nxb.manxb " +
                "INNER JOIN LOAISACH ls ON s.maloaisach = ls.maloaisach " +
                "Inner JOIN PHIEUMUON pm on s.masach = pm.masach " +
                "GROUP BY pm.masach  ORDER by COUNT(pm.masach) DESC LIMIT 10",null);
        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    Sach sach = new Sach();
                    sach.setMasach(cursor.getInt(0));
                    sach.setTensach(cursor.getString(1));
                    sach.setImages(cursor.getString(2));
                    sach.setGiathue(cursor.getInt(3));
                    sach.setTentacgia(cursor.getString(4));
                    sach.setNxb(cursor.getString(5));
                    sach.setImagesNXB(cursor.getString(6));
                    sach.setTenmaloai(cursor.getString(7));
                    sach.setSomuon(cursor.getInt(8));
                    sach.setImagesTacGia(cursor.getString(9));
                    ds.add(sach);
                    cursor.moveToNext();
                }
                database.setTransactionSuccessful();
            }else
            {
                throw new Exception();
            }
        }catch (Exception e)
        {
            Log.e("Loilaytop", e+"" );
        }finally {
            database.endTransaction();
        }
        return ds;
    }
    public ArrayList<Sach> getAll_Toktok()
    {
        ArrayList<Sach> ds = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        Cursor cursor = database.rawQuery("SELECT s.masach, s.tensach, s.images,s.giathue,tg.tentacgia,nxb.tenNXB,nxb.images,ls.tenloaisach,tg.images " +
                "from SACH s INNER JOIN TACGIA tg on s.matacgia = tg.matacgia " +
                "INNER JOIN NXB nxb ON s.manxb = nxb.manxb " +
                "INNER JOIN LOAISACH ls ON s.maloaisach = ls.maloaisach " ,null);
        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    Sach sach = new Sach();
                    sach.setMasach(cursor.getInt(0));
                    sach.setTensach(cursor.getString(1));
                    sach.setImages(cursor.getString(2));
                    sach.setGiathue(cursor.getInt(3));
                    sach.setTentacgia(cursor.getString(4));
                    sach.setNxb(cursor.getString(5));
                    sach.setImagesNXB(cursor.getString(6));
                    sach.setTenmaloai(cursor.getString(7));
                    sach.setImagesTacGia(cursor.getString(8));
                    ds.add(sach);
                    cursor.moveToNext();
                }
                database.setTransactionSuccessful();
            }else
            {
                throw new Exception();
            }
        }catch (Exception e)
        {
            Log.e("Loilaytop", e+"" );
        }finally {
            database.endTransaction();
        }
        return ds;
    }
    public Sach getSach(Integer id)
    {
        Sach sach = new Sach();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        Cursor cursor = database.rawQuery("SELECT s.masach, s.tensach, s.images,s.giathue,tg.tentacgia,nxb.tenNXB,nxb.images,ls.tenloaisach,tg.images,s.matacgia,s.manxb,s.maloaisach " +
                "from SACH s INNER JOIN TACGIA tg on s.matacgia = tg.matacgia " +
                "INNER JOIN NXB nxb ON s.manxb = nxb.manxb " +
                "INNER JOIN LOAISACH ls ON s.maloaisach = ls.maloaisach where s.masach = ?" ,new String[]{String.valueOf(id)});
        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                sach.setMasach(cursor.getInt(0));
                sach.setTensach(cursor.getString(1));
                sach.setImages(cursor.getString(2));
                sach.setGiathue(cursor.getInt(3));
                sach.setTentacgia(cursor.getString(4));
                sach.setNxb(cursor.getString(5));
                sach.setImagesNXB(cursor.getString(6));
                sach.setTenmaloai(cursor.getString(7));
                sach.setImagesTacGia(cursor.getString(8));
                sach.setMatacgia(cursor.getInt(9));
                sach.setMaNXB(cursor.getInt(10));
                sach.setMaloaisach(cursor.getInt(11));
            }
                database.setTransactionSuccessful();
            {
                throw new Exception();
            }
        }catch (Exception e)
        {
            Log.e("Loilaysach", e+"" );
        }finally {
            database.endTransaction();
        }
        return sach;
    }
    public boolean addSach(Sach sach)
    {
        boolean result = false;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try
        {
            ContentValues values = new ContentValues();
            values.put("tensach",sach.getTensach());
            values.put("images",sach.getImages());
            values.put("GiaThue",sach.getGiathue());
            values.put("matacgia",sach.getMatacgia());
            values.put("maNXB",sach.getMaNXB());
            values.put("maloaisach",sach.getMaloaisach());
            long row = database.insertOrThrow("SACH",null,values);
            result = row >=1;
            database.setTransactionSuccessful();
        }catch (Exception e)
        {
            Log.e("loidangky", e + "" );
        }finally {
            database.endTransaction();
        }
        return result;
    }
    public boolean updateSach(Sach sach)
    {
        Boolean result = true;
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put("tensach",sach.getTensach());
            contentValues.put("images",sach.getImages());
            contentValues.put("GiaThue",sach.getGiathue());
            contentValues.put("matacgia",sach.getMatacgia());
            contentValues.put("maNXB",sach.getMaNXB());
            contentValues.put("maloaisach",sach.getMaloaisach());
            long row = database.update("SACH",contentValues,"Masach = ?",new String[]{String.valueOf(sach.getMasach())});
            if(row >= 1)
            {
                database.setTransactionSuccessful();
                result = true;
            }
            else {
                throw new Exception();
            }

        }catch (Exception e)
        {
            result = false;
        }finally {
            database.endTransaction();
        }
        return result;
    }
    public boolean datele(Integer id)
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int row = database.delete("SACH","Masach = ?",new String[]{String.valueOf(id)});
        if(row > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
