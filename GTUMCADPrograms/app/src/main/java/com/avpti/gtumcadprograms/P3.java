package com.avpti.gtumcadprograms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class P3 extends AppCompatActivity {
    EditText etA, etB;
    TextView tvAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3);

        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);
        tvAns = findViewById(R.id.tvAns);
    }

    public void btnClick(View view) {
        double a = Double.parseDouble(etA.getText().toString()), b = Double.parseDouble(etB.getText().toString());
        double ans = 0;
        switch (((Button) view).getText().toString()) {
            case "+":
                ans = a + b;
                break;
            case "-":
                ans = a - b;
                break;
            case "*":
                ans = a * b;
                break;
            case "/":
                ans = (b != 0) ? a / b : 0;
                break;
        }
        tvAns.setText(ans + "");
    }
}
