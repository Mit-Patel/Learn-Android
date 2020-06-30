package com.avpti.gtumcadprograms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class P8 extends AppCompatActivity {
    TextView tv;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p8);
        tv = findViewById(R.id.tv);
    }

    public void sizeIncr(View view) {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,tv.getTextSize() + 1);
    }

    public void sizeDecr(View view) {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,tv.getTextSize() - 1);
    }
}
