package com.learnandy.mitpatel.internetconnectivity;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

public class Communication {
    public String host;
    private String message = "";
    private int port;
    private SendReceiveThread thread;

    Communication() {
        host = "192.168.20.25";
        port = 5555;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    String getMessage() {
        return message;
    }

    void sendData(String data) {
        thread = new SendReceiveThread();
        try {
            message = thread.execute(host, port + "", data).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    String sendData2(String data) {
        byte[] response = null;
        try {
            Socket client = new Socket();
            //client.setSoTimeout(10000);
            client.connect(new InetSocketAddress(host, port), 2000);

            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.write(data.getBytes());
            out.flush();


            DataInputStream in = new DataInputStream(client.getInputStream());
            response = new byte[1024];
            in.read(response);


            client.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "-1;";
        }
        String res = "";
        try {
            res = new String(response, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "-1;";

        } catch (Exception e) {
            e.printStackTrace();
            return "-1;";

        }
        return res;

    }

    class SendReceiveThread extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... voids) {
            byte[] response = null;
            try {
                Socket client = new Socket(voids[0], Integer.parseInt(voids[1]));

                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                out.write(voids[2].getBytes());
                out.flush();

                DataInputStream in = new DataInputStream(client.getInputStream());
                response = new byte[1024];
                in.read(response);

                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String res = "";
            try {
                res = new String(response, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return res;
        }
    }
}
