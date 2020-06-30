package com.avpti.gtumcadprograms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class P5 extends AppCompatActivity {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin@123";
    private EditText textUsername, textPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);

        textUsername = findViewById(R.id.edit_text_username);
        textPassword = findViewById(R.id.edit_text_password);
        buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setEnabled(false);

        textPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 8) {
                    buttonLogin.setEnabled(true);
                } else {
                    buttonLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void loginUser(View view) {
        if (TextUtils.isEmpty(textUsername.getText())) {
            textUsername.setError("This field cannot be empty");
        } else {
            if (textUsername.getText().toString().equals(USERNAME) && textPassword.getText().toString().equals(PASSWORD)) {
                Intent intent = new Intent(P5.this, P5Success.class);
                intent.putExtra("username", USERNAME);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
