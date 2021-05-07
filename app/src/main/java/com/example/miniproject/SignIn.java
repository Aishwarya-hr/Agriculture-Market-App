package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Boolean.TRUE;

public class SignIn extends AppCompatActivity {
    public static final String EXTRA_UID="com.example.miniproject.EXTRA_UID";
    DatabaseHelper db;
EditText username,password;
TextView forgetPassword;
Button next;
String u,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        db=new DatabaseHelper(this);
        next=(Button)findViewById(R.id.next);
        forgetPassword=(TextView)findViewById(R.id.textView5);

    }

    public void goToResetPassword(View view)
    {
        forgetPassword.setTextColor(0);
        Intent i=new Intent(SignIn.this,ForgetPassword.class);
        startActivity(i);
    }
    public void signIn(View view)
    {
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        u=username.getText().toString();
        p=password.getText().toString();

        if(u.equals("") || p.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
        }
        else
        {
            try{
                Boolean res=db.signVerify(u,p);
                if(res==TRUE) {
                    Toast.makeText(getApplicationContext(),"Successfully loged in :)",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(SignIn.this,AccountInterface.class);
                    i.putExtra(EXTRA_UID,u);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),"Wrong entry of email or Password",Toast.LENGTH_SHORT).show();

            }
            catch(Exception e)
            {
                Toast.makeText(getApplicationContext(), "error=" + e, Toast.LENGTH_LONG).show();

            }
        }
    }
}