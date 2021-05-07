package com.example.miniproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RCViewHolderClass> {
    Context c;
    ArrayList<Model> arrayList;


    public RecyclerViewAdapter(ArrayList<Model> modelArrayList, Context c) {
        this.arrayList = modelArrayList;
        this.c = c;
    }



    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }




    @NonNull
    @Override
    public RCViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RCViewHolderClass(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RCViewHolderClass holder, int position) {

        final Model model = arrayList.get(position);
        String name=model.getName();
        String yeild=model.getYeild();
        Bitmap image=model.getImageBitmap();

holder.imag.setImageBitmap(image);
holder.name.setText(name);
holder.unit.setText(yeild);

 holder.setOnItemClickListener(new OnItemClickListener() {
     @Override
     public void onItemClick(View view, int position) {
         String oname=arrayList.get(position).getName();//gets data from previous activity
         String ounit=arrayList.get(position).getYeild();
         String cid=arrayList.get(position).getCid();
         String fid=arrayList.get(position).getFarmerid();
         String location=arrayList.get(position).getLocation();
         String price=arrayList.get(position).getPrice();
         String hectare=arrayList.get(position).getHectare();
         BitmapDrawable imgmap=(BitmapDrawable)holder.imag.getDrawable();

         Bitmap imgbit=imgmap.getBitmap();

         ByteArrayOutputStream stream=new ByteArrayOutputStream();
         imgbit.compress(Bitmap.CompressFormat.PNG,100,stream);

         byte[] bytes=stream.toByteArray();// get our data with intent

         Intent intent=new Intent(c,NextToRecyclerView.class);
         intent.putExtra("name",oname);
         intent.putExtra("unit",ounit);
         intent.putExtra("image",bytes);
         intent.putExtra("id",cid);
         intent.putExtra("hectare",hectare);
         intent.putExtra("price",price);
         intent.putExtra("location",location);
         intent.putExtra("fid",fid);
         c.startActivity(intent);
     }
 });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RCViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView name,unit;
        ImageView imag;
        OnItemClickListener listener;
        public RCViewHolderClass(@NonNull View itemView) {

            super(itemView);
            name=itemView.findViewById(R.id.cropName);
            unit=itemView.findViewById(R.id.unit);
            imag=itemView.findViewById(R.id.CropImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.listener.onItemClick(v,getLayoutPosition());
        }
        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener=listener;
        }
    }
}
