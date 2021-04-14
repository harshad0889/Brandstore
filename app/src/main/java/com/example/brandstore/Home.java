package com.example.brandstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    Button addproduct;
    DatabaseHelper db2;
    private DrawerLayout dl;
    private NavigationView nv;
    private Toolbar tb;
    GridView gridView;
    ArrayList<product> list;
    productListAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addproduct = findViewById(R.id.add_product);
        db2 = new DatabaseHelper(this);
        dl = findViewById(R.id.drawer);
        nv = findViewById(R.id.nav);
        tb=findViewById(R.id.appbar);

        gridView =  findViewById(R.id.gv_product2);
        list = new ArrayList<>();
        adapter = new productListAdapter(this, R.layout.product_items, list);
        gridView.setAdapter(adapter);



        Cursor cursor = db2.getData("SELECT * FROM product_table");
        list.clear();
        while (cursor.moveToNext()) {
            int pid = cursor.getInt(0);
            String p_name = cursor.getString(1);
            String p_category = cursor.getString(2);
            String p_desc = cursor.getString(3);
            String p_size = cursor.getString(4);
            String a_price = cursor.getString(5);
            String o_price = cursor.getString(6);
            String p_off = cursor.getString(8);
            String p_sale = cursor.getString(7);
            byte[] image = cursor.getBlob(9);

            list.add(new product( pid,  p_name,a_price,  o_price,p_sale, p_category, p_desc, p_size, p_off, image));
        }
        adapter.notifyDataSetChanged();







        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menuicon);


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){

                    case R.id.add_product:
                        Intent add = new Intent(Home.this, Add_product.class);
                        startActivity(add);
                        finish();
                        return true;


                    case R.id.sales:
                        Intent s = new Intent(Home.this, sale_view.class);
                        startActivity(s);
                        finish();
                        return true;

                    case R.id.update_prod:
                        Intent r = new Intent(Home.this, product_updview.class);
                        startActivity(r);
                        finish();
                        return true;

                    case R.id.view_product:
                        Intent u = new Intent(Home.this, product_view.class);
                        startActivity(u);
                        finish();
                        return true;




                    case R.id.add_cat:
                        Intent main = new Intent(Home.this, Add_category.class);
                        startActivity(main);
                        finish();



                }

                return true;
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home.class);
        startActivity(in);
        finish();
    }
}