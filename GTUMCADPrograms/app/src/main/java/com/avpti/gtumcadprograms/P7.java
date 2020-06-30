package com.avpti.gtumcadprograms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class P7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p7);
    }

    public void btnClick(View view) {
        EditText etA = findViewById(R.id.etA), etB = findViewById(R.id.etB);
        int a = Integer.parseInt(etA.getText().toString()), b = Integer.parseInt(etB.getText().toString());
        Toast.makeText(this, (a > b) ? a + "" : b + "", Toast.LENGTH_SHORT).show();
    }
}
