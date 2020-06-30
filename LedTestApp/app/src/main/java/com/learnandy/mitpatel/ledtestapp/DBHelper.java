package com.learnandy.mitpatel.ledtestapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HomeAutomationDB.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_ID       = "id";
    public static final String COL_FNAME    = "fname";
    public static final String COL_LNAME    = "lname";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_HOUSE_ID = "house_id";
    public static final String COL_EMAIL    = "email";
    public static final String COL_MOBILE_NO= "mobile_no";
    public static final String COL_IS_ADMIN = "is_admin";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table if not exists " + TABLE_NAME +" (" +
            "id integer primary key autoincrement , " +
            "fname text ,  " +
            "lname text , " +
            "username text , " +
            "password text , " +
            "house_id integer ," +
            "email text," +
            "mobile_no decimal(10,0)," +
            "is_admin boolean ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String fname, String lname, String username, String password, int house_id, String email, long mobile_no, int is_admin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FNAME    ,fname);
        values.put(COL_LNAME    ,lname);
        values.put(COL_USERNAME ,username);
        values.put(COL_PASSWORD ,password);
        values.put(COL_HOUSE_ID ,house_id);
        values.put(COL_EMAIL    ,email);
        values.put(COL_MOBILE_NO,mobile_no);
        values.put(COL_IS_ADMIN ,is_admin);

        if(db.insert(TABLE_NAME,null,values) != -1) return true;
        else return false;
    }

    public Cursor getAllUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from users",null);
        return res;
    }

    public Cursor getUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from users where id = ? ",new String[]{id});
        return res;
    }

    public int deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users","id = ?",new String[]{id});
    }

    public boolean updateUser(String id,String fname, String lname, String username, String password, String email, long mobile_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FNAME    ,fname);
        values.put(COL_LNAME    ,lname);
        values.put(COL_USERNAME ,username);
        values.put(COL_PASSWORD ,password);
        values.put(COL_EMAIL    ,email);
        values.put(COL_MOBILE_NO,mobile_no);


        if(db.update(TABLE_NAME,values,"id = ?",new String[]{id}) == 1) return true;
        else return false;
    }
}
