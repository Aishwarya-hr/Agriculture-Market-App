package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class CropInRecycleView extends AppCompatActivity {
DatabaseHelper2 db;
RecyclerView rv;
RecyclerViewAdapter adapter;
Button show;
String s="Sold";
    ArrayList<Model> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_in_recycle_view);


        show=(Button)findViewById(R.id.show);

        db=new DatabaseHelper2(this);
        rv=findViewById(R.id.croprv);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{

                   adapter=new RecyclerViewAdapter(db.getdataCrop(s),CropInRecycleView.this);
                   rv.setHasFixedSize(true);
                   rv.setLayoutManager(new LinearLayoutManager(CropInRecycleView.this));
                   rv.setAdapter(adapter);

                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Error="+e,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}