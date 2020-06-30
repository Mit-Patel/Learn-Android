package com.learnandy.mitpatel.ledtestapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    public static final int PORT = 5555;
    Switch led;
    String host;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        led = findViewById(R.id.switch_led);

        led.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String data = "0";

                if (isChecked) data = "1";
                else data = "0";

                new requestServerTask().execute(data);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    class requestServerTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String data = strings[0];
            String response="";
            InetAddress ip;
            Socket client = null;
            DataOutputStream out = null;
            DataInputStream in = null;

            try {
               // Thread.sleep(5000);
                ip = InetAddress.getByName(host);
                client = new Socket(ip, PORT);

                out = new DataOutputStream(client.getOutputStream());
                out.write(data.getBytes());
                out.flush();

                in = new DataInputStream(client.getInputStream());
                byte[] response_data = new byte[1024];
                in.read(response_data);
                response = new String(response_data, "utf-8");
                response = response.trim();
            } catch (Exception e) {
               response = "Error 101: Error while sending request or receiving response from server.";
                Log.i("ERROR_101", e.getMessage());
            } finally {
                try {
                    client.close();
                    out.close();
                    in.close();
                } catch (Exception e) {
                    //Toast.makeText(MainActivity.this,"Error 102: Error while closing the connection.",Toast.LENGTH_LONG).show();
                    Log.i("ERROR_102", e.getMessage());
                }
            }
            //Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
            return response;
        }
    }
}
