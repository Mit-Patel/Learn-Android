package com.learnandy.mitpatel.internetconnectivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;

public class MyService extends Service {
    public static final String TAG = MyService.class.getSimpleName();
    public int counter = 0;
    Context context;

    private Timer timer;
    private TimerTask timerTask;
    long oldTime=0;


    public MyService() {
    }

    public MyService(Context context) {
        this.context = context;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
//        Toast.makeText(context.getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
        startTimer();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        // send new broadcast when service is destroyed.
        // this broadcast restarts the service.
        Intent broadcastIntent = new Intent("MY_ACTION");
        broadcastIntent.putExtra("service", 1);
        broadcastIntent.putExtra("message", "");
        sendBroadcast(broadcastIntent);

        stoptimertask();
    }

    public void startTimer() {

        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 1000, 1000); //
    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Communication cm = new Communication();

                String data="99;";
                String result = cm.sendData2(data);
                String[] res = result.split(";");
                if(res[0].equals("-1")){
                    Intent broadcastIntent = new Intent("MY_ACTION");
                    broadcastIntent.putExtra("service", 2);
                    broadcastIntent.putExtra("message", "Not Connected to server");
                    sendBroadcast(broadcastIntent);
                }
                Log.i(TAG, result);
                Log.i(TAG, "in timer ++++  "+ (counter++));
            }
        };
    }

    /**
     * not needed
     */
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
