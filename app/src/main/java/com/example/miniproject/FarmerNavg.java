package com.example.miniproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class FarmerNavg extends AppCompatActivity {

TextView t;
EditText cid,cname,hectares,yeild,price,location;
ImageButton photo;
ImageView setImage;
RadioGroup status;
RadioButton state;
Button add;
DatabaseHelper2 db1;
    String x;
private Uri ImagePath;
private Bitmap ImageToStore;

private static final int PICK_IMAGE_REQUEST=100;
String CropId,CropName,Hectares,Yeild,Price,Location,Status,uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_navg);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);
        Intent i=getIntent();
        uid=i.getStringExtra(AccountInterface.EXTRA_USERID);


            photo=(ImageButton)findViewById(R.id.image);

    }

    public void goToPhoto(View v)
    {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);
        }
        catch(Exception e){
        Toast.makeText(getApplicationContext(),"er="+e,Toast.LENGTH_LONG).show();
    }


    }

protected void onActivityResult(int requestCode,int resultCode,Intent data)
{
    try {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
            ImagePath = data.getData();

            ImageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),ImagePath);
            setImage=(ImageView)findViewById(R.id.imageView);
                setImage.setImageBitmap(ImageToStore);

        }
    }
    catch (Exception e)
    {
        Toast.makeText(getApplicationContext(),"Error="+e,Toast.LENGTH_LONG).show();
    }
}



    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment=null;

                    switch (item.getItemId())
                    {
                        case R.id.navigation_add:
                            selectedFragment=new AddFragment();
                            break;
                        case R.id.navigation_search:
                            selectedFragment=new Search(uid);
                            break;
                        case R.id.navigation_profile:
                            selectedFragment=new Profile(uid);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
    public  void checkStatus(View v)
    {
        status=(RadioGroup)findViewById(R.id.statusgroup);
        int radioid=status.getCheckedRadioButtonId();
        state=findViewById(radioid);
        Status=state.getText().toString();
    }
    public void addToCropData(View view)
    {
        t=(TextView)findViewById(R.id.textView6);
        cid=(EditText)findViewById(R.id.cid);
        cname=(EditText)findViewById(R.id.cname);
        hectares=(EditText)findViewById(R.id.hectare);
        yeild=(EditText)findViewById(R.id.yeild);
        price=(EditText)findViewById(R.id.price);
        location=(EditText)findViewById(R.id.location);
        db1=new DatabaseHelper2(this);

        CropId=cid.getText().toString();
        CropName=cname.getText().toString();
        Hectares=hectares.getText().toString();
        Yeild=yeild.getText().toString();
        Price=price.getText().toString();
        Location=location.getText().toString();


        Intent i=getIntent();
        uid=i.getStringExtra(AccountInterface.EXTRA_USERID);


        if(CropId.equals("") && CropName.equals("") || Hectares.equals("") || Yeild.equals("") || Price.equals("") ||
                Location.equals("") || uid.equals("") || Status.equals("") || ImageToStore==null)
        {
            Toast.makeText(getApplicationContext(),"Fields is/are empty ",Toast.LENGTH_LONG).show();
        }
        else {
            try {
                Boolean res = db1.insertCropData(CropId, CropName, Hectares, Yeild, Price, Location, Status, uid,ImageToStore);
                if(res==true)
                {
                    Toast.makeText(getApplicationContext(),"Crop data added Successfully.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Unable to add.",Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error=" + e, Toast.LENGTH_LONG).show();
            }
        }
    }

}
