package com.example.brandstore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class sale_report2 extends AppCompatActivity {



    TextView total;
    DatabaseHelper db;
    String ps_cat,stock_qty;
    private ListView listView;

    private SimpleCursorAdapter adapter7;

    final String[] from = new String[]{db.COL_ordid,"x"};

    final int[] to = new int[]{R.id.report_ordid, R.id.report_total};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_report2);


        db=new DatabaseHelper(this);
        listView= findViewById(R.id.lv_report);
        total= findViewById(R.id.tot_amount);

        Intent intent = getIntent();

        final String s_date = intent.getStringExtra("so_date");


        Cursor cursor7 = db.reportlist(s_date);

        adapter7 = new SimpleCursorAdapter(this,R.layout.report_items, cursor7, from, to,0);
        adapter7.notifyDataSetChanged();

        listView.setAdapter(adapter7);


        Cursor cursor = db.getData(String.format("SELECT sum(total) from order_table where odate = '%s'",s_date));

        while (cursor.moveToNext()) {
            String total_amount = cursor.getString(0);
            total.setText(total_amount);
        }
    }
}