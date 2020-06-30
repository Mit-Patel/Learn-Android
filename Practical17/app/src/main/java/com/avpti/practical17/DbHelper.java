package com.avpti.practical17;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "TEST.db";
    private static final String TABLE_NAME = "student";
    private static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (id integer primary key ,name text, email text, cpi double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(int id, String name, String email, double cpi) {
        SQLiteDatabase db = getWritableDatabase();

        String s = "insert into student values(?,?,?,?)";

        try {
            db.execSQL(s, new String[]{id + "", name, email, cpi + ""});
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean update(int id, String name, String email, double cpi) {
        SQLiteDatabase db = getWritableDatabase();

        String s = "update student set name= ? , email= ? , cpi= ? where id= ?";

        try {
            db.execSQL(s, new String[]{ name, email, cpi + "",id + ""});
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id){
        SQLiteDatabase db = getWritableDatabase();

        String s = "delete from student where id= ?";

        try {
            db.execSQL(s, new String[]{ id + ""});
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Cursor selectAllStudent() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select * from student" ,null);
    }
}
