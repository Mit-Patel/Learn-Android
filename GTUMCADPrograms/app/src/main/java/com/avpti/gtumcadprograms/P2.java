package com.avpti.gtumcadprograms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class P2 extends AppCompatActivity {

    EditText etCentrigrade;
    TextView tvFahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2);

        etCentrigrade = findViewById(R.id.etCentigrade);
        tvFahrenheit = findViewById(R.id.tvFahrenheit);

    }

    public void btnClick(View view) {
        int c = Integer.parseInt(etCentrigrade.getText().toString());

        int f = (c * 9 / 5) + 32;
        tvFahrenheit.setText(f + "");
    }
}
