package com.learnandy.mitpatel.internetconnectivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Intent mServiceIntent;
    Context ctx;
    private MyService mSensorService;
    ConnectivityReceiver br ;

    public Context getCtx() {
        return ctx;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);

        mSensorService = new MyService(this);

        mServiceIntent = new Intent(this, mSensorService.getClass());

        IntentFilter filter = new IntentFilter("MY_ACTION");
        br = new ConnectivityReceiver();
        this.registerReceiver(br, filter);


        if (!isMyServiceRunning(mSensorService.getClass())) {
            startService(mServiceIntent);
        }


    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {

        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (serviceClass.getName().equals(service.service.getClassName())) {
                    Log.i("isMyServiceRunning?", true + "");
                    return true;
                }
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }

    @Override
    protected void onDestroy() {
        stopService(mServiceIntent);
        Log.i("MainActivity", "onDestroy!");
        super.onDestroy();

    }

}