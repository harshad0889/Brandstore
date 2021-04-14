package com.example.brandstore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class salelistAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<sale> salelist;

    public salelistAdapter(Context context, int layout, ArrayList<sale> salelist) {
        this.context = context;
        this.layout = layout;
        this.salelist = salelist;
    }


    @Override
    public int getCount() {
        return salelist.size();
    }

    @Override
    public Object getItem(int position) {
        return salelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder{
        ImageView imageView;
        TextView sid, s_title,s_desc;
    }



    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);


            holder.sid = (TextView) row.findViewById(R.id.s_id);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            holder.s_title = (TextView) row.findViewById(R.id.sale_title);
            holder.s_desc = (TextView) row.findViewById(R.id.sale_desc);


            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        sale s = salelist.get(position);


        holder.sid.setText(Integer.toString(s.getSid()));
        holder.s_title.setText(s.getS_title());
        holder.s_desc.setText(s.getS_desc());




        byte[] foodImage = s.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
