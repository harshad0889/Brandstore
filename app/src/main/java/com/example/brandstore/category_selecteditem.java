package com.example.brandstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

public class category_selecteditem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selecteditem);


        Intent intent = getIntent();

        String catid = intent.getStringExtra("catid");
        String sbrand= intent.getStringExtra("catname");

        Bitmap bitmap = intent.getParcelableExtra("catimage");
    }
}