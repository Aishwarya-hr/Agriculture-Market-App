package com.example.miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.FileInputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "miniProject.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table SignUpData(UserName text primary key,Password int)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists SignUpData");


    }




    //insertion to SignUpData table
    public boolean insertSignUp(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("UserName",username);
        contentValues.put("Password",password);
        long ins=db.insert("SignUpData",null,contentValues);
        if(ins==1)
            return false;
        else return true;
    }
    //checking whether username exist earlier or not
    public boolean checkexistent(String username)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from SignUpData where UserName=?",new String[] {username});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    //Aunthenticating username and password
    public boolean signVerify(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from SignUpData where UserName=? and Password=?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    //resetting the password when user forget it.
    public boolean resetPass(String u,String p)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("update SignUpData set Password=? where UserName=?",new String[]{p,u});
        if(c.getCount()==1) {
            return false;
        }
        else return true;
    }


}
