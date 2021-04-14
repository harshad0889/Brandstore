package com.example.brandstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Add_category extends AppCompatActivity {


    DatabaseHelper db;
    EditText cate_name;
    TextView carowner;
    Button add_cat,cancel,addimage;
    final int REQUEST_CODE_GALLERY = 999;
    SharedPreferences sp;
    ListView lv_category;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        db = new DatabaseHelper(this);
        cate_name = (EditText) findViewById(R.id.cat_name);
        add_cat =  findViewById(R.id.add_cat);
        cancel= findViewById(R.id.p_cancel);
        addimage =  findViewById(R.id.addimage);
        iv = findViewById(R.id.iv);


        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions
                        (Add_category.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);

            }
        });


        add_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ctname = cate_name.getText().toString();



                if (cate_name.length() == 0) {
                    cate_name.requestFocus();
                    cate_name.setError("please enter category name");
                } else {



                    byte[] newentryimg = imageViewToByte(iv);

                    add_cate(ctname, newentryimg);
                    Toast.makeText(Add_category.this, "category added succesfully ", Toast.LENGTH_LONG).show();

                    Intent r = new Intent(Add_category.this, Home.class);
                    startActivity(r);
                    finish();

                }
            }
            private void add_cate(String ctname,
                                  byte[] newentryimg) {

                boolean insert_cate = db.add_cate(ctname,  newentryimg);
            }


            private byte[] imageViewToByte (ImageView iv){

                Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
            }
        });
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Uri imageuri = data.getData();
        try {
            InputStream is = getContentResolver().openInputStream(imageuri);

            Bitmap bitmap = BitmapFactory.decodeStream(is);
            iv.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home.class);
        startActivity(in);
        finish();
    }
}