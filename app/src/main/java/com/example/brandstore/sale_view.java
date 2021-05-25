package com.example.brandstore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class sale_view extends AppCompatActivity {


    GridView gridView;
    ArrayList<sale> list;
    salelistAdapter adapter = null;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_view);


        db=new DatabaseHelper(this);
        gridView =  findViewById(R.id.gv_sale);
        list = new ArrayList<>();
        adapter = new salelistAdapter(this, R.layout.sale_items, list);
        gridView.setAdapter(adapter);
        final String date2 = new SimpleDateFormat("dd MMMM YYYY", Locale.getDefault()).format(new Date());



        Cursor cursor = db.getData("SELECT * FROM sale_table");
        list.clear();
        while (cursor.moveToNext()) {
            int sid = cursor.getInt(0);
            String s_title = cursor.getString(1);
            String s_desc = cursor.getString(2);
            String prod_name = cursor.getString(3);
            String sdate = cursor.getString(4);
            String edate = cursor.getString(5);
            byte[] image = cursor.getBlob(7);

            list.add(new sale( sid, s_title,s_desc, prod_name,  sdate,  edate, image));
        }
        adapter.notifyDataSetChanged();



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView pname = view.findViewById(R.id.s_pname);
                TextView sdate = view.findViewById(R.id.sdate);
                TextView edate = view.findViewById(R.id.edate);

                String p_name = pname.getText().toString();
                String s_date = sdate.getText().toString();
                String e_date = edate.getText().toString();

                Cursor cursor = db.getData(String.format("SELECT * FROM sale_table where '%s' BETWEEN '%s' and  '%s' ",date2,s_date,e_date));
                if (cursor.getCount() == 0){
                    Toast.makeText(sale_view.this, "  sale start on "+s_date, Toast.LENGTH_LONG).show();
                }else{

                    Intent s = new Intent(sale_view.this,sale_product_view.class);
                    s.putExtra("spname",p_name);



                    startActivity(s);

                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), product_view.class);
        startActivity(in);
        finish();
    }
}