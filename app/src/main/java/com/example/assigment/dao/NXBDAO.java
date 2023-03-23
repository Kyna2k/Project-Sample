package com.example.assigment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment.DbHelper.DbHelper;
import com.example.assigment.model.NXB;
import com.example.assigment.model.TacGia;

import java.util.ArrayList;

public class NXBDAO {
    private DbHelper dbHelper;
    public NXBDAO(Context context)
    {
        dbHelper = DbHelper.getInstance(context);
    }
    public ArrayList<NXB> getAll()
    {
        ArrayList<NXB> ds = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        Cursor cursor = database.rawQuery("SELECT * FROM NXB",null);
        try{
            if(cursor.getCount()>0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    NXB nxb = new NXB();
                    nxb.setMaNXB(cursor.getInt(0));
                    nxb.setImages(cursor.getString(1));
                    nxb.setTenNXB(cursor.getString(2));
                    ds.add(nxb);
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
    public boolean addnxb(NXB nxb)
    {
        boolean result = false;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try
        {
            ContentValues values = new ContentValues();
            values.put("images",nxb.getImages());
            values.put("tenNXB",nxb.getTenNXB());
            long row = database.insertOrThrow("NXB",null,values);
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
    public boolean updateNXB(NXB nxb)
    {
        Boolean result = true;
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put("images",nxb.getImages());
            contentValues.put("tenNXB",nxb.getTenNXB());
            long row = database.update("NXB",contentValues,"maNXB = ?",new String[]{String.valueOf(nxb.getMaNXB())});
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
        int row = database.delete("NXB","maNXB = ?",new String[]{String.valueOf(id)});
        if(row > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
