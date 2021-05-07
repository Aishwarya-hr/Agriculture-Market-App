package com.example.miniproject;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static java.lang.Boolean.TRUE;

public class Search extends Fragment {
    TextView result;
    Button change;
    DatabaseHelper2 db2;
    String uid,cid;
    EditText id;
    Search(String uid)
    {
        this.uid=uid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.navigate_search,container,false);
        db2=new DatabaseHelper2(getContext());
       result=(TextView) view.findViewById(R.id.result);
        change=(Button) view.findViewById(R.id.change);

        change.setOnClickListener(new View.OnClickListener() {
    @Override
        public void onClick(View v) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        View mView = getLayoutInflater().inflate(R.layout.custom_status_change, null);

        id = (EditText) mView.findViewById(R.id.input);
        Button cancel = (Button) mView.findViewById(R.id.cancel);
        Button sold = (Button) mView.findViewById(R.id.sold);


        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        sold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="Sold";
                cid=id.getText().toString();
                try{
                    Boolean b1=db2.CheckCropId(uid,cid);
                    if(b1==true) {
                        Boolean b = db2.statusData(uid, cid, s);


                        if (b == false) {

                            Boolean res = db2.setStatus(s, uid);
                            if (res == TRUE)
                                Toast.makeText(getContext(), "Status got changed to sold ", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getContext(), "Not able to change ", Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(getContext(), "Item already Sold", Toast.LENGTH_LONG).show();
                    }
                    else {

                            Toast.makeText(getContext(), "Crop not found", Toast.LENGTH_LONG).show();
                    }

            }
                catch (Exception e)
                {
                    Toast.makeText(getContext(),"Error="+e,Toast.LENGTH_LONG).show();
                }
                alertDialog.dismiss();

            }
        });

        alertDialog.show();
        }

        });


try {
    Cursor c=db2.selectCropData(uid);
    if (c.getCount() ==0)
        return null;
    StringBuffer buf = new StringBuffer();
int i=1;
    while (c.moveToNext()) {
        buf.append (i+") "+c.getString(0)+"     ");
        buf.append (c.getString(1)+"     ");
       buf.append (c.getString(2)+"    \n\n");
              i=i+1;
             }
    result.setText(buf);

}
catch(Exception e)
{
    Toast.makeText(getContext(),"Error="+e,Toast.LENGTH_LONG).show();
}
        return view;

    }


}
