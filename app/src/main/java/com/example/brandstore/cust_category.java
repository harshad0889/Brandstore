package com.example.brandstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class cust_category extends AppCompatActivity {
    RecyclerView rv_cat;
    ArrayList<category> catlists;

    catlistAadapter adapter2 = null;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_category);

        db=new DatabaseHelper(this);

        rv_cat =  findViewById(R.id.rv_categoreis);





        // for recycler view
        catlists = new ArrayList<>();
        catlistAadapter catadapter = new catlistAadapter(cust_category.this, catlists);
        rv_cat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv_cat.setAdapter(catadapter);


        Cursor cursor2 = db.getData("SELECT * FROM cate_table");

        while (cursor2.moveToNext()) {
            int cat_id = cursor2.getInt(0);
            String cat_name = cursor2.getString(1);
            byte[] img = cursor2.getBlob(2);

            catlists.add(new category(cat_id, cat_name, img));
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home2.class);
        startActivity(in);
        finish();
    }
}