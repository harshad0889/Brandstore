package com.example.brandstore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class catlistAadapter extends RecyclerView.Adapter<catlistAadapter.CatViewHolder> {

    Context context;
    ArrayList<category> catlist;
    DatabaseHelper db;

    public catlistAadapter(Context context, ArrayList<category> catlist){
        this.context = context;
        this.catlist = catlist;
        db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater cInflator = LayoutInflater.from(context);
        view = cInflator.inflate(R.layout.cat_item,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        category model = catlist.get(position);
        // String cid = model.getCatid();
       // String cname = model.getCat_name();
       // String img = model.getImg();

        //set views

        holder.cat_id.setText(Integer.toString(model.getCatid()));
        holder.cat_name.setText(model.getCat_name());


        byte[] foodImage = model.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.cat_img.setImageBitmap(bitmap);





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
    }
}
