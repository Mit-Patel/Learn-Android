package com.learnandy.mitpatel.iotmodules2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    //Shared Preference Mode
    private static final int PRIVATE_MODE = 0;
    //Shared Preference Name
    private static final String PREF_NAME = "AppLoginPref";
    //Keys
    private static final String IS_LOGIN = "is_logged_in";
    public static final String KEY_ID = "user_id";
    public static final String KEY_IS_ADMIN = "is_admin";
    public static final String KEY_HOUSE_ID = "house_id";

    //COnstructor
    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    //Create and sets all the required data to preference
    public void createLoginSession(int id, int hid,int isAdmin) {
        //login pref
        editor.putBoolean(IS_LOGIN, true);
        //id pref
        editor.putInt(KEY_ID, id);
        //house_id
        editor.putInt(KEY_HOUSE_ID, hid);
        //is_admin
        editor.putInt(KEY_IS_ADMIN, isAdmin);
        //commit
        editor.commit();
    }

    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
        }
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    //for getting user data
    public HashMap<String, Integer> getUserData() {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put(KEY_ID, sharedPreferences.getInt(KEY_ID, -1));
        hashMap.put(KEY_HOUSE_ID, sharedPreferences.getInt(KEY_HOUSE_ID, -1));
        hashMap.put(KEY_IS_ADMIN, sharedPreferences.getInt(KEY_IS_ADMIN, -1));

        return hashMap;
    }

    public int getHouseId(){
        return sharedPreferences.getInt(KEY_HOUSE_ID,-1);
    }

    public  int getUserId(){
        return sharedPreferences.getInt(KEY_ID,-1);
    }

    public int isAdmin(){
        return sharedPreferences.getInt(KEY_IS_ADMIN,-1);
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }
}
