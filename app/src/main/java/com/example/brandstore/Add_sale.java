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

public class Add_sale extends AppCompatActivity {

    DatabaseHelper db;
    EditText sale_title,sale_desc;
    TextView carowner;
    Button add_sale,cancel,addimage;
    final int REQUEST_CODE_GALLERY = 999;
    SharedPreferences sp;
    ListView lv_category;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);

        db = new DatabaseHelper(this);
        sale_title = (EditText) findViewById(R.id.sale_title);
        sale_desc = (EditText) findViewById(R.id.sale_desc);
        add_sale =  findViewById(R.id.add_sale);
        cancel= findViewById(R.id.p_cancel);
        addimage =  findViewById(R.id.addimage);
        iv = findViewById(R.id.iv);



        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions
                        (Add_sale.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);

            }
        });

        //*******************ADD SALE BUTTON CLICK****************
        add_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stitle = sale_title.getText().toString();
                String sdesc= sale_desc.getText().toString();


                if (sale_title.length() == 0) {
                    sale_title.requestFocus();
                    sale_title.setError("please enter sale name");
                } else if (sale_desc.length() == 0) {
                    sale_desc.requestFocus();
                    sale_desc.setError("please enter sale desc ");
                }else {



                    byte[] newentryimg = imageViewToByte(iv);

                    add_sale(stitle, sdesc, newentryimg);
                    Toast.makeText(Add_sale.this, "sale added succesfully ", Toast.LENGTH_LONG).show();

                    Intent r = new Intent(Add_sale.this, Home.class);
                    startActivity(r);
                    finish();

                }

            }
            private void add_sale(String stitle, String sdesc,
                                     byte[] newentryimg) {

                boolean insertsaledata = db.insert_sale_data(stitle, sdesc, newentryimg);
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