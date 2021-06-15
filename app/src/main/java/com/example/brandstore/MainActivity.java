package com.example.brandstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    Button start1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        Thread splash=new Thread(){
            public void run() {
                try {
                    sleep(3 * 1000);
                    Intent homeIntent = new Intent(MainActivity.this, Home2.class);
                    startActivity(homeIntent);
                    finish();
                } catch (Exception e) {
                }
            }
        };
        splash.start();






        start1 =  findViewById(R.id.start);


        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r = new Intent(MainActivity.this, verify_phno.class);
                startActivity(r);
                //<include layout="@layout/appbar" />
            }
        });






    }
}


/*if(sp_manager.getUser2(MainActivity.this).length() == 0)
        {
            Intent homeIntent = new Intent(MainActivity.this,verify_phno.class);
            startActivity(homeIntent);
        }else{

            Intent homeIntent = new Intent(MainActivity.this,Home2.class);
            startActivity(homeIntent);

        }*/