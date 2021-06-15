package com.example.brandstore;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class whishlist extends AppCompatActivity {

    whislistAdapter adapter = null;
    DatabaseHelper db2;
    GridView gv_wish;
    SharedPreferences sp;
    String uid,username;
    Button rmv,place_order,add_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whishlist);

        db2 = new DatabaseHelper(this);
        sp = getSharedPreferences("user_details", Context.MODE_PRIVATE);
        uid = sp.getString("uid",null);
        username  = sp.getString("username",null);


        gv_wish = findViewById(R.id.gv_wishlist);
       // Cart_total = view.findViewById(R.id.tv_total);
        ArrayList<wish> wishlist;
        wishlist = new ArrayList<>();
        final Context context;
        adapter = new whislistAdapter(this, R.layout.wish_items, wishlist);
        gv_wish.setAdapter(adapter);







        Cursor cursor = db2.getData(String.format("SELECT * FROM wish_table JOIN product_table on  wish_table.pid = product_table.pid WHERE _id ='%s'",uid));
        wishlist.clear();
        while (cursor.moveToNext()) {
            int pid = cursor.getInt(1);
            String p_name = cursor.getString(6);
            String price = cursor.getString(11);
            String p_desc = cursor.getString(9);
            String p_qty = cursor.getString(3);
            String p_size = cursor.getString(4);
            String uid = cursor.getString(2);
            byte[] image = cursor.getBlob(13);

            wishlist.add(new wish( pid,  p_name, price, p_desc,p_size,p_qty,uid,  image));
        }
        adapter.notifyDataSetChanged();
    }
}