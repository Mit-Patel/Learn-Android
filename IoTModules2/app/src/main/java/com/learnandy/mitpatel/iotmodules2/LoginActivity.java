package com.learnandy.mitpatel.iotmodules2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    //database object
    DBHelper mydb;
    // UI references.
    private TextView textUsername;
    private EditText textPassword;
    //session manager object
    private SessionManager sessionManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //setting database and initializing session
        mydb = new DBHelper(this);
        sessionManager = new SessionManager(this);

        //getting view in control
        textUsername = findViewById(R.id.username);
        textPassword = findViewById(R.id.password);
        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);

        //button click event
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //login process starts
                attemptLogin();
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, Void> {
        ProgressDialog pd;

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(String... strings) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                onPostExecute(null);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(LoginActivity.this, null, "Logging in...");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pd.dismiss();
        }
    }

    private void attemptLogin() {
        //clearing the errors
        textUsername.setError(null);
        textPassword.setError(null);

        //getting the data from views
        String username = textUsername.getText().toString().trim();
        String password = textPassword.getText().toString().trim();

        //other variables
        boolean cancel = false;
        View focusView = null;

        //error if password is empty
        if (TextUtils.isEmpty(password)) {
            textPassword.setError(getString(R.string.error_field_required));
            focusView = textPassword;
            cancel = true;
        }

        //error if password is less than 4 digits
       else if (!isPasswordValid(password)) {
            textPassword.setError(getString(R.string.error_invalid_password));
            focusView = textPassword;
            cancel = true;
        }

        //error if username is empty
        if (TextUtils.isEmpty(username)) {
            textUsername.setError(getString(R.string.error_field_required));
            focusView = textUsername;
            cancel = true;
        }

        //show error or login the user
        if (cancel) {
            focusView.requestFocus();
        } else {
            performLogin();
        }
    }

    private void performLogin() {
        //Progress Dialog show
        LoginTask loginTask = new LoginTask();
        loginTask.execute();

        //checking the user credentials and getting the required data
        Cursor res = mydb.checkUserLogin(textUsername.getText().toString().trim(), textPassword.getText().toString().trim());

        //error if no data found
        res.moveToFirst();
        if (res.getCount() != 1) {
            Common.showAlertMessage(this,"Invalid Login", "Invalid Username or Password!");
            //destroying progress dialog
            loginTask.cancel(true);
            resetData();
            return;
        }

        //creating session
        sessionManager.createLoginSession(res.getInt(0), res.getInt(1),res.getInt(2));

        //destroying progress dialog
        loginTask.cancel(false);

        //starting new activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //closing login activity
        finish();
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }



    //resets text in views
    private void resetData(){
        textUsername.setText("");
        textPassword.setText("");
        textUsername.requestFocus();
    }
}

