package com.learnandy.mitpatel.ledtestapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {
    DBHelper mydb;
    EditText txt_fname, txt_lname, txt_email, txt_username, txt_password, txt_mobile_no;
    CheckBox cb_is_admin;
    Button btnUpdate;
    private static int user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        mydb = new DBHelper(this);

        txt_fname = findViewById(R.id.txt_fname);
        txt_lname = findViewById(R.id.txt_lname);
        txt_email = findViewById(R.id.txt_email);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        txt_mobile_no = findViewById(R.id.txt_mobile_no);
        cb_is_admin = findViewById(R.id.cb_is_admin);
        btnUpdate = findViewById(R.id.button_update);
    }


    public void insertClick(View view) {
        String result = validateData();
        if (!result.equals("")) {
            showMessage("Alert", result);
            return;
        }

        boolean isInserted = mydb.insertUser(
                txt_fname.getText().toString(),
                txt_lname.getText().toString(),
                txt_username.getText().toString(),
                txt_password.getText().toString(),
                1,
                txt_email.getText().toString(),
                Long.parseLong(txt_mobile_no.getText().toString()),
                cb_is_admin.isChecked() ? 1 : 0
        );

        if (isInserted) {
            Toast.makeText(this, "Admin created", Toast.LENGTH_SHORT).show();
            resetData();
        } else Toast.makeText(this, "Admin not created", Toast.LENGTH_SHORT).show();
    }

    public void viewAllClick(View view) {
        Intent intent = new Intent(this, UsersListActivity.class);
        startActivityForResult(intent, 1);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public String validateData() {
        String msg = "";
        if (txt_fname.getText().toString().trim().equals(""))
            msg = "First Name cannot be empty!";
        else if (txt_lname.getText().toString().trim().equals(""))
            msg = "Last Name cannot be empty!";
        else if (txt_username.getText().toString().trim().equals(""))
            msg = "Username cannot be empty!";
        else if (txt_password.getText().toString().trim().equals(""))
            msg = "Password cannot be empty!";
        else if (txt_email.getText().toString().trim().equals(""))
            msg = "Email Id cannot be empty!";
        else if (txt_mobile_no.getText().toString().trim().equals(""))
            msg = "Mobile No. cannot be empty!";

        return msg;
    }

    public void resetData() {
        txt_fname.setText("");
        txt_lname.setText("");
        txt_email.setText("");
        txt_username.setText("");
        txt_password.setText("");
        txt_mobile_no.setText("");
        cb_is_admin.setChecked(false);
        btnUpdate.setEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                ListItem listItem = (ListItem) data.getSerializableExtra("updateValues");
                if (listItem != null) {
                    user_id = listItem.getUser_id();
                    txt_fname.setText(listItem.getFname());
                    txt_lname.setText(listItem.getLname());
                    txt_email.setText(listItem.getEmail());
                    txt_username.setText(listItem.getUsername());
                    txt_password.setText(listItem.getPassword());
                    txt_mobile_no.setText(listItem.getMobile_no());
                    cb_is_admin.setChecked((listItem.getIs_admin() == 1) ? true : false);
                    btnUpdate.setEnabled(true);
                } else {
                    showMessage("Error", "Update values not found!");
                    btnUpdate.setEnabled(false);
                }
            }
        }
    }

    public void updateClick(View view) {
        String result = validateData();
        if (!result.equals("")) {
            showMessage("Alert", result);
            return;
        }

        boolean isUpdated = mydb.updateUser(
                user_id + "",
                txt_fname.getText().toString(),
                txt_lname.getText().toString(),
                txt_username.getText().toString(),
                txt_password.getText().toString(),
                txt_email.getText().toString(),
                Long.parseLong(txt_mobile_no.getText().toString())
        );

        if (isUpdated) {
            Toast.makeText(this, "User updated", Toast.LENGTH_SHORT).show();
            resetData();
            user_id = 0;
        } else Toast.makeText(this, "User not updated", Toast.LENGTH_SHORT).show();

    }
}
