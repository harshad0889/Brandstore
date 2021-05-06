package com.example.brandstore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class view_stocks2 extends AppCompatActivity {
    Button add_stocks;
    TextView ps_category,stock_q;
    DatabaseHelper db;
    String ps_cat,stock_qty;
    private ListView listView;

    private SimpleCursorAdapter adapter7;

    final String[] from = new String[]{db.p_category,db.stock};

    final int[] to = new int[]{R.id.ps_cat, R.id.stock_qty};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stocks2);

        db=new DatabaseHelper(this);
        add_stocks= findViewById(R.id.add_stocks);
        listView= findViewById(R.id.lv_stocks);

        Intent intent = getIntent();

        final String s_date = intent.getStringExtra("s_date");






        //String s_date = "2021-05-05 19:18:00";
       // String sid = "5";
        Cursor cursor7 = db.stocklist(s_date);

        adapter7 = new SimpleCursorAdapter(this,R.layout.stocks_items, cursor7, from, to,0);
        adapter7.notifyDataSetChanged();

        listView.setAdapter(adapter7);
    }
}