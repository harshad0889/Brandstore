package com.example.brandstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.brandstore.update_product.getImage;

public class p_grid_selecteditem extends AppCompatActivity {
    TextView pid,pname,category,price,desc,qty,size,userid,qty_value;
    ImageView img_prod;
    int qtyv = 1;
    DatabaseHelper db;
    Button add_cart,chat2,inc_bt,dec_bt,add_whishlist;
    SharedPreferences sd;
    String uid,username;

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

        img_prod = findViewById(R.id.img_prod);
        add_cart = findViewById(R.id.add_cart);
        add_whishlist = findViewById(R.id.add_whishlist);
       // carreview = findViewById(R.id.car_review);
        //chat1 = findViewById(R.id.chat);
        //tb = findViewById(R.id.appbar);


        Intent intent = getIntent();

        String prod_id = intent.getStringExtra("pid");
        String p_name= intent.getStringExtra("p_name");
        String p_price= intent.getStringExtra("p_price");
        String p_cat= intent.getStringExtra("p_cat");
        String p_desc= intent.getStringExtra("p_desc");
        String p_qty= intent.getStringExtra("p_sale");
        String p_size= intent.getStringExtra("size");
        Bitmap bitmap = intent.getParcelableExtra("bitmap");


        pid.setText(prod_id);
        pname.setText(p_name);
        price.setText(p_price);
        category.setText(p_cat);
        desc.setText(p_desc);
        qty.setText(p_qty);
        size.setText(p_size);
        userid.setText(uid);

        byte[] bytes = db.prodImage(prod_id);
        img_prod.setImageBitmap(getImage(bytes));

        //*************ADD CART BUTTON ONCLICK***************
        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String spid = pid.getText().toString();
                String suid = userid.getText().toString();
                String sp_qty = qty_value.getText().toString();
                String s_psize = size.getText().toString();

                db.insert_to_cart(spid, suid, sp_qty, s_psize);
                Toast.makeText(p_grid_selecteditem.this, "Product added to cart ", Toast.LENGTH_LONG).show();


            }
        });

        //***********add whishlist button click


        add_whishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spid = pid.getText().toString();
                String suid = userid.getText().toString();
                //String sp_qty = qty_value.getText().toString();
               // String s_psize = size.getText().toString();

                db.insert_to_wishlist(spid, suid);
                Toast.makeText(p_grid_selecteditem.this, "Product added to wishlist ", Toast.LENGTH_LONG).show();

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