package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view)
    {
        Intent i=new Intent(MainActivity.this,SignIn.class);
        startActivity(i);

    }
    public void signUp(View v)
    {
        Intent i=new Intent(MainActivity.this,SignUp.class);
        startActivity(i);

    }
}