package com.learnandy.mitpatel.ledtestapp;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MenuActivity extends AppCompatActivity {
    WifiManager wm;
    InetAddress inetAddress;
    String host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btnLed = findViewById(R.id.button_led);
        btnLed.setEnabled(false);

        if(checkWifiConnectivity() == 1){
            btnLed.setEnabled(true);
        }

    }

    public int checkWifiConnectivity(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiCheck = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifiCheck.isConnected()) {
            wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

            inetAddress = intToInetAddress(wm.getDhcpInfo().serverAddress);
            host = inetAddress.getHostAddress();
            if (!host.equals("172.24.1.1")) {
                //Toast.makeText(getApplicationContext(), "Please connect wifi to RPi3", Toast.LENGTH_SHORT).show();
                Log.i("RPI_WIFI","Some other wifi is connected!");

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Please connect to WiFi: RPi3");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.show();

                return 0;
            } else {
                Log.i("RPI_WIFI","RPI wifi connected successfully!");
                return 1;
            }
        } else {
            //Toast.makeText(getApplicationContext(), "Wifi is not connected", Toast.LENGTH_SHORT).show();
            Log.i("WIFI","WiFi not connected or turned on!");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Please connect to WiFi");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            builder.show();
            return 0;
        }
    }
    public InetAddress intToInetAddress(int hostAddress) {
        byte[] addressBytes = {(byte) (0xff & hostAddress),
                (byte) (0xff & (hostAddress >> 8)),
                (byte) (0xff & (hostAddress >> 16)),
                (byte) (0xff & (hostAddress >> 24))};

        try {
            return InetAddress.getByAddress(addressBytes);
        } catch (UnknownHostException e) {
            throw new AssertionError();
        }
    }

    public void ledClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void checkWifiClick(View view) {
        if(checkWifiConnectivity() == 1){
            Toast.makeText(this,"RPi3 is successfully connected!",Toast.LENGTH_SHORT).show();
        }
    }

    public void exitClick(View view) {
        finish();
    }

    public void databaseActivityClick(View view) {
        Intent intent = new Intent(this,DatabaseActivity.class);
        startActivity(intent);
    }
}
