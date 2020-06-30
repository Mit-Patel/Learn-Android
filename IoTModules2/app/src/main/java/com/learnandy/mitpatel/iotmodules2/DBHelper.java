package com.learnandy.mitpatel.iotmodules2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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
            "mobile_no decimal(12,0)," +
            "is_admin integer default 0) ");

    db.execSQL("insert into users(fname,lname,username,password,house_id,email,mobile_no,is_admin) values('Mit','Patel','admin','admin',1,'patelmit2012@gmail.com',9099945683,1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String fname, String lname, String username, String password, int house_id, String email, long mobile_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FNAME    ,fname);
        values.put(COL_LNAME    ,lname);
        values.put(COL_USERNAME ,username);
        values.put(COL_PASSWORD ,password);
        values.put(COL_HOUSE_ID ,house_id);
        values.put(COL_EMAIL    ,email);
        values.put(COL_MOBILE_NO,mobile_no);

        if(db.insert(TABLE_NAME,null,values) != -1) return true;
        else return false;
    }

    public Cursor getUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from users where id = ? ",new String[]{id});
        return res;
    }

    public Cursor checkUserLogin(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select id,house_id,is_admin from users where username = ? and password = ?",new String[]{username,password});
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

    public boolean updateHouseId(String id,int house_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_HOUSE_ID,house_id);

        if(db.update(TABLE_NAME,values,"id = ?",new String[]{id}) == 1) return true;
        else return false;
    }

    public Cursor getAllUsers(int house_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from users where house_id = ? and is_admin != 1",new String[]{house_id+""});
        return res;
    }
}
