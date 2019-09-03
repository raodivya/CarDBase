package com.mkumar.m.cardatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final String databasename="CarsDB.db";
    public static final String tablename="car_table";
    public static final String col1="Id";
    public static final String col2="Car";
    public static final String col3="Owner";
    public static final String col4="Type";
    public static final String col5="Reg";
    public static final String col6="Ins";
    public static final String col7="Pol";

    public DBHelper(Context c)
    {
        super(c,databasename,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tablename+" (Id integer primary key autoincrement,Car Text,Owner Text,Type Text,Reg Text,Ins Text,Pol Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tablename);
    }
    public boolean insert(String car,String owner,String type,String reg,String ins,String pol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, car);
        cv.put(col3, owner);
        cv.put(col4, type);
        cv.put(col5, reg);
        cv.put(col6, ins);
        cv.put(col7, pol);

        long res = db.insert(tablename, null, cv);
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(tablename,"Id=?",new String[]{id});
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(String car,String owner,String type,String reg,String ins,String pol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, car);
        cv.put(col3, owner);
        cv.put(col4, type);
        cv.put(col5, reg);
        cv.put(col6, ins);
        cv.put(col7, pol);

        long res = db.update(tablename,cv,"Car=?",new String[]{car});
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor displayAll() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res=db.rawQuery("select * from car_table",null);
        return res;
    }
    public Cursor displaySingleByCar(String car) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res=db.rawQuery("select * from car_table where Car="+car,null);
        return res;
    }
    public boolean deletebycar(String car) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(tablename,"Car=?",new String[]{car});
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }
}
