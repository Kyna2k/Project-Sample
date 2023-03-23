package com.example.assigment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment.DbHelper.DbHelper;
import com.example.assigment.model.User;

import java.util.ArrayList;

public class UserDAO {
    private DbHelper dbHelper;
    private SharedPreferences sharedPreferences;
    public UserDAO(Context context)
    {
        dbHelper = DbHelper.getInstance(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
    }
    public boolean Login(User user)
    {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select Mauser,type from USER where username = ? and password = ?",new String[]{user.getUsername(),user.getPassword()});
        cursor.getCount();
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id",cursor.getInt(0));
            editor.putInt("type",cursor.getInt(1));
            editor.apply();
            return true;
        }
        return false;
    }
    public boolean dangky(User user)
    {
        boolean result = false;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try
        {
            ContentValues values = new ContentValues();
            values.put("hoten",user.getHoTen());
            values.put("email",user.getEmail());
            values.put("avatar",user.getAvatar());
            values.put("username",user.getUsername());
            values.put("password",user.getPassword());
            values.put("type",user.getType());
            long row = database.insertOrThrow("USER",null,values);
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
    public User getUser(Integer id)
    {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from USER where Mauser = ?",new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        User user = new User(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(0));
        return user;
    }
    public boolean changePass(User user)
    {
        Boolean result = true;
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hoten",user.getHoTen());
            contentValues.put("email",user.getEmail());
            contentValues.put("avatar",user.getAvatar());
            contentValues.put("password",user.getPassword());
            long row = database.update("USER",contentValues,"Mauser = ?",new String[]{String.valueOf(user.getId())});
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
    public ArrayList<User> getALL()
    {
        ArrayList<User> ds = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        Cursor cursor = database.rawQuery("SELECT * FROM user ORDER BY type",null);
        try{
            if(cursor.getCount()>0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    User user = new User();
                    user.setId(cursor.getInt(0));
                    user.setHoTen(cursor.getString(1));
                    user.setEmail(cursor.getString(2));
                    user.setAvatar(cursor.getString(3));
                    user.setUsername(cursor.getString(4));
                    user.setPassword(cursor.getString(5));
                    user.setType(cursor.getInt(6));
                    ds.add(user);
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
    public boolean deleteUser(Integer id)
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int row = database.delete("USER","Mauser = ?",new String[]{String.valueOf(id)});
        if(row > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
