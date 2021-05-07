package com.example.miniproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Profile extends Fragment {
String id;
    Profile(String id)
    {
        this.id=id;
    }
    TextView name,number,email,street,city,state,pincode;
    DatabaseHelper1 db;
    String u;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.navigate_profile,container,false);

       name=(TextView) view.findViewById(R.id.name);
        number=(TextView) view.findViewById(R.id.number);
        email=(TextView) view.findViewById(R.id.email);
        street=(TextView) view.findViewById(R.id.street);
        city=(TextView) view.findViewById(R.id.city);
        state=(TextView) view.findViewById(R.id.state);
        pincode=(TextView) view.findViewById(R.id.pincode);
        db=new DatabaseHelper1(getContext());


        try {

                Cursor c = db.selectProfileData(id);
                if (c.getCount() ==0)
                    return null;
                      StringBuffer name1 = new StringBuffer();
            StringBuffer number1 = new StringBuffer();
            StringBuffer email1 = new StringBuffer();
            StringBuffer street1 = new StringBuffer();
            StringBuffer city1 = new StringBuffer();
            StringBuffer state1 = new StringBuffer();
            StringBuffer pincode1 = new StringBuffer();

            while (c.moveToNext()) {
                 name1.append (c.getString(2));
                number1.append (c.getString(5));
                street1.append (c.getString(6));
                city1.append (c.getString(7));
                state1.append (c.getString(8));
                email1.append (c.getString(9));
                pincode1.append (c.getString(10));

            }
            name.setText(name1);
            number.setText(number1);
            email.setText(email1);
            street.setText(street1);
            state.setText(state1);
            city.setText(city1);
            pincode.setText(pincode1);
            }
        catch(Exception e)
        {
            Toast.makeText(getContext(),"error="+e,Toast.LENGTH_LONG).show();
        }
       return view;
    }
}
