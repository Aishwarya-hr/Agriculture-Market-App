package com.example.miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    private ByteArrayOutputStream objectopStream;
    private byte[] imageInByte;
    public DatabaseHelper2(@Nullable Context context) {
        super(context, "Crop.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table CropData(CropID primary key,CropName text,Hectares text,Yeild text,Price text,Location text,Status text,Userid text,image blob not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists CropData");
    }

    public ArrayList<Model> getdataCrop(String s) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<Model> arrayList = new ArrayList<>();

            Cursor c = db.rawQuery("SELECT * FROM CropData where Status!=?", new String[]{s});
            if (c.getCount() != 0) {
                while (c.moveToNext()) {
                    String cid = c.getString(0);
                    String name = c.getString(1);
                    String hectare = c.getString(2);
                    String unit = c.getString(3);
                    String price = c.getString(4);
                    String location = c.getString(5);
                    String farmerid = c.getString(7);
                    byte[] imagebytes = c.getBlob(8);

                    Bitmap obj = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);
                    arrayList.add(new Model(name, unit, obj,cid,hectare,price,location,farmerid));
                }
                return arrayList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }



    //Checking Whether crop id is there or not
    public boolean CheckCropId(String uid,String cid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("SELECT Status FROM CropData where Userid=? and CropID=?", new String[] {uid,cid});
        if(cr.getCount()>0) return true;
        else return false;
    }
    //Checking wheather status is sold or not
    public boolean statusData(String uid,String cid,String state1) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("SELECT Status FROM CropData where Userid=? and CropID=? and Status=?", new String[] {uid,cid,state1});
        if(cr.getCount()>0) return true;
        else return false;
    }
    //reSet Status data from trader end
    public boolean setStatusSold(String s,String u)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("update CropData set Status=? where CropID=?",new String[]{s,u});
        if(c.getCount()==1) {
            return false;
        }
        else return true;
    }
    //reSet Status data
    public boolean setStatus(String s,String u)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("update CropData set Status=? where Userid=?",new String[]{s,u});
        if(c.getCount()==1) {
            return false;
        }
        else return true;
    }




    //retriving crop data
    public Cursor selectCropData(String uid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT CropID,CropName,Status FROM CropData where Userid=?", new String[] {uid});
        return cr;
    }

    public boolean insertCropData(String cid, String Cropname, String Hectares, String approxYield,
                                  String price, String location, String status, String uid, Bitmap x) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {


            ContentValues contentValues = new ContentValues();

            contentValues.put("CropID", cid);
            contentValues.put("CropName", Cropname);
            contentValues.put("Hectares", Hectares);
            contentValues.put("Yeild", approxYield);
            contentValues.put("Price", price);
            contentValues.put("Location", location);
            contentValues.put("Status", status);
            contentValues.put("Userid", uid);

            objectopStream=new ByteArrayOutputStream();
            x.compress(Bitmap.CompressFormat.JPEG,100,objectopStream);
            imageInByte=objectopStream.toByteArray();
            contentValues.put("image",imageInByte);

            db.insert("CropData", null, contentValues);

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
