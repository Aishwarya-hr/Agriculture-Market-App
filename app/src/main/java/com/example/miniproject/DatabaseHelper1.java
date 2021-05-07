package com.example.miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;



public class DatabaseHelper1 extends SQLiteOpenHelper {


    public DatabaseHelper1(@Nullable Context context) {
        super(context, "profile.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table ProfileData(uid text primary key,Type text NOT NULL,FullName text,Gender text NOT NULL,DOB text,ContactNum text,Street text,City text,State text,Email text,pincode text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists ProfileData");

    }
//retriving profile data
    public Cursor selectProfileData(String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM ProfileData where uid=?", new String[] {uid});
        return cr;
    }


    //insertion to ProfileData table
    public boolean insertProfileData(String uid,String type,String name, String gender,
                                     String dob,String num,String street,String city,String state,String mail,String pin)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("uid",uid);
        contentValues.put("Type",type);
        contentValues.put("FullName",name);
        contentValues.put("Gender",gender);
        contentValues.put("DOB",dob);
        contentValues.put("ContactNum",num);
        contentValues.put("Street",street);
        contentValues.put("City",city);
        contentValues.put("State",state);
        contentValues.put("Email",mail);
        contentValues.put("pincode",pin);

        long ins=db.insert("ProfileData",null,contentValues);
        if(ins==1)
            return false;
        else return true;
    }
}
