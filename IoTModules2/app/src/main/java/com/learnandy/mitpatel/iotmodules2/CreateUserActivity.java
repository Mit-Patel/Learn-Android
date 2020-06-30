package com.learnandy.mitpatel.iotmodules2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class CreateUserActivity extends AppCompatActivity {
    //Database object
    DBHelper mydb;

    //Session manager
    SessionManager sessionManager;

    //constant for house id
    private static int HOUSE_ID;

    //UI references
    EditText txt_fname, txt_lname, txt_email, txt_username, txt_password, txt_mobile_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //getting session data
        sessionManager = new SessionManager(getApplicationContext());

        //initializa new database object
        mydb = new DBHelper(this);

        //views in control
        txt_fname = findViewById(R.id.txt_fname);
        txt_lname = findViewById(R.id.txt_lname);
        txt_email = findViewById(R.id.txt_email);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        txt_mobile_no = findViewById(R.id.txt_mobile_no);

        //setting house_id
        HashMap<String, Integer> hashMap = sessionManager.getUserData();
        HOUSE_ID = hashMap.get(SessionManager.KEY_HOUSE_ID);
    }

    //creating user
    public void createClick(View view) {
        //validating data and create user
        validateData();
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //validate data function
    public void validateData() {

        //clearing the errors
        txt_fname.setError(null);
        txt_lname.setError(null);
        txt_username.setError(null);
        txt_password.setError(null);
        txt_email.setError(null);
        txt_mobile_no.setError(null);

        //getting the data from views

        String fname = txt_fname.getText().toString().trim();
        String lname = txt_lname.getText().toString().trim();
        String username = txt_username.getText().toString().trim();
        String email = txt_email.getText().toString().trim();
        String mobileNo = txt_mobile_no.getText().toString().trim();
        String password = txt_password.getText().toString().trim();

        //other variables
        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(email)) {
            txt_email.setError(getString(R.string.error_field_required));
            focusView = txt_email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            txt_email.setError(getString(R.string.error_invalid_email));
            focusView = txt_email;
            cancel = true;
        }
        if (TextUtils.isEmpty(mobileNo)) {
            txt_mobile_no.setError(getString(R.string.error_field_required));
            focusView = txt_mobile_no;
            cancel = true;
        }
        if (TextUtils.isEmpty(fname)) {
            txt_fname.setError(getString(R.string.error_field_required));
            focusView = txt_fname;
            cancel = true;
        }
        if (TextUtils.isEmpty(lname)) {
            txt_lname.setError(getString(R.string.error_field_required));
            focusView = txt_lname;
            cancel = true;
        }
        //error if password is empty
        if (TextUtils.isEmpty(password)) {
            txt_password.setError(getString(R.string.error_field_required));
            focusView = txt_password;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            txt_password.setError(getString(R.string.error_invalid_password));
            focusView = txt_password;
            cancel = true;
        }

        //error if username is empty
        if (TextUtils.isEmpty(username)) {
            txt_username.setError(getString(R.string.error_field_required));
            focusView = txt_username;
            cancel = true;
        }

        //show error or login the user
        if (cancel) {
            focusView.requestFocus();
        } else {
            //executing query for inserting data into database
            boolean isInserted = mydb.insertUser(
                    txt_fname.getText().toString(),
                    txt_lname.getText().toString(),
                    txt_username.getText().toString(),
                    txt_password.getText().toString(),
                    HOUSE_ID,
                    txt_email.getText().toString(),
                    Long.parseLong(txt_mobile_no.getText().toString())
            );

            //acknowledgement
            if (isInserted) {
                Toast.makeText(this, "User created!", Toast.LENGTH_LONG).show();
                resetData();
            } else Toast.makeText(this, "User not created!", Toast.LENGTH_LONG).show();
        }
    }

    //resetting data fuunction
    public void resetData() {
        txt_fname.setText("");
        txt_lname.setText("");
        txt_email.setText("");
        txt_username.setText("");
        txt_password.setText("");
        txt_mobile_no.setText("");
        txt_fname.requestFocus();
    }
}
