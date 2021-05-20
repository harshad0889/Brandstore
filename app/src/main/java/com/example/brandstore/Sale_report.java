package com.example.brandstore;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Sale_report extends AppCompatActivity {

    EditText pick_date;
    String sp_cat,sp_date;
    Button add_stocks,show_report,ref_stock;
    TextView ps_category,stock_q;
    DatabaseHelper db;





    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_report);


        db=new DatabaseHelper(this);
        show_report= findViewById(R.id.show_report);
        pick_date= findViewById(R.id.report_date);

        final String date2 = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault()).format(new Date());



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

                new DatePickerDialog(Sale_report.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });


        show_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pick_date.length() == 0) {
                    pick_date.requestFocus();
                    pick_date.setError("please choose a date");
                }else {
                    String s_date = pick_date.getText().toString();
                    Intent in = new Intent(Sale_report.this, sale_report2.class);
                    in.putExtra("so_date", s_date);
                    startActivity(in);
                }


            }
        });

    }
}