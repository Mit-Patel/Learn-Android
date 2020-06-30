package com.learnandy.mitpatel.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView textEventHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEventHeader = findViewById(R.id.event_header);

        //Log.d("Main Activity","Hello World");
    }

    private void changeHeaderText(){
        String header = textEventHeader.getText().toString();

        Toast.makeText(this, header, Toast.LENGTH_SHORT).show();
        textEventHeader.setText("Happy Birthday Sanjana");
    }
}
