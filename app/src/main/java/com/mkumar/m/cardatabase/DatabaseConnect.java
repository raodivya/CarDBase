package com.mkumar.m.cardatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnect extends SQLiteOpenHelper {
    public static final String databasename="User1.db";
    public static final String tablename="Users";
    public static final String col1="Id";
    public static final String col2="Uname";
    public static final String col3="Phone";
    public static final String col4="Gender";
    public static final String col5="Passwd";
    public DatabaseConnect(Context c)
    {
        super(c,databasename,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tablename+" (Id integer primary key autoincrement,Uname Text,Phone Text,Gender Text,Passwd Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tablename);
    }
    public boolean insert(String uname, String phone, String gender, String passwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, uname);
        cv.put(col3, phone);
        cv.put(col4, gender);
        cv.put(col5, passwd);

        long res = db.insert(tablename, null, cv);
        if (res > 0) {
            return true;
        } else {
            return false;
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

    public boolean update(String id,String uname, String phone, String gender, String passwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, uname);
        cv.put(col3, phone);
        cv.put(col4, gender);
        cv.put(col5, passwd);

        long res = db.update(tablename,cv,"Id=?",new String[]{id});
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor displayAll() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res=db.rawQuery("select * from Users",null);
        return res;
    }
    public Cursor displaySingleById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res=db.rawQuery("select * from Users where Id="+id,null);
        return res;
    }
    public Cursor displaySingleByUname(String un) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res=db.rawQuery("select * from Users where Uname='"+un.trim()+"'",null);
        return res;
    }
    public Cursor displaySingleByUnamePassword(String un,String pw) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res=db.rawQuery("select * from Users where Uname='"+un.trim()+"'"+" and Passwd='"+pw.trim()+"'",null);
        return res;
    }
}
