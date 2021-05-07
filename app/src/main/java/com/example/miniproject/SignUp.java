package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    DatabaseHelper db;
EditText username,password,confirmpassword;
Button signup;
String u,p,cp;
    boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db=new DatabaseHelper(this);
        signup=(Button)findViewById(R.id.signup);
    }
    public void loadSignUpData(View view)
    {

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        confirmpassword=(EditText)findViewById(R.id.confirmpassword);

        u=username.getText().toString();
        p=password.getText().toString();
        cp=confirmpassword.getText().toString();


        if(u.equals("") || p.equals("") || cp.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();

        }
        else {
            if (p.equals(cp)) {
                try {
                    check = db.checkexistent(u);
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "error=" + e, Toast.LENGTH_LONG).show();
                }
                if (check == true) {

                    try {
                        Boolean res = db.insertSignUp(u, p);
                        if (res == true)
                            Toast.makeText(getApplicationContext(), "Signed Up Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "Sign Up unsuccessfull", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "error=" + e, Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "UserName Already exists", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
            }
        }
    }
}