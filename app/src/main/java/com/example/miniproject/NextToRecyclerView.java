package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Boolean.TRUE;

public class NextToRecyclerView extends AppCompatActivity {


    TextView name,unit,price,location,hect,faid,textView,textView1,hectaretext;
    ImageView img;
    Button bid,buy;
    String fid;
    String cropID;
    RelativeLayout layout;
    DatabaseHelper2 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_to_recycler_view);
        name=(TextView)findViewById(R.id.name);
        unit=(TextView)findViewById(R.id.yeild);
        location=(TextView)findViewById(R.id.location);
        price=(TextView)findViewById(R.id.price);
        hect=(TextView)findViewById(R.id.hectare);
        faid=(TextView)findViewById(R.id.farid);

        textView=(TextView)findViewById(R.id.textView);
        textView1=(TextView)findViewById(R.id.textView1);
        hectaretext=(TextView)findViewById(R.id.hectaretext);

        img=(ImageView)findViewById(R.id.showImage);
        bid=(Button)findViewById(R.id.bid);
        buy=(Button)findViewById(R.id.buy);
        db=new DatabaseHelper2(this);
        layout=(RelativeLayout)findViewById(R.id.layoutNext);
        //get the data from previous activity
        Intent in=getIntent();
        String crop=in.getStringExtra("name");
        String yeild=in.getStringExtra("unit");
        byte[] mBytes=getIntent().getByteArrayExtra("image");
        cropID=in.getStringExtra("id");
        String rate=in.getStringExtra("price");
        String place=in.getStringExtra("location");
        String hectare=in.getStringExtra("hectare");
         fid=in.getStringExtra("fid");
       // Toast.makeText(getApplicationContext(),"this="+yeild,Toast.LENGTH_LONG).show();
        //decode

        Bitmap imagebit= BitmapFactory.decodeByteArray(mBytes,0,mBytes.length);


        name.setText(crop);


        img.setImageBitmap(imagebit);
        unit.setText(yeild);
        price.setText(rate);

        location.setText(place);
        hect.setText(hectare);
    }
    public void bidding(View v)
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(NextToRecyclerView.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_bid, null);

        Button bidd = (Button) mView.findViewById(R.id.bidd);
        Button cancel = (Button) mView.findViewById(R.id.cancel);
        EditText input=(EditText)mView.findViewById(R.id.input);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        bidd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(getApplicationContext(), "Successfully bidded", Toast.LENGTH_LONG).show();

            }
        });

        bidd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        bidd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Successfully bidded", Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });
    }
    public void buyFromFarmer(View view)
    {
        final String s="Sold";
        final AlertDialog.Builder alert = new AlertDialog.Builder(NextToRecyclerView.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_buy, null);
        Button yes = (Button) mView.findViewById(R.id.yes);
        Button no = (Button) mView.findViewById(R.id.no);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

       no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                            Boolean res = db.setStatusSold(s,cropID);
                            if (res == TRUE)
                                Toast.makeText(getApplicationContext(), "Successfully booked contact farmer for transaction", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getApplicationContext(), "Not able buy ", Toast.LENGTH_LONG).show();


                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Error="+e,Toast.LENGTH_LONG).show();
                }
                alertDialog.dismiss();

            }
        });

        alertDialog.show();
    }
    public void callProfile(View v)
    {
       /* TextView name,unit,price,location,hect,faid;
        ImageView img;
        Button bid,buy;*/
        name.setVisibility(View.GONE);
        unit.setVisibility(View.GONE);
        price.setVisibility(View.GONE);
        location.setVisibility(View.GONE);
        hect.setVisibility(View.GONE);
        faid.setVisibility(View.GONE);
        img.setVisibility(View.GONE);
       bid.setVisibility(View.GONE);
       buy.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        hectaretext.setVisibility(View.GONE);

        FragmentManager fm=getSupportFragmentManager();
        Profile profile=new Profile(fid);
        fm.beginTransaction().replace(R.id.layoutNext,profile).commit();
    }
}