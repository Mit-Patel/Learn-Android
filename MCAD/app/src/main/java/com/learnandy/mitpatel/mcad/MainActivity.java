package com.learnandy.mitpatel.mcad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] strings;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> arrayAdapterSpinner;
    ListView listView;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        spinner = findViewById(R.id.spinner);

        strings = new String[]{"A", "B", "C"};

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);

        listView.setAdapter(arrayAdapter);

        arrayAdapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, strings);

        spinner.setAdapter(arrayAdapterSpinner);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item clicked: " + strings[position], Toast.LENGTH_SHORT).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item clicked: " + strings[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }
}
