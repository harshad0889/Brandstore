package com.example.brandstore;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class cartlistAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<cart> cartlist;
    public static String totalPrice = null;
    DatabaseHelper db2;
    SharedPreferences sp;
    cart_fragment cr;
    public cart_fragment fragment;

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
        TextView p_id, p_name,a_price,p_qty,p_desc,uid,total_cprice,tot,cart_id,size;
        Button rmv,pl_ord;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();
        //sp = getSharedPreferences("user_details",MODE_PRIVATE);
        db2 = new DatabaseHelper(context);
        fragment = new cart_fragment();

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
            holder.pl_ord = (Button) row.findViewById(R.id.place_ord);
            holder.tot = (TextView) row.findViewById(R.id.tot);
            holder.cart_id = (TextView) row.findViewById(R.id.cart_id);
            holder.size = (TextView) row.findViewById(R.id.size);




            row.setTag(holder);
        }
        else {
            holder = (ViewHolder)row.getTag();
        }

        final cart carts = cartlist.get(position);


        holder.p_id.setText(Integer.toString(carts.getPid()));
        holder.p_name.setText(carts.getP_name());
        holder.cart_id.setText(carts.getCart_id());
        holder.a_price.setText(carts.getA_price());
        holder.p_qty.setText(carts.getP_qty());
        holder.uid.setText(carts.getUid());
        holder.p_desc.setText(carts.getP_desc());
        holder.size.setText(carts.getP_size());

       // getPrice();
        totalPrice = getPrice();
       // holder.tot.setText(totalPrice);
        fragment.Cart_total.setText(totalPrice);



        holder.rmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db2.remove_item(uid);
                cartlist.remove(position);
                notifyDataSetChanged();

                String prid= Integer.toString(carts.getPid());
                String p_uid= carts.getUid();
                deleteitem(prid,p_uid);
                Toast.makeText(context.getApplicationContext(),p_uid,Toast.LENGTH_SHORT).show();

            }

            private void deleteitem(String prid, String p_uid) {
               int deleteitem = db2.remove_item(prid,p_uid);
            }




        });








        byte[] foodImage = carts.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }

    public  String getPrice()
    {
        double total_price = 0.0;
        for(int j =0;j <cartlist.size();j++) {
            String d = cartlist.get(j).getA_price();
            String q = cartlist.get(j).getP_qty();
            total_price = total_price + (Double.parseDouble(q) * Double.parseDouble(d));
            Log.e(total_price + "", d + "*" + q);
        }
        return String.valueOf(total_price);





    }
}
