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

public class productListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<product> prodlist;

    public productListAdapter(Context context, int layout, ArrayList<product> prodlist) {
        this.context = context;
        this.layout = layout;
        this.prodlist = prodlist;
    }




    @Override
    public int getCount() {
        return prodlist.size();
    }

    @Override
    public Object getItem(int position) {
        return prodlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private class ViewHolder{
        ImageView imageView;
        TextView p_id, p_name,a_price,o_price,qty,p_desc,p_off,p_category;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);


            holder.p_id = (TextView) row.findViewById(R.id.prod_id);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            holder.p_name = (TextView) row.findViewById(R.id.prod_name);
            holder.a_price = (TextView) row.findViewById(R.id.act_Price);
            holder.o_price = (TextView) row.findViewById(R.id.off_Price);
            holder.qty = (TextView) row.findViewById(R.id.qty);
            holder.p_desc = (TextView) row.findViewById(R.id.prod_desc);
            holder.p_off = (TextView) row.findViewById(R.id.p_off);
            holder.p_category = (TextView) row.findViewById(R.id.category);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        product prod = prodlist.get(position);


        holder.p_id.setText(Integer.toString(prod.getPid()));
        holder.p_name.setText(prod.getP_name());
        holder.a_price.setText(prod.getA_price());
        holder.o_price.setText(prod.getO_price());
        holder.qty.setText(prod.getQty());
        holder.p_desc.setText(prod.getP_desc());
        holder.p_off.setText(prod.getP_off());
        holder.p_category.setText(prod.getP_category());



        byte[] foodImage = prod.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
