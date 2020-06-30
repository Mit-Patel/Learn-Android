package com.avpti.gtumcadprograms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        Intent intent = new Intent();
        switch (((Button) view).getText().toString()) {
            case "Program 1":
                intent = new Intent(this, P1.class);
                break;
            case "Program 2":
                intent = new Intent(this, P2.class);
                break;
            case "Program 3":
                intent = new Intent(this, P3.class);
                break;
            case "Program 4":
                intent = new Intent(this, P4.class);
                break;
            case "Program 5":
                intent = new Intent(this, P5.class);
                break;
            case "Program 6":
                intent = new Intent(this, P6.class);
                break;
            case "Program 7":
                intent = new Intent(this, P7.class);
                break;
            case "Program 8":
                intent = new Intent(this, P8.class);
                break;
            case "Program 9":
                intent = new Intent(this, P9.class);
                break;
            case "Program 10":
                intent = new Intent(this, P10.class);
                break;
            case "Program 11":
                intent = new Intent(this, P11.class);
                break;
            case "Program 12":
                intent = new Intent(this, P12.class);
                break;

            case "Program 13":
                intent = new Intent(this, P13.class);
                break;
            case "Program 14":
                intent = new Intent(this, P14.class);
                break;
        }
        startActivity(intent);
    }
}