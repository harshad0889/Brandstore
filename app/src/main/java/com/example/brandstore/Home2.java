package com.example.brandstore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Home2 extends AppCompatActivity {
    private Toolbar tb;
    GridView gridView;
    ArrayList<product> list;
    productListAdapter adapter = null;
    DatabaseHelper db2;
    private DrawerLayout dl;
    private NavigationView nv;
    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        db2 = new DatabaseHelper(this);
        dl = findViewById(R.id.drawer);
        nv = findViewById(R.id.nav);
        tb=findViewById(R.id.appbar);
        frame=findViewById(R.id.frame_cotainer);

        //gridView =  findViewById(R.id.gv_product3);
        list = new ArrayList<>();
        adapter = new productListAdapter(this, R.layout.product_items, list);
       // gridView.setAdapter(adapter);
        final String date2 = new SimpleDateFormat("dd MMMM YYYY", Locale.getDefault()).format(new Date());

        //load default fragment

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_cotainer,new home_fragment()).commit();





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

            list.add(new product( pid,  p_name, p_category,p_subcat, p_desc,p_size,a_price,  p_sale,   image,p_off));
        }
       // adapter.notifyDataSetChanged();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedfrag =null;


                switch (item.getItemId()) {
                    case R.id.home_nav:
                        selectedfrag = new home_fragment();
                        break;



                    case R.id.category_nav:
                        if(sp_manager.getUser2(getApplicationContext()).length() == 0)
                        {
                            Toast.makeText(getApplicationContext(), "Please login ", Toast.LENGTH_LONG).show();
                            Intent homeIntent = new Intent(getApplicationContext(),verify_phno.class);
                            startActivity(homeIntent);
                        }else{

                            selectedfrag = new cat_fragment();
                            break;

                        }


                    case R.id.sale_nav:
                        if(sp_manager.getUser2(getApplicationContext()).length() == 0)
                        {
                            Toast.makeText(getApplicationContext(), "Please login ", Toast.LENGTH_LONG).show();
                            Intent homeIntent = new Intent(getApplicationContext(),verify_phno.class);
                            startActivity(homeIntent);
                        }else{

                            selectedfrag = new sale_fragment();
                            break;

                        }



                    case R.id.cart_nav:
                        if(sp_manager.getUser2(getApplicationContext()).length() == 0)
                        {
                            Toast.makeText(getApplicationContext(), "Please login ", Toast.LENGTH_LONG).show();
                            Intent homeIntent = new Intent(getApplicationContext(),verify_phno.class);
                            startActivity(homeIntent);

                        }else{

                            selectedfrag = new cart_fragment();
                            break;

                        }


                    case R.id.profile:


                            selectedfrag = new viewprofile_fragment();
                            break;



                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_cotainer,selectedfrag).commit();
                return true;
            }
        });







    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home2.class);
        startActivity(in);
        finish();
    }
}



/*<GridView
        android:id="@+id/gv_product3"
        android:layout_width="match_parent"
        android:layout_height="match_parent"

        android:layout_marginTop="50dp"
        android:background="@color/white"


        android:fadingEdge="vertical"
        android:gravity="center"
        android:horizontalSpacing="10dp"
        android:numColumns="2"
        android:stretchMode="columnWidth"
        android:columnWidth="90dp"
        android:padding="5dp"
        android:verticalSpacing="10dp"

        >
    </GridView>*/