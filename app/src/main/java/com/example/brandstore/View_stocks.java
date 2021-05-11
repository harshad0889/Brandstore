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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class View_stocks extends AppCompatActivity {
    Button add_stocks,show_stock,ref_stock;
    TextView ps_category,stock_q;
    DatabaseHelper db;
    String ps_cat,stock_qty;
    private ListView listView;
    EditText pick_date;
    String sp_cat,sp_date;

    private int mdate,mmonth,myear,smonth,x=1;

    private SimpleCursorAdapter adapter7;

    final String[] from = new String[]{db.p_category,db.stock};

    final int[] to = new int[]{R.id.ps_cat, R.id.stock_qty};
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stocks);

        db=new DatabaseHelper(this);
        show_stock= findViewById(R.id.show_stocks);
        pick_date= findViewById(R.id.stock_date);
        add_stocks= findViewById(R.id.add_stocks);
        ref_stock= findViewById(R.id.refresh_stocks);

       final String date2 = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault()).format(new Date());


        add_stocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor1 = db.getData(String.format("select s_date from stocks where s_date = '%s' ",date2));
                 if (cursor1.getCount() == 0) {
                     Cursor cursor = db.getData("SELECT DISTINCT  pcategory, sum(psale) from product_table group by pcategory");


                     while (cursor.moveToNext()) {
                         for (int i = 0; i< cursor.getColumnNames().length;i++) {

                             ps_cat = cursor.getString(0);
                             stock_qty = cursor.getString(1);

                             // add_stocks.setText(ps_cat);
                         }
                         Log.e("added", stock_qty + ps_cat);

                         boolean insert_stocks = db.insert_stocks(ps_cat,stock_qty,date2);
                         Toast.makeText(View_stocks.this, "  STOCK UPDATED SUCCESSFULLY  ", Toast.LENGTH_LONG).show();



                     }

                }
                else {
                    Toast.makeText(View_stocks.this, "Stock exists ", Toast.LENGTH_LONG).show();

                }







                //
                /*Cursor cursor = db.getData("SELECT DISTINCT  pcategory, sum(psale) from product_table group by pcategory");


                while (cursor.moveToNext()) {
                    for (int i = 0; i< cursor.getColumnNames().length;i++) {

                        ps_cat = cursor.getString(0);
                        stock_qty = cursor.getString(1);

                        // add_stocks.setText(ps_cat);
                    }
                    Log.e("added", stock_qty + ps_cat);

                    boolean insert_stocks = db.insert_stocks(ps_cat,stock_qty,date2);
                    Toast.makeText(View_stocks.this, "  STOCK UPDATED SUCCESSFULLY  ", Toast.LENGTH_LONG).show();



                }*/

            }
        });


        ref_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Cursor cursor2 = db.getData("select pcat,s_date from stocks ");


                while (cursor2.moveToNext()) {
                    for (int i = 0; i < cursor2.getColumnNames().length; i++) {

                        sp_cat = cursor2.getString(0);
                        sp_date = cursor2.getString(1);


                        // add_stocks.setText(ps_cat);
                    }
                    Log.e("dates", sp_cat + "," + sp_date + "," + date2);

                    Log.e("dat", sp_cat + "," + sp_date + "," + date2);
                    if (sp_date.equals(date2)) {
                        Toast.makeText(View_stocks.this, "  STOCK EXIST  ", Toast.LENGTH_LONG).show();
                        break;
                    }

                }

                    //boolean insert_stocks = db.insert_stocks(ps_cat,stock_qty);
                    //dude just run the code n see what happens .LOL!
                    //Log.e("samp", stock_qty + ps_cat);

                if (!sp_date.equals(date2)) {

                    Cursor cursor = db.getData("SELECT DISTINCT  pcategory, sum(psale) from product_table group by pcategory");


                    while (cursor.moveToNext()) {
                        for (int i = 0; i< cursor.getColumnNames().length;i++) {

                            ps_cat = cursor.getString(0);
                            stock_qty = cursor.getString(1);

                            // add_stocks.setText(ps_cat);
                        }
                        Log.e("added", stock_qty + ps_cat);

                        boolean insert_stocks = db.insert_stocks(ps_cat,stock_qty,date2);
                        Toast.makeText(View_stocks.this, "  stock added  ", Toast.LENGTH_LONG).show();



                    }
                    //Toast.makeText(View_stocks.this, "  stock added  ", Toast.LENGTH_LONG).show();
                }



                    /*if ( sp_date.equals(date2)){
                        Toast.makeText(View_stocks.this, " this  exists  ", Toast.LENGTH_LONG).show();
                    }
                    else{
                        //boolean insert_stocks = db.insert_stocks(ps_cat,stock_qty);
                        //dude just run the code n see what happens .LOL!
                        //Log.e("samp", stock_qty + ps_cat);
                        Toast.makeText(View_stocks.this, "  stock added  ", Toast.LENGTH_LONG).show();


                        Cursor cursor3 = db.getData("SELECT DISTINCT  pcategory, sum(psale) from product_table group by pcategory");

                        while (cursor3.moveToNext()) {
                            for (int i = 0; i< cursor3.getColumnNames().length;i++) {

                                ps_cat = cursor3.getString(0);
                                stock_qty = cursor3.getString(1);

                                // add_stocks.setText(ps_cat);
                            }
                            Log.e("samp", stock_qty + ps_cat);
                            // boolean insert_stocks = db.insert_stocks(ps_cat,stock_qty);






                        }

                    }*/










            }
        });


        /*String s_date = "2021-05-05 19:18:00";
        String sid = "5";
        Cursor cursor7 = db.stocklist(s_date);

        adapter7 = new SimpleCursorAdapter(this,R.layout.stocks_items, cursor7, from, to,0);
        adapter7.notifyDataSetChanged();

        listView.setAdapter(adapter7);*/
        //date picker dialogue
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayofmonth);
                String Myformat = "YYYY-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(Myformat, Locale.US);
                pick_date.setText(sdf.format(myCalendar.getTime()));

            }
        };

        pick_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* final Calendar cal = Calendar.getInstance();
                mdate = cal.get(Calendar.DATE);
                smonth = cal.get(Calendar.MONTH) + 1;
                mmonth = smonth+x;
                myear = cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(View_stocks.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        pick_date.setText(year+"-0"+month+"-"+date);


                    }
                },myear,mmonth,mdate);
                //datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                datePickerDialog.show();*/
               new DatePickerDialog(View_stocks.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();


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