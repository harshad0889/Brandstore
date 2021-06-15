package com.example.brandstore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

        final String date2 = new SimpleDateFormat("dd MMMM YYYY", Locale.getDefault()).format(new Date());



        Cursor cursor = db2.getData(String.format("SELECT * from product_table left join sale_table on product_table.subcat = sale_table.sale_title AND  '%s' BETWEEN start_date and  end_date  WHERE  psale>0",date2));
        list.clear();
        while (cursor.moveToNext()) {
            int pid = cursor.getInt(0);
            String p_name = cursor.getString(1);
            String p_category = cursor.getString(2);
            String p_subcat = cursor.getString(3);
            String p_desc = cursor.getString(4);
            String p_size = cursor.getString(5);
            String a_price = cursor.getString(6);
           // String o_price = cursor.getString(6);
            String p_off = cursor.getString(11);
            String p_sale = cursor.getString(7);
            byte[] image = cursor.getBlob(8);

            list.add(new product( pid,  p_name,p_category,p_subcat,p_desc,p_size,a_price,  p_sale,    image,p_off));
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


                    case R.id.add_sale:
                        Intent g = new Intent(Home.this, Add_sale.class);
                        startActivity(g);
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

                    case R.id.orders:
                        Intent o = new Intent(Home.this, All_orders.class);
                        startActivity(o);
                        finish();
                        return true;

                    case R.id.stocks:
                        Intent sr = new Intent(Home.this, View_stocks.class);
                        startActivity(sr);
                        finish();
                        return true;




                    case R.id.add_cat:
                        Intent ma = new Intent(Home.this, Add_category.class);
                        startActivity(ma);
                        finish();
                        return true;

                    case R.id.logout:
                        Intent m = new Intent(Home.this, verify_phno.class);
                        startActivity(m);
                        finish();



                }

                return true;
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvid = view.findViewById(R.id.prod_id);
                TextView act_price = (TextView) view.findViewById(R.id.act_Price);
                TextView prod_desc = (TextView) view.findViewById(R.id.prod_desc);
                TextView prod_name = (TextView) view.findViewById(R.id.prod_name);
                TextView category = (TextView) view.findViewById(R.id.category);
                TextView tv_subcat = (TextView) view.findViewById(R.id.sub_cat);
                TextView sale = (TextView) view.findViewById(R.id.p_sale);
                TextView p_size = (TextView) view.findViewById(R.id.p_size);

                ImageView ivimage = view.findViewById(R.id.imgFood);



                String pid = tvid.getText().toString();
                String sa_price = act_price.getText().toString();
                String p_desc = prod_desc.getText().toString();
                String p_name = prod_name.getText().toString();
                String p_cat = category.getText().toString();
                String sp_subcat = tv_subcat.getText().toString();
                String p_sale = sale.getText().toString();
                String size = p_size.getText().toString();


                Intent s = new Intent(Home.this,p_grid_selecteditem.class);
                s.putExtra("pid",pid);
                s.putExtra("p_price",sa_price);
                s.putExtra("p_desc",p_desc);
                s.putExtra("p_name",p_name);
                s.putExtra("p_cat",p_cat);
                s.putExtra("psubcat",sp_subcat);
                s.putExtra("p_sale",p_sale);
                s.putExtra("size",size);


                startActivity(s);
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
        Intent in = new Intent(getApplicationContext(), product_view.class);
        startActivity(in);
        finish();
    }
}