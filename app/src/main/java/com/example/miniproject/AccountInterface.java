package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AccountInterface extends AppCompatActivity {
    public static final String EXTRA_USERID="com.example.miniproject.EXTRA_UID";
TextView register;
Button farmer,trader,customer;
String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_interface);

        register=(TextView)findViewById(R.id.textview3);
        register.setPaintFlags(register.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        //Upon clicking Create Account Register page will open. uid is primary key.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=getIntent();
                uid=i.getStringExtra(SignIn.EXTRA_UID);

                Intent i1=new Intent(AccountInterface.this,Register.class);
                i1.putExtra(EXTRA_USERID,uid);
                startActivity(i1);
            }
        });

    }
public void moveToFarmerNavigation(View view)
{
    Intent i=getIntent();
    uid=i.getStringExtra(SignIn.EXTRA_UID);

    Intent i2= new Intent(AccountInterface.this,FarmerNavg.class);
    i2.putExtra(EXTRA_USERID,uid);
    startActivity(i2);
}
public void recylerViewCrop(View v)
{
    Intent i=getIntent();
    uid=i.getStringExtra(SignIn.EXTRA_UID);

    Intent i2= new Intent(AccountInterface.this,CropInRecycleView.class);
    i2.putExtra(EXTRA_USERID,uid);
    startActivity(i2);
}
}