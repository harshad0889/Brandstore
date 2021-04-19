package com.example.brandstore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class catlistAadapter2 extends RecyclerView.Adapter<catlistAadapter2.CatViewHolder> {

    Context context;
    ArrayList<category> catlist;
    DatabaseHelper db;

    public catlistAadapter2(Context context, ArrayList<category> catlist){
        this.context = context;
        this.catlist = catlist;
        db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public catlistAadapter2.CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater cInflator = LayoutInflater.from(context);
        view = cInflator.inflate(R.layout.cat_item_cust,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull catlistAadapter2.CatViewHolder holder, final int position) {
        final category model = catlist.get(position);
        // String cid = model.getCatid();
        // String cname = model.getCat_name();
        // String img = model.getImg();

        //set views

        holder.cat_id.setText(Integer.toString(model.getCatid()));
        holder.cat_name.setText(model.getCat_name());


        byte[] foodImage = model.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.cat_img.setImageBitmap(bitmap);


        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+model.getCat_name(), Toast.LENGTH_LONG).show();


                Intent intent = new Intent(context,cat_prod_view.class);

                //passing data to cat selected activity

                // intent.putExtra("catid",catlist.get(position).getCatid());
                intent.putExtra("catname",catlist.get(position).getCat_name());
                //intent.putExtra("catimage",catlist.get(position).getImg());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return catlist.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        TextView cat_id,cat_name;
        ImageView cat_img;
        CardView cardview;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_id = itemView.findViewById(R.id.cat_id);
            cat_name = itemView.findViewById(R.id.cat_name);
            cat_img = itemView.findViewById(R.id.imgcat);
            cardview = itemView.findViewById(R.id.card_view2);
        }
    }

   /* @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater cInflator = LayoutInflater.from(context);
        view = cInflator.inflate(R.layout.cat_item_cust,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, final int position) {
       final category model = catlist.get(position);
        // String cid = model.getCatid();
       // String cname = model.getCat_name();
       // String img = model.getImg();

        //set views

        holder.cat_id.setText(Integer.toString(model.getCatid()));
        holder.cat_name.setText(model.getCat_name());


        byte[] foodImage = model.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.cat_img.setImageBitmap(bitmap);



        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "catlistAadapter"+model.getCat_name(), Toast.LENGTH_LONG).show();


                Intent intent = new Intent(context,cat_prod_view.class);

                //passing data to cat selected activity

               // intent.putExtra("catid",catlist.get(position).getCatid());
                intent.putExtra("catname",catlist.get(position).getCat_name());
                //intent.putExtra("catimage",catlist.get(position).getImg());
                context.startActivity(intent);


            }
        });


       // holder.cat_img.setImageURI(Uri.parse(img));
        //holder.cat_name.setText(cname);
        //holder.cat_id.setText(cid);



    }

    @Override
    public int getItemCount() {
        return catlist.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        TextView cat_id,cat_name;
        ImageView cat_img;
        CardView cardview;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_id = itemView.findViewById(R.id.cat_id);
            cat_name = itemView.findViewById(R.id.cat_name);
            cat_img = itemView.findViewById(R.id.imgcat);
            cardview = itemView.findViewById(R.id.card_view);

        }
    }*/
}
