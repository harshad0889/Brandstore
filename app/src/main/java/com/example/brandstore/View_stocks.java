package com.example.brandstore;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class View_stocks extends AppCompatActivity {
    Button add_stocks,show_stock;
    TextView ps_category,stock_q;
    DatabaseHelper db;
    String ps_cat,stock_qty;
    private ListView listView;
    EditText pick_date;

    private int mdate,mmonth,myear;

    private SimpleCursorAdapter adapter7;

    final String[] from = new String[]{db.p_category,db.stock};

    final int[] to = new int[]{R.id.ps_cat, R.id.stock_qty};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stocks);

        //db=new DatabaseHelper(this);
        show_stock= findViewById(R.id.show_stocks);
        pick_date= findViewById(R.id.stock_date);
        add_stocks= findViewById(R.id.add_stocks);
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


        /*String s_date = "2021-05-05 19:18:00";
        String sid = "5";
        Cursor cursor7 = db.stocklist(s_date);

        adapter7 = new SimpleCursorAdapter(this,R.layout.stocks_items, cursor7, from, to,0);
        adapter7.notifyDataSetChanged();

        listView.setAdapter(adapter7);*/

        pick_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                mdate = cal.get(Calendar.DATE);
                mmonth = cal.get(Calendar.MONTH);
                myear = cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(View_stocks.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        pick_date.setText(date+"-"+month+"-"+year);


                    }
                },myear,mmonth,mdate);
                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                datePickerDialog.show();


            }
        });

        show_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pick_date.length() == 0) {
                    pick_date.requestFocus();
                    pick_date.setError("please choose a date");
                }else {
                    String s_date = pick_date.getText().toString();
                    Intent in = new Intent(View_stocks.this, view_stocks2.class);
                    in.putExtra("s_date", s_date);
                    startActivity(in);
                }


            }
        });



    }
}