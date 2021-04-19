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

public class cartlistAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<cart> cartlist;

    public cartlistAdapter(Context context, int layout, ArrayList<cart> cartlist) {
        this.context = context;
        this.layout = layout;
        this.cartlist = cartlist;
    }


    @Override
    public int getCount() {
        return cartlist.size();
    }

    @Override
    public Object getItem(int position) {
        return cartlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView p_id, p_name,a_price,p_qty,p_desc,uid;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);


            holder.p_id = (TextView) row.findViewById(R.id.pid);
            holder.imageView = (ImageView) row.findViewById(R.id.img_product);
            holder.p_name = (TextView) row.findViewById(R.id.prod_name);
            holder.a_price = (TextView) row.findViewById(R.id.price);
            // holder.o_price = (TextView) row.findViewById(R.id.off_Price);
            holder.p_qty = (TextView) row.findViewById(R.id.qty);
            holder.p_desc = (TextView) row.findViewById(R.id.prod_desc);
            // holder.p_off = (TextView) row.findViewById(R.id.p_off);
            holder.uid = (TextView) row.findViewById(R.id.uid);


            row.setTag(holder);
        }
        else {
            holder = (ViewHolder)row.getTag();
        }

        cart carts = cartlist.get(position);


        holder.p_id.setText(Integer.toString(carts.getPid()));
        holder.p_name.setText(carts.getP_name());
        holder.a_price.setText(carts.getA_price());
        holder.p_qty.setText(carts.getP_qty());
        holder.uid.setText(carts.getUid());
        holder.p_desc.setText(carts.getP_desc());




        byte[] foodImage = carts.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }

}
