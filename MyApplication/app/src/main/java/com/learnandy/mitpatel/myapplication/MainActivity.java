package com.learnandy.mitpatel.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textH =  findViewById(R.id.hello_world);

    }


    public void changeText(View view) {
        textH.setText("hi shreloock");
    }
}
