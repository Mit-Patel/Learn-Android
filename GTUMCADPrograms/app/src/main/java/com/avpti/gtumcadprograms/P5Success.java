package com.avpti.gtumcadprograms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class P5Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5_success);

        String username = getIntent().getStringExtra("username");
        TextView textView = findViewById(R.id.text_view_username);
        textView.setText("Welcome " + username);
    }
}
