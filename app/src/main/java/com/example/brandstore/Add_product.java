package com.example.brandstore;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Add_product extends AppCompatActivity {


    DatabaseHelper db;
    EditText prod_name,prod_category,prod_desc,size,sale,actual_amount,offer_amount,Offer,sub_cat;
    TextView carowner;
    Button add_product,cancel,addimage;
    final int REQUEST_CODE_GALLERY = 999;
    SharedPreferences sp;
    ListView lv_category;
    ListView list_size,list_size_shirts,lv_subcate;
    ImageView iv;

    String  pname,pcategory,pdesc,psize,act_price,off_price,psale,poffer,subcat;

    private SimpleCursorAdapter adapter;

   // final String[] from = new String[]{db.COL_ctname};

    //final int[] to = new int[]{R.id.duid};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        db = new DatabaseHelper(this);
        prod_name = (EditText) findViewById(R.id.p_name);
        prod_category = (EditText) findViewById(R.id.P_category);
        sub_cat = (EditText) findViewById(R.id.P_subcategory);
        prod_desc = (EditText) findViewById(R.id.P_desc);
        size = (EditText) findViewById(R.id.P_size);
        sale = (EditText) findViewById(R.id.P_sale);
        actual_amount = (EditText) findViewById(R.id.A_price);
        offer_amount= findViewById(R.id.of_price);
        Offer =  findViewById(R.id.P_Offer);
        add_product =  findViewById(R.id.add_product);
        cancel= findViewById(R.id.p_cancel);
        addimage =  findViewById(R.id.addimage);
        addimage= findViewById(R.id.addimage);
        iv = findViewById(R.id.iv);

//"SELECT  DISTINCT cat_name from cate_table"


        List<String> names = new ArrayList<>();
        Cursor cursor = db.getData("SELECT  DISTINCT cat_name from cate_table");
        if(cursor != null){
            while(cursor.moveToNext()){
                names.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ctname)));

            }
        }


                lv_category = findViewById(R.id.lvcat);
        lv_category = new ListView(this);
        List<String> data = new ArrayList<>();
        data.add("SHIRTS");
        data.add("JEANS");
        data.add("T SHIRTS");
        data.add("PANTS");
        data.add("BELTS");
        data.add("WALLETS");
        data.add("BOXERS");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);
        lv_category.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(Add_product.this);
        builder.setCancelable(true);
        builder.setView(lv_category);
        final  AlertDialog dialog = builder.create();






        prod_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                lv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        prod_category.setText(adapter.getItem(i));
                        dialog.dismiss();
                    }
                });
            }
        });



        //// for subcatgory

        List<String> names8 = new ArrayList<>();
        Cursor cursor8 = db.getData("SELECT  DISTINCT sale_title from sale_table");
        if(cursor8 != null){
            while(cursor8.moveToNext()){
                names8.add(cursor8.getString(cursor8.getColumnIndex(DatabaseHelper.COL_stitle)));


            }
        }


        lv_subcate = findViewById(R.id.lv_subcat);
        lv_subcate = new ListView(this);
        List<String> data8 = new ArrayList<>();

        names8.add("NO SALE");
        final ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names8);
        lv_subcate.setAdapter(adapter8);
        AlertDialog.Builder builder8 = new AlertDialog.Builder(Add_product.this);
        builder8.setCancelable(true);
        builder8.setView(lv_subcate);
        final  AlertDialog dialog8 = builder8.create();






        sub_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog8.show();

                lv_subcate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        sub_cat.setText(adapter8.getItem(i));
                        dialog8.dismiss();
                    }
                });
            }
        });

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions
                        (Add_product.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });





        //***********size select list for shirts*******


        list_size = new ListView(this);
        List<String> data5 = new ArrayList<>();
        data5.add("30");
        data5.add("32");
        data5.add("34");
        data5.add("36");
        data5.add("38");

        final ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data5);
        list_size.setAdapter(adapter5);
        AlertDialog.Builder builder5 = new AlertDialog.Builder(Add_product.this);
        builder5.setCancelable(true);
        builder5.setView(list_size);
        final  AlertDialog dialog5 = builder5.create();



        //***********size select list for pants*******


        list_size_shirts = new ListView(this);
        List<String> sdata = new ArrayList<>();
        sdata.add("S");
        sdata.add("M");
        sdata.add("L");
        sdata.add("XL");
        sdata.add("XXL");
        sdata.add("No size");

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,sdata);
        list_size_shirts.setAdapter(adapter2);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(Add_product.this);
        builder2.setCancelable(true);
        builder2.setView(list_size_shirts);
        final  AlertDialog dialog2 = builder2.create();

        //*******size text onclick
        size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String scat = prod_category.getText().toString();
                if (scat.equals("pants") || scat.equals("PANTS") || scat.equals("JEANS")  || scat.equals("TROUSERS") || scat.equals("BOXERS")){
                    dialog5.show();

                }else if(scat.equals("shirts") || scat.equals("SHIRTS") || scat.equals("T SHIRTS") ){
                    dialog2.show();
                }else{
                    dialog2.show();
                }

            }
        });

        //**********list view shirts onclick lsitener****
        list_size_shirts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                size.setText(adapter2.getItem(position));

                dialog2.dismiss();


            }
        });

        //**********list view onclick lsitener****
        list_size.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                size.setText(adapter5.getItem(position));

                dialog5.dismiss();


            }
        });










        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pname = prod_name.getText().toString();
                pcategory = prod_category.getText().toString();
                pdesc = prod_desc.getText().toString();
                psize = size.getText().toString();
                act_price = actual_amount.getText().toString();
                off_price = offer_amount.getText().toString();
                psale = sale.getText().toString();
                poffer = Offer.getText().toString();
                subcat = sub_cat.getText().toString();
               //ccarreset = carreset.getText().toString();


                if (prod_name.length() == 0) {
                    prod_name.requestFocus();
                    prod_name.setError("please enter product name");
                } else if (prod_category.length() == 0) {
                    prod_category.requestFocus();
                    prod_category.setError("please enter product category");
                } else if (prod_desc.length() == 0) {
                    prod_desc.requestFocus();
                    prod_desc.setError("please enter product desc");
                } else if (size.length() == 0) {
                    size.requestFocus();
                    size.setError("please choose size");
                }  else if (actual_amount.length() == 0) {
                    actual_amount.requestFocus();
                    actual_amount.setError("please enter  actual amount");

                }else if (sub_cat.length() == 0) {
                    sub_cat.requestFocus();
                    sub_cat.setError("please choose sub category");

                }
                else if (sale.length() == 0) {
                    sale.requestFocus();
                    sale.setError("please enter  sale");
                }
               else {


                    Toast.makeText(Add_product.this, "Product added succesfully ", Toast.LENGTH_LONG).show();
                    byte[] newentryimg = imageViewToByte(iv);

                    Addproduct(pname, pcategory,subcat, pdesc, psize, act_price,  psale, newentryimg);

                    Intent r = new Intent(Add_product.this, Home.class);
                    startActivity(r);
                    finish();



                }
            }

            private void Addproduct (String cname, String c_cat,String c_subcat, String cdesc, String
                    csize, String cact_price,  String csale,
                                  byte[] newentryimg){

                boolean insertcardata = db.insert_prod_data(cname, c_cat,c_subcat, cdesc, csize, cact_price,  csale, newentryimg);
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

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home.class);
        startActivity(in);
        finish();
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), product_view.class);
        startActivity(in);
        finish();
    }
}