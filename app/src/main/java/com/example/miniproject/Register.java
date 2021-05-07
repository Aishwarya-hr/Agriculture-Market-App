package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    DatabaseHelper1 db;
EditText FullName,ContactNum,DOB,Street,City,State,Email,Pincode;
Button save;
RadioGroup Type,Gen;
RadioButton type,gen;
String uid,name,num,gender,dob,street,city,state,mail,pin,registerType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=new DatabaseHelper1(this);
        Intent i=getIntent();
        uid=i.getStringExtra(AccountInterface.EXTRA_USERID);


        FullName=(EditText)findViewById(R.id.name);
        ContactNum=(EditText)findViewById(R.id.PhNoNumber);
        DOB=(EditText)findViewById(R.id.dob);
        Street=(EditText)findViewById(R.id.street);
        City=(EditText)findViewById(R.id.city);
        State=(EditText)findViewById(R.id.state);
        Email=(EditText)findViewById(R.id.email);
        Pincode=(EditText)findViewById(R.id.pin);

        Type=(RadioGroup)findViewById(R.id.radiogroup1);
        Gen=(RadioGroup)findViewById(R.id.radiogroup2);

        save=(Button)findViewById(R.id.create);
    }

    public  void checkType(View v)
    {
        int radioid=Type.getCheckedRadioButtonId();
        type=findViewById(radioid);
        registerType=type.getText().toString();
      //  Toast.makeText(getApplicationContext(),"Error="+registerType,Toast.LENGTH_LONG).show();
    }
    public void checkGender(View view)
    {
        int radioid=Gen.getCheckedRadioButtonId();
        gen=findViewById(radioid);
        gender=gen.getText().toString();
        //Toast.makeText(getApplicationContext(),"Error="+gender,Toast.LENGTH_LONG).show();
    }
    public void createAccount(View view)
    {

        name=FullName.getText().toString();
        num=ContactNum.getText().toString();
        dob=DOB.getText().toString();
        street=Street.getText().toString();
        city=City.getText().toString();
        state=State.getText().toString();
        mail=Email.getText().toString();
        pin=Pincode.getText().toString();

        if(name.equals("") || num.equals("")||dob.equals("") || street.equals("") || city.equals("") || state.equals("") ||
        mail.equals("") || pin.equals("")||uid.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Fields is/are empty ",Toast.LENGTH_LONG).show();
        }
        else
        {
            try{
                Boolean res=db.insertProfileData(uid,registerType,name,gender,dob,num,street,city,state,mail,pin);

                if(res==true)
                {
                    Toast.makeText(getApplicationContext(),"Account Created Successfully.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Unable to create account.",Toast.LENGTH_LONG).show();
                }
            }
            catch(Exception e)
            {
                Toast.makeText(getApplicationContext(),"Error="+e,Toast.LENGTH_LONG).show();
            }

        }
    }

}