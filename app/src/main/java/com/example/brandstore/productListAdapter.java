package com.example.brandstore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class productListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<product> prodlist;

    DatabaseHelper db;
    String pname = "SHIRTS";

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



    static class ViewHolder{
        ImageView imageView;
        TextView p_id, p_name,a_price,o_price,p_sale,p_desc,p_off,p_category,p_size,off,off_sprice,off_tv,p_subcat;
        RatingBar rating_bar;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();
        db = new DatabaseHelper(context);

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);


            holder.p_id = (TextView) row.findViewById(R.id.prod_id);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            holder.p_name = (TextView) row.findViewById(R.id.prod_name);
            holder.a_price = (TextView) row.findViewById(R.id.act_Price);
            holder.o_price = (TextView) row.findViewById(R.id.off_sprice);
            holder.p_sale = (TextView) row.findViewById(R.id.p_sale);
            holder.p_desc = (TextView) row.findViewById(R.id.prod_desc);
            holder.p_off = (TextView) row.findViewById(R.id.off1);
            holder.p_category = (TextView) row.findViewById(R.id.category);
            holder.p_subcat = (TextView) row.findViewById(R.id.sub_cat);
            holder.p_size = (TextView) row.findViewById(R.id.p_size);
            holder.off = (TextView) row.findViewById(R.id.off1);
            holder.off_sprice = (TextView) row.findViewById(R.id.off_sprice);
            holder.off_tv = (TextView) row.findViewById(R.id.off_tv);
            holder.rating_bar =  (RatingBar) row.findViewById(R.id.rating);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }




        product prod = prodlist.get(position);



        holder.p_id.setText(Integer.toString(prod.getPid()));
        holder.p_name.setText(prod.getP_name());


        double oprice = 0.0;
        String offerprice = prod.getA_price();
        String off = prod.getP_off();
        if (off != null){

            String per = "100";
            Log.e("sample", offerprice + off);
            oprice = (Double.parseDouble(offerprice) - (Double.parseDouble(offerprice) * Double.parseDouble(off)/Double.parseDouble(per)));
        }else
        {
            off="0";
        String per = "100";
        Log.e("sample", offerprice + off);
            oprice = (Double.parseDouble(offerprice) - (Double.parseDouble(offerprice) * Double.parseDouble(off)/Double.parseDouble(per)));
            holder.off_tv.setVisibility(View.GONE);

        }
       // oprice = (Double.parseDouble(offerprice) - (Double.parseDouble(offerprice) * Double.parseDouble(off)/Double.parseDouble(per)));

        holder.a_price.setText(String.valueOf(oprice));
        holder.o_price.setText(prod.getA_price());
        holder.p_sale.setText(prod.getP_sale());
        holder.p_size.setText(prod.getP_size() );
        holder.p_desc.setText(prod.getP_desc());
        holder.p_off.setText(prod.getP_off());
        holder.p_category.setText(prod.getP_category());
        holder.p_subcat.setText(prod.getP_subcat());
        //holder.rating_bar.setRating(Float.parseFloat(revav.getRating()));
        //holder.rating_bar.setRating(Float.parseFloat(db.getData(String.format("select avg(rating) from review WHERE pid ='%s'"))));
        //String rat = toString().(db.getData(String.format("select avg(rating) from review WHERE pid ='%s'"));



        byte[] foodImage = prod.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;





        //this above code should be added or replaced in select * from product and try it
    }

}


   /* double oprice = 0.0;
    String offerprice = prod.getA_price();
    String off = prod.getP_off();
    String per = "100";
        oprice = oprice - (Double.parseDouble(offerprice) * Double.parseDouble(off)/Double.parseDouble(per));*/