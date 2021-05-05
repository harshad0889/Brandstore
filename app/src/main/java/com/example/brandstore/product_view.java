package com.example.brandstore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class product_view extends AppCompatActivity {

    GridView gridView;
    RecyclerView rV_cat;
    ArrayList<product> list;
    ArrayList<category> catlists;
    productListAdapter adapter = null;
    catlistAadapter adapter2 = null;
    private NavigationView nv;
    private DrawerLayout dl;
    private Toolbar tb;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        db=new DatabaseHelper(this);
        gridView =  findViewById(R.id.gv_product);
        dl = findViewById(R.id.drawer);
        nv = findViewById(R.id.nav);
        rV_cat =  findViewById(R.id.rv_categoreis);
        tb=findViewById(R.id.appbar);

        list = new ArrayList<>();
        adapter = new productListAdapter(this, R.layout.product_items, list);
        gridView.setAdapter(adapter);





        Cursor cursor = db.getData("SELECT * FROM product_table");
        list.clear();
        while (cursor.moveToNext()) {
            int pid = cursor.getInt(0);
            String p_name = cursor.getString(1);
            String p_category = cursor.getString(2);
            String p_desc = cursor.getString(3);
            String p_size = cursor.getString(4);
            String a_price = cursor.getString(5);
           // String o_price = cursor.getString(6);
           // String p_off = cursor.getString(8);
            String p_sale = cursor.getString(6);
            byte[] image = cursor.getBlob(7);

            list.add(new product( pid,  p_name,p_category,p_desc,p_size,a_price,  p_sale,    image));
        }
        adapter.notifyDataSetChanged();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvid = view.findViewById(R.id.prod_id);
                TextView act_price = (TextView) view.findViewById(R.id.act_Price);
                TextView prod_desc = (TextView) view.findViewById(R.id.prod_desc);
                TextView prod_name = (TextView) view.findViewById(R.id.prod_name);
                TextView category = (TextView) view.findViewById(R.id.category);
                TextView sale = (TextView) view.findViewById(R.id.p_sale);
                TextView p_size = (TextView) view.findViewById(R.id.p_size);

                ImageView ivimage = view.findViewById(R.id.imgFood);



                String pid = tvid.getText().toString();
                String sa_price = act_price.getText().toString();
                String p_desc = prod_desc.getText().toString();
                String p_name = prod_name.getText().toString();
                String p_cat = category.getText().toString();
                String p_sale = sale.getText().toString();
                String size = p_size.getText().toString();


                Intent s = new Intent(product_view.this,p_grid_selecteditem.class);
                s.putExtra("pid",pid);
                s.putExtra("p_price",sa_price);
                s.putExtra("p_desc",p_desc);
                s.putExtra("p_name",p_name);
                s.putExtra("p_cat",p_cat);
                s.putExtra("p_sale",p_sale);
                s.putExtra("size",size);


                startActivity(s);
            }
        });


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
                        Intent add = new Intent(product_view.this, Add_product.class);
                        startActivity(add);
                        finish();
                        return true;

                    case R.id.sales:
                        Intent s = new Intent(product_view.this, sale_view.class);
                        startActivity(s);
                        finish();
                        return true;


                    case R.id.add_sale:
                        Intent g = new Intent(product_view.this, Add_sale.class);
                        startActivity(g);
                        finish();
                        return true;

                    case R.id.update_prod:
                        Intent r = new Intent(product_view.this, product_updview.class);
                        startActivity(r);
                        finish();
                        return true;
                    case R.id.orders:
                        Intent o = new Intent(product_view.this, All_orders.class);
                        startActivity(o);
                        finish();
                        return true;

                    case R.id.view_product:
                        Intent u = new Intent(product_view.this, product_view.class);
                        startActivity(u);
                        finish();
                        return true;




                    case R.id.add_cat:
                        Intent main = new Intent(product_view.this, Add_category.class);
                        startActivity(main);
                        finish();



                }

                return true;
            }
        });




        // for recycler view
        catlists = new ArrayList<>();
        catlistAadapter catadapter = new catlistAadapter(product_view.this, catlists);
        rV_cat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rV_cat.setAdapter(catadapter);


        Cursor cursor2 = db.getData("SELECT * FROM cate_table");

        while (cursor2.moveToNext()) {
            int cat_id = cursor2.getInt(0);
            String cat_name = cursor2.getString(1);
            byte[] img = cursor2.getBlob(2);

            catlists.add(new category(cat_id, cat_name, img));
        }

        //rV_cat.setHasFixedSize(true);
        //catlistAadapter catadapter = new catlistAadapter(product_view.this, catlists);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

       // rV_cat.setAdapter(catadapter);
       // catadapter.notifyDataSetChanged();




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