package com.example.assigment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment.DbHelper.DbHelper;
import com.example.assigment.model.TacGia;
import com.example.assigment.model.User;

import java.util.ArrayList;

public class TacGiaDAo {
    private DbHelper dbHelper;
    public TacGiaDAo(Context context)
    {
        dbHelper = DbHelper.getInstance(context);
    }

    public ArrayList<TacGia> getAll()
    {
        ArrayList<TacGia> ds = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        Cursor cursor = database.rawQuery("SELECT * FROM Tacgia",null);
        try{
            if(cursor.getCount()>0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    TacGia tacGia = new TacGia();
                    tacGia.setMatacgia(cursor.getInt(0));
                    tacGia.setImages(cursor.getString(1));
                    tacGia.setTentacgia(cursor.getString(2));
                    ds.add(tacGia);
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
    public boolean addTacgia(TacGia tacGia)
    {
        boolean result = false;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try
        {
            ContentValues values = new ContentValues();
            values.put("images",tacGia.getImages());
            values.put("tentacgia",tacGia.getTentacgia());
            long row = database.insertOrThrow("Tacgia",null,values);
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
    public boolean updateTacGia(TacGia tacGia)
    {
        Boolean result = true;
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put("images",tacGia.getImages());
            contentValues.put("tentacgia",tacGia.getTentacgia());
            long row = database.update("Tacgia",contentValues,"matacgia = ?",new String[]{String.valueOf(tacGia.getMatacgia())});
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
    public boolean delateTacgia(Integer id)
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int row = database.delete("Tacgia","matacgia = ?",new String[]{String.valueOf(id)});
        if(row > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
