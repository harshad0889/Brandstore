package com.example.brandstore;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class View_stocks extends AppCompatActivity {
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
        setContentView(R.layout.activity_view_stocks);

        db=new DatabaseHelper(this);
        add_stocks= findViewById(R.id.add_stocks);
        listView= findViewById(R.id.lv_stocks);
        add_stocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.getData("SELECT  pcategory, sum(psale) from product_table group by pcategory");


                while (cursor.moveToNext()) {
                    for (int i = 0; i< cursor.getColumnNames().length;i++) {

                        ps_cat = cursor.getString(0);
                        stock_qty = cursor.getString(1);

                        // add_stocks.setText(ps_cat);
                    }
                    Log.e("samp", stock_qty + ps_cat);
                    boolean insert_stocks = db.insert_stocks(ps_cat,stock_qty);



                }

            }
        });

        String s_date = "2021-05-05 19:18:00";
        String sid = "5";
        Cursor cursor7 = db.stocklist(s_date);

        adapter7 = new SimpleCursorAdapter(this,R.layout.stocks_items, cursor7, from, to,0);
        adapter7.notifyDataSetChanged();

        listView.setAdapter(adapter7);



    }
}