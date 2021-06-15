package com.example.brandstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class track_order extends AppCompatActivity {
    DatabaseHelper db;
    String uid,username;
    SharedPreferences sd;
    Button bt_cancel,bt_return;

    private SimpleCursorAdapter adapter2;
    private ListView lv_status;
    final String[] from = new String[]{db.s_cartid,db.s_status,db.sdate};

    final int[] to = new int[]{R.id.cartid, R.id.status, R.id.s_date};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);

        db = new DatabaseHelper(this); db = new DatabaseHelper(this);

        lv_status = findViewById(R.id.lv_status);

        Intent intent = getIntent();

        String status_cart_id = intent.getStringExtra("statuss_cart_id");

       // String status_cart_id = cart_id.getText().toString();
        Cursor cursor4 = db.status_list(status_cart_id);

        adapter2 = new SimpleCursorAdapter(this,R.layout.status_items, cursor4, from, to,0);
        adapter2.notifyDataSetChanged();

        lv_status.setAdapter(adapter2);
        TextView stat = findViewById(R.id.status);
        //String status_val = stat.getText().toString();
        //if (status_val == "ORDERED"){
       //         stat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shipment,0,0,0);
       // }
    }

}