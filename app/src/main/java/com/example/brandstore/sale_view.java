package com.example.brandstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

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


        Cursor cursor = db.getData("SELECT * FROM sale_table");
        list.clear();
        while (cursor.moveToNext()) {
            int sid = cursor.getInt(0);
            String s_title = cursor.getString(1);
            String s_desc = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new sale( sid,  s_title,s_desc, image));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home.class);
        startActivity(in);
        finish();
    }
}