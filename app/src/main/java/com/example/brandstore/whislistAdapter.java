package com.example.brandstore;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class whislistAdapter extends BaseAdapter {


    private Context context;
    private  int layout;
    private ArrayList<wish> wishlist;
    DatabaseHelper db2;
    SharedPreferences sp;

    public whislistAdapter(Context context, int layout, ArrayList<wish> wishlist) {
        this.context = context;
        this.layout = layout;
        this.wishlist = wishlist;
    }


    @Override
    public int getCount() {
        return wishlist.size();
    }

    @Override
    public Object getItem(int position) {
        return wishlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolder{

        ImageView imageView;
        TextView p_id, p_name,a_price,p_qty,p_desc,uid,total_cprice,tot;
        Button rmv,pl_ord,add_cart;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {


        View row = view;
        ViewHolder holder = new ViewHolder();
        //sp = getSharedPreferences("user_details",MODE_PRIVATE);
        db2 = new DatabaseHelper(context);



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
            holder.rmv = (Button) row.findViewById(R.id.remove);
            holder.add_cart = (Button) row.findViewById(R.id.add_cart);
            holder.pl_ord = (Button) row.findViewById(R.id.place_ord);
            holder.tot = (TextView) row.findViewById(R.id.tot);



            row.setTag(holder);
        }
        else {
            holder = (whislistAdapter.ViewHolder)row.getTag();
        }

        final wish wishs = wishlist.get(position);


        holder.p_id.setText(Integer.toString(wishs.getPid()));
        holder.p_name.setText(wishs.getP_name());
        holder.a_price.setText(wishs.getA_price());
        holder.p_qty.setText(wishs.getP_qty());
        holder.uid.setText(wishs.getUid());
        holder.p_desc.setText(wishs.getP_desc());

        // getPrice();
        holder.rmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db2.remove_item(uid);
                wishlist.remove(position);
                notifyDataSetChanged();

                String prid= Integer.toString(wishs.getPid());
                String p_uid= wishs.getUid();
                deleteitem2(prid,p_uid);
                Toast.makeText(context.getApplicationContext(),p_uid,Toast.LENGTH_SHORT).show();

            }

            private void deleteitem2(String prid, String p_uid) {
                int deleteitem = db2.remove_item2(prid,p_uid);
            }




        });
        holder.add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db2.remove_item(uid);

                notifyDataSetChanged();

                String prid= Integer.toString(wishs.getPid());
                String p_uid= wishs.getUid();
                String qty = wishs.getP_qty();
                String size = wishs.getP_size();
                db2.insert_to_cart(prid,p_uid,qty,size);
                //addcart2(prid,p_uid,qty,size);
                Toast.makeText(context.getApplicationContext(),p_uid,Toast.LENGTH_SHORT).show();

            }

            private void addcart2(String prid, String p_uid,String qty, String size) {
                //int deleteitem = db2.insert_to_cart(prid,p_uid,qty,size);
            }




        });








        byte[] foodImage = wishs.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }


}
