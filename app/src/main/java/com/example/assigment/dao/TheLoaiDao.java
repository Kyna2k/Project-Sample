package com.example.assigment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment.DbHelper.DbHelper;
import com.example.assigment.model.NXB;
import com.example.assigment.model.TheLoai;

import java.util.ArrayList;

public class TheLoaiDao {
    private DbHelper dbHelper;
    public TheLoaiDao(Context context)
    {
        dbHelper = DbHelper.getInstance(context);
    }
    public ArrayList<TheLoai> getAll()
    {
        ArrayList<TheLoai> ds = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        Cursor cursor = database.rawQuery("SELECT * FROM LOAISACH",null);
        try{
            if(cursor.getCount()>0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    TheLoai theLoai = new TheLoai(cursor.getInt(0),cursor.getString(1));
                    ds.add(theLoai);
                    cursor.moveToNext();
                }
                database.setTransactionSuccessful();
            }
        }catch (Exception e)
        {

        }finally {
            database.endTransaction();
        }
        return ds;
    }
    public boolean addtheloai(TheLoai theLoai)
    {
        boolean result = false;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try
        {
            ContentValues values = new ContentValues();
            values.put("tenloaisach",theLoai.getTentheloai());
            long row = database.insertOrThrow("LOAISACH",null,values);
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
    public boolean updateTheLoai(TheLoai theLoai)
    {
        Boolean result = true;
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put("tenloaisach",theLoai.getTentheloai());

            long row = database.update("LOAISACH",contentValues,"maloaisach = ?",new String[]{String.valueOf(theLoai.getMatheloai())});
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
        int row = database.delete("LOAISACH","maloaisach = ?",new String[]{String.valueOf(id)});
        if(row > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
