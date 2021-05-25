package com.example.brandstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class p_grid_selecteditem extends AppCompatActivity {
    TextView pid,pname,category,price,desc,qty,size,userid,qty_value,Review,offer;
    ImageView img_prod;
    int qtyv = 1;
    DatabaseHelper db;
    Button add_cart,chat2,inc_bt,dec_bt,add_whishlist,go_to_cart;
    SharedPreferences sd;
    String uid,username,product_id;
    ListView list_size,list_size_shirts;
    String wp_id,quantity;
    String wu_id;



    private ListView listView;
    private LinearLayout lin;
    private SimpleCursorAdapter adapter4;

    final String[] from = new String[]{db.COL_pro_id,db.COL_user,db.COL_rating,db.COL_review,db.COL_rdate};

    final int[] to = new int[]{R.id.prod_id2, R.id.user2,R.id.rating, R.id.preview2,R.id.r_date};
     String u_uid,u_pid,u_qty,u_amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_grid_selecteditem);
        db = new DatabaseHelper(this);
        sd = getSharedPreferences("user_details",MODE_PRIVATE);
        uid = sd.getString("uid",null);
        username  = sd.getString("username",null);


        pid = (TextView)findViewById(R.id.p_id);
        userid = (TextView)findViewById(R.id.u_id);
        pname = (TextView)findViewById(R.id.p_name);
        price = (TextView)findViewById(R.id.price);
        category = (TextView)findViewById(R.id.category);
        desc = (TextView)findViewById(R.id.p_desc);
        qty = (TextView)findViewById(R.id.qty);
        size = (TextView)findViewById(R.id.size);
        qty_value= findViewById(R.id.tv_qty_value);
        qty_value.setText(String.valueOf(qtyv));
        dec_bt= findViewById(R.id.dec);
        inc_bt= findViewById(R.id.inc);
        list_size= findViewById(R.id.listsize);
        list_size_shirts= findViewById(R.id.listsize_shirts);

        img_prod = findViewById(R.id.img_prod);
        add_cart = findViewById(R.id.add_cart);
        offer = findViewById(R.id.off);
        add_whishlist = findViewById(R.id.add_whishlist);
       lin = findViewById(R.id.reviews_lin);
        Review = findViewById(R.id.prod_review);
        listView = findViewById(R.id.lv_review);






        Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rs = new Intent(p_grid_selecteditem.this, Add_review.class);
                String p_id = pid.getText().toString();
                //String username = br.getText().toString();
                // Intent s = new Intent(Gridselecteditem.this,confirmcar.class);
                rs.putExtra("prodid",p_id);

                startActivity(rs);

            }
        });
        //***********size select list for shirts*******


        list_size = new ListView(this);
        List<String> data = new ArrayList<>();
        data.add("30");
        data.add("32");
        data.add("34");
        data.add("36");
        data.add("38");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        list_size.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(p_grid_selecteditem.this);
        builder.setCancelable(true);
        builder.setView(list_size);
        final  AlertDialog dialog = builder.create();



        //***********size select list for pants*******


        list_size_shirts = new ListView(this);
        List<String> sdata = new ArrayList<>();
        sdata.add("S");
        sdata.add("M");
        sdata.add("L");
        sdata.add("XL");
        sdata.add("XXL");

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,sdata);
        list_size_shirts.setAdapter(adapter2);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(p_grid_selecteditem.this);
        builder2.setCancelable(true);
        builder2.setView(list_size_shirts);
        final  AlertDialog dialog2 = builder2.create();

        //*******size text onclick
        size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String scat = category.getText().toString();
                if (scat.equals("pants") || scat.equals("PANTS") || scat.equals("JEANS")){
                    dialog.show();

                }else if(scat.equals("shirts") || scat.equals("SHIRTS") || scat.equals("T SHIRTS") ){
                    dialog2.show();
                }else{
                    dialog2.show();
                }

            }
        });

        //**********list view onclick lsitener****
        list_size.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                size.setText(adapter.getItem(position));

                dialog.dismiss();


            }
        });

        //**********list view shirts onclick lsitener****
        list_size_shirts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                size.setText(adapter2.getItem(position));

                dialog2.dismiss();


            }
        });



        Intent intent = getIntent();

        final String prod_id = intent.getStringExtra("pid");
        String p_name= intent.getStringExtra("p_name");
        String p_price= intent.getStringExtra("p_price");
        String p_cat= intent.getStringExtra("p_cat");
        String p_desc= intent.getStringExtra("p_desc");
        String p_qty= intent.getStringExtra("p_sale");
        final String p_size= intent.getStringExtra("size");
        String off= intent.getStringExtra("off");
        Bitmap bitmap = intent.getParcelableExtra("bitmap");


        pid.setText(prod_id);
        pname.setText(p_name);
        price.setText(p_price);
        category.setText(p_cat);
        desc.setText(p_desc);
        qty.setText(p_qty);
        offer.setText(off);
        userid.setText(uid);
        size.setText(p_size);

        byte[] bytes = db.prodImage(prod_id);
        img_prod.setImageBitmap(getImage(bytes));

        product_id = pid.getText().toString();

        //*************ADD CART BUTTON ONCLICK***************



        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Cursor cursor3 = db.getData(String.format("SELECT pid,_id,qty,cart_psize from cart_table where pid =%s and _id = %s and cart_psize='%s' AND order_id ISNULL ",prod_id,uid,p_size));


                if (size.length() == 0) {
                    size.requestFocus();
                    size.setError("choose your size");
                }else if(cursor3.getCount() == 0) {


                    String spid = pid.getText().toString();
                    String suid = userid.getText().toString();
                    String sp_qty = qty_value.getText().toString();
                    String s_psize = size.getText().toString();

                    db.insert_to_cart(spid, suid, sp_qty, s_psize);
                    Toast.makeText(p_grid_selecteditem.this, "Product added to cart ", Toast.LENGTH_LONG).show();
                    add_cart.setText("GO TO CART");
                    //go_to_cart.setVisibility(View.VISIBLE);


                    //update stocks

                    Cursor cursor4 = db.getData(String.format("SELECT psale from product_table where pid =%s ",prod_id));

                    while (cursor4.moveToNext()) {
                        quantity = cursor4.getString(0);

                    }

                    String act_qty =String.valueOf( Integer.parseInt(quantity)-Integer.parseInt(sp_qty));


                    int update = db.update_stock(spid,act_qty);

                }
                else{
                    final Cursor cursorx = db.getData(String.format("SELECT * from cart_table join product_table where cart_table.pid= product_table.pid AND cart_table.pid =%s and _id = %s and cart_psize='%s' AND order_id ISNULL ",prod_id,uid,p_size));

                    while (cursorx.moveToNext()) {
                         u_pid = cursorx.getString(1);
                         u_uid = cursorx.getString(2);
                         u_qty = cursorx.getString(3);
                         u_amount = cursorx.getString(12);

                    }





                    String spid = pid.getText().toString();
                    String suid = userid.getText().toString();
                    String sp_qty = qty_value.getText().toString();


                    double total_price = 0.0;
                     String n_qty = Integer.toString(Integer.parseInt(sp_qty)+Integer.parseInt(u_qty));

                     total_price =  total_price + (Double.parseDouble(n_qty) * Double.parseDouble(u_amount));
                     String n_amount = String.valueOf(total_price);
                     Toast.makeText(p_grid_selecteditem.this, "same product "+n_qty+ "..."+n_amount, Toast.LENGTH_LONG).show();

                     int up_cart = db.update_cart_qty(spid,suid,n_qty);
                    //update stocks

                    Cursor cursor4 = db.getData(String.format("SELECT psale from product_table where pid =%s ",prod_id));

                    while (cursor4.moveToNext()) {
                        quantity = cursor4.getString(0);

                    }

                    String act_qty =String.valueOf( Integer.parseInt(quantity)-Integer.parseInt(sp_qty));


                    int update = db.update_stock(spid,act_qty);



                    }




            }
        });

        //***********add whishlist button click


        add_whishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                    Cursor cursor2 = db.getData(String.format("SELECT pid,_id from wish_table where pid =%s and _id = %s ",prod_id,uid));
               // Log.e("samp",   cursor2);



                   /* while (cursor2.moveToNext()) {
                        for (int i = 0; i < cursor2.getCount(); i++) {

                            wp_id = cursor2.getString(0);
                            wu_id = cursor2.getString(1);


                            // add_stocks.setText(ps_cat);
                        }
                        Log.e("samp", wp_id + "," + wu_id);


                    }*/

                if (size.length() == 0) {
                    size.requestFocus();
                    size.setError("choose your size");

                }
                else if (cursor2.getCount() == 0) {
                    String spid = pid.getText().toString();
                    String suid = userid.getText().toString();
                    String sp_qty = qty_value.getText().toString();
                    String s_psize = size.getText().toString();

                    db.insert_to_wishlist(spid, suid, sp_qty, s_psize);
                    Toast.makeText(p_grid_selecteditem.this, "Product added to wishlist ", Toast.LENGTH_LONG).show();
                    add_whishlist.setText("GO TO WISHLIST");

                        }
                else {
                    Toast.makeText(p_grid_selecteditem.this, "already in wishlist ", Toast.LENGTH_LONG).show();

                        }


            }
        });





        inc_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (qtyv < 5){
                    qtyv++;
                    qty_value.setText(String.valueOf(qtyv));
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Limit of 5 products only",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }


        });
        dec_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrement();
            }


        });

        String sp_id = pid.getText().toString();
        Cursor cursor4 = db.review_list(sp_id);
        Log.e( "cursor",sp_id);

        //listView = findViewById(R.id.lv_review);

        adapter4 = new SimpleCursorAdapter(this,R.layout.review_items, cursor4, from, to,0);
        adapter4.notifyDataSetChanged();

        listView.setAdapter(adapter4);
       // lin.addView(adapter4);







    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
   public void increment(){
        if (qtyv < 5){
            qtyv++;
            qty_value.setText(String.valueOf(qtyv));
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Limit of 5 products only",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
    void decrement(){
        if (qtyv > 1){
            qtyv--;
            qty_value.setText(String.valueOf(qtyv));
        }
    }
}