package com.example.brandstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    Button addproduct;
    DatabaseHelper db2;
    private DrawerLayout dl;
    private NavigationView nv;
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addproduct = findViewById(R.id.add_product);
        db2 = new DatabaseHelper(this);
        dl = findViewById(R.id.drawer);
        nv = findViewById(R.id.nav);
        tb=findViewById(R.id.appbar);


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
                        Intent main = new Intent(Home.this, Add_product.class);
                        startActivity(main);
                        finish();
                        return true;
                   /* case R.id.addcar:
                        Intent main = new Intent(Home.this, Addcar.class);
                        startActivity(main);
                        finish();
                        Toast.makeText(Home.this, "added car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.rentdriver:
                        Toast.makeText(Home.this, "rented driver", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.adddriver:
                        Intent intent= new Intent(Home.this,Adddriver.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Home.this, "added driver", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.notification:
                        Intent s = new Intent(Home.this, notifview.class);
                        startActivity(s);
                        finish();
                        Toast.makeText(Home.this, "noti car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.dnotification:
                        Intent d = new Intent(Home.this, notifdriview.class);
                        startActivity(d);
                        finish();
                        Toast.makeText(Home.this, "noti car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.updatecar:
                        Intent m = new Intent(Home.this, Carview2.class);
                        startActivity(m);
                        finish();
                        Toast.makeText(Home.this, "updated car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.updatedriver:
                        Intent r = new Intent(Home.this, Driverview2.class);
                        startActivity(r);
                        finish();
                        Toast.makeText(Home.this, "update driver", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.about:
                        Toast.makeText(Home.this, "about", Toast.LENGTH_SHORT).show();
                        return true;*/

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
}