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

public class MyorderlistAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<order> orderlist;

    public MyorderlistAdapter(Context context, int layout, ArrayList<order> orderlist) {
        this.context = context;
        this.layout = layout;
        this.orderlist = orderlist;
    }



    @Override
    public int getCount() {
        return orderlist.size();
    }

    @Override
    public Object getItem(int position) {
        return orderlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView order_id, uid,delivery_amt,status,pay_mode,o_date,username,phone,address,pin, cart_id,pid,qty,cart_psize,p_name,p_cat,price,total;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);



            holder.order_id = (TextView) row.findViewById(R.id.order_id);
            holder.uid = (TextView) row.findViewById(R.id.uid);
            holder.delivery_amt = (TextView) row.findViewById(R.id.del_amt);
            holder.status = (TextView) row.findViewById(R.id.status);
            holder.pay_mode = (TextView) row.findViewById(R.id.p_mode);
            holder.o_date = (TextView) row.findViewById(R.id.o_date);
            holder.username = (TextView) row.findViewById(R.id.u_name);
            holder.phone = (TextView) row.findViewById(R.id.phone);
            holder.address = (TextView) row.findViewById(R.id.address);
            holder.pin = (TextView) row.findViewById(R.id.pin);
            holder.cart_id = (TextView) row.findViewById(R.id.cart_id);
            holder.pid = (TextView) row.findViewById(R.id.pid);
            holder.qty = (TextView) row.findViewById(R.id.qty);
            holder.imageView = (ImageView) row.findViewById(R.id.img_product);
            holder.p_name = (TextView) row.findViewById(R.id.prod_name);
            holder.cart_psize = (TextView) row.findViewById(R.id.c_size);
            holder.p_cat = (TextView) row.findViewById(R.id.p_cat);
            holder.price = (TextView) row.findViewById(R.id.price);
            holder.total = (TextView) row.findViewById(R.id.tot);

            row.setTag(holder);
        }
        else {
            holder = (MyorderlistAdapter.ViewHolder) row.getTag();
        }

        order ord = orderlist.get(position);


        holder.order_id.setText(Integer.toString(ord.getOrder_id()));
        holder.uid.setText(Integer.toString(ord.getUid()));
        holder.delivery_amt.setText(ord.getDelivery_amt());
         holder.status.setText(ord.getStatus());
        holder.pay_mode.setText(ord.getPay_mode());
        holder.o_date.setText(ord.getO_date());
        holder.username.setText(ord.getUsername());
        holder.phone.setText(ord.getPhone());
        holder.address.setText(ord.getAddress());
        holder.pin.setText(ord.getPin());
        holder.cart_id.setText(Integer.toString(ord.getCart_id()));
        holder.pid.setText(Integer.toString(ord.getPid()));
        holder.qty.setText(Integer.toString(ord.getQty()));
        holder.p_name.setText(ord.getP_name());
        holder.cart_psize.setText(ord.getCart_psize());
        holder.p_cat.setText(ord.getP_cat());
        holder.price.setText(Integer.toString(ord.getPrice()));
        holder.total.setText(Integer.toString(ord.getTotal()));



        byte[] prod_image = ord.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(prod_image, 0, prod_image.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
