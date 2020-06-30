package com.avpti.practical17;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etId, etName, etEmail, etCpi;
    ListView lvStudent;
    ArrayList<String> list;
    ArrayAdapter<String> ada;
    DbHelper mydb;
    int index = 0,count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DbHelper(this);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etCpi = findViewById(R.id.etCpi);
        lvStudent = findViewById(R.id.lvStudent);

        list = new ArrayList<>();
        loadStudent();

        ada = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        lvStudent.setAdapter(ada);
    }

    public void onNavClick(View view) {
        switch (view.getId()) {
            case R.id.btnFirst:
                index = 0;
                break;
            case R.id.btnPrev:
                if (index > 0) index--;
                break;
            case R.id.btnNext:
                if (index < count) index++;
                break;
            case R.id.btnLast:
                index = count;
                break;
        }

        setEditTextData(list.get(index));
    }

    public void setEditTextData(String data) {
        String[] all = data.split(" ");
        etId.setText(all[0]);
        etName.setText(all[1]);
        etEmail.setText(all[2]);
        etCpi.setText(all[3]);
    }

    public void onClearClick(View view) {
        etId.setText("");
        etName.setText("");
        etEmail.setText("");
        etCpi.setText("");
    }


    public void onActionClick(View v) {
        int id = Integer.parseInt(etId.getText().toString());
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        double cpi = Double.parseDouble(etCpi.getText().toString());
        String msg = "";

        switch (v.getId()) {
            case R.id.btnAdd:
                msg = (mydb.insert(id, name, email, cpi)) ? " inserted." : " not inserted.";
                break;
            case R.id.btnUpdate:
                msg = (mydb.update(id, name, email, cpi)) ? " updated." : " not updated.";
                break;
            case R.id.btnDelete:
                msg = (mydb.delete(id)) ? " deleted." : " not deleted.";
                break;
        }

        Toast.makeText(this, "Data"+msg, Toast.LENGTH_SHORT).show();

        loadStudent();
        ada.notifyDataSetChanged();
    }

    public void loadStudent() {
        list.clear();

        Cursor res = mydb.selectAllStudent();
        res.moveToFirst();
        String str = "";
        while (!res.isAfterLast()) {
            list.add(res.getString(0) + " " + res.getString(1) + " " + res.getString(2) + " " + res.getString(3));
            res.moveToNext();
        }

        res.close();
        count = list.size() - 1;
    }
}
