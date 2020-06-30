package com.avpti.gtumcadprograms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class P4 extends AppCompatActivity {
    EditText etName, etAddress;
    RadioGroup rgGender;
    CheckBox cb1, cb2, cb3;
    ToggleButton tb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4);

        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        rgGender = findViewById(R.id.rgGender);
        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);
        tb1 = findViewById(R.id.toggleButton);
    }

    public void display(View v) {
        String checkbox = "";
        if (cb1.isChecked())
            checkbox += cb1.getText().toString() + " ";
        if (cb2.isChecked())
            checkbox += cb2.getText().toString() + " ";
        if (cb3.isChecked())
            checkbox += cb3.getText().toString() + " ";

        String data = etName.getText() + ", " + etAddress.getText() + ", " + ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText() + ", " + checkbox + ", " + tb1.getText();

        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

    }
}
