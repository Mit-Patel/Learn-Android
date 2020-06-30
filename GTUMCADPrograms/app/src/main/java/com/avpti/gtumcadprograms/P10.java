package com.avpti.gtumcadprograms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class P10 extends AppCompatActivity {
    EditText etText;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p10);

        etText = findViewById(R.id.etText);
        tv = findViewById(R.id.tv);

        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
