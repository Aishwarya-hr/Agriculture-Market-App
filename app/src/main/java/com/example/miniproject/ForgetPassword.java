package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Boolean.TRUE;

public class ForgetPassword extends AppCompatActivity {
    EditText username, password, confirmpassword;
    Button reset;
    DatabaseHelper db;
    String u, p, cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        db=new DatabaseHelper(this);
        reset = (Button) findViewById(R.id.reset);

    }

    public void resetPassword(View view) {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        u = username.getText().toString();
        p = password.getText().toString();
        cp = confirmpassword.getText().toString();

        if (u.equals("") || p.equals("") || cp.equals("")) {
            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else {
            if(p.equals(cp)) {
                try {
                    Boolean res = db.resetPass(u, p);
                    if (res == TRUE) {
                        Toast.makeText(getApplicationContext(), "Password Reset Success :)", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "User name not found", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "error=" + e, Toast.LENGTH_LONG).show();

                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Password not Matching", Toast.LENGTH_SHORT).show();
            }
        }
    }
}