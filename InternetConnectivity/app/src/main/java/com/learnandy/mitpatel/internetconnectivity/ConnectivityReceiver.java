package com.learnandy.mitpatel.internetconnectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class ConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int data = intent.getIntExtra("service",-1 );
        String message = intent.getStringExtra("message");
        if(data == 1){
            Log.i(MyService.class.getSimpleName(), "Broadcast Received");
            Log.i(MyService.class.getSimpleName(), "Restarting Service");
            context.startService(new Intent(context, MyService.class));
        }else{
            Toast.makeText(context,message , Toast.LENGTH_SHORT).show();
        }

    }
}