package com.example.brandstore;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class update_product extends AppCompatActivity {

    DatabaseHelper db;
    EditText prod_name,prod_category,prod_desc,size,sale,actual_amount,offer_amount,Offer,pid,sub_cat;
    TextView carowner;
    Button update,delete,addimage;
    final int REQUEST_CODE_GALLERY = 999;
    SharedPreferences sp;
    ListView lv_category,lv_subcate;
    ImageView iv;

    String  pname,pcategory,pdesc,psize,act_price,off_price,psale,poffer,prodid,psubcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        db = new DatabaseHelper(this);
        prod_name = (EditText) findViewById(R.id.p_name);
        pid = (EditText) findViewById(R.id.p_id);
        prod_category = (EditText) findViewById(R.id.P_category);
        sub_cat = (EditText) findViewById(R.id.P_subcategory);
        prod_desc = (EditText) findViewById(R.id.P_desc);
        size = (EditText) findViewById(R.id.P_size);
        sale = (EditText) findViewById(R.id.P_sale);
        actual_amount = (EditText) findViewById(R.id.A_price);
        offer_amount= findViewById(R.id.of_price);
        Offer =  findViewById(R.id.P_Offer);
        update =  findViewById(R.id.update_product);
        delete= findViewById(R.id.del_product);

        addimage= findViewById(R.id.addimage);
        iv = findViewById(R.id.iv);

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
        data.add("Shirts");
        data.add("Jeans");
        data.add("Tshirts");
        data.add("pants");
        data.add("Belts");
        data.add("Wallets");
        data.add("Boxers");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);
        lv_category.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(update_product.this);
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







        //

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
        AlertDialog.Builder builder8 = new AlertDialog.Builder(update_product.this);
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
                        (update_product.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });




        Intent intent = getIntent();
        String spid = intent.getStringExtra("pid");
        String sname= intent.getStringExtra("pname");
        String scat= intent.getStringExtra("pcat");
        String subcat= intent.getStringExtra("psubcat");
        String sdesc= intent.getStringExtra("pdesc");
        String ssize= intent.getStringExtra("psize");
        String sacprice= intent.getStringExtra("pactprice");
        String sofprice= intent.getStringExtra("pofprice");
        String ssale= intent.getStringExtra("psale");
        String soff= intent.getStringExtra("poff");


        pid.setText(spid);
        prod_name.setText(sname);
        prod_category.setText(scat);
        sub_cat.setText(subcat);
        prod_desc.setText(sdesc);
        size.setText(ssize);
        actual_amount.setText(sacprice);
       // offer_amount.setText(sofprice);
        sale.setText(ssale);
       // Offer.setText(soff);
        byte[] bytes = db.prodImage(spid);
        iv.setImageBitmap(getImage(bytes));



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prodid = pid.getText().toString();
                pname = prod_name.getText().toString();
                pcategory = prod_category.getText().toString();
                psubcat=sub_cat.getText().toString();
                pdesc = prod_desc.getText().toString();
                psize = size.getText().toString();
                act_price = actual_amount.getText().toString();
                //off_price = offer_amount.getText().toString();
                psale = sale.getText().toString();
               // poffer = Offer.getText().toString();
                //caddcar = addcar.getText().toString();
                //ccarreset = carreset.getText().toString();


                if (prod_name.length() == 0) {
                    prod_name.requestFocus();
                    prod_name.setError("please enter product name");
                } else if (prod_category.length() == 0) {
                    prod_category.requestFocus();
                    prod_category.setError("please enter product category");
                } else if (sub_cat.length() == 0) {
                    sub_cat.requestFocus();
                    sub_cat.setError("please choose a sub category");
                } else if (prod_desc.length() == 0) {
                    prod_desc.requestFocus();
                    prod_desc.setError("please enter product desc");
                } else if (size.length() == 0) {
                    size.requestFocus();
                    size.setError("please choose size");
                }  else if (actual_amount.length() == 0) {
                    actual_amount.requestFocus();
                    actual_amount.setError("please enter  actual amount");

                }
                else if (sale.length() == 0) {
                    sale.requestFocus();
                    sale.setError("please enter  sale");
                }
                else {


                    Toast.makeText(update_product.this, "Product updated succesfully ", Toast.LENGTH_LONG).show();
                    byte[] newentryimg = imageViewToByte(iv);

                    updateprod(prodid,pname, pcategory,psubcat, pdesc, psize, act_price, psale,  newentryimg);

                    Intent r = new Intent(update_product.this, product_view.class);
                    startActivity(r);
                    finish();



                }
            }

            private void updateprod(String prodid,String pname, String pcategory,String psubcat, String pdesc, String
                    psize, String act_price,  String psale,
                                    byte[] newentryimg){

                int insertproduct = db.updateproduct(prodid,pname, pcategory,psubcat, pdesc, psize, act_price,  psale, newentryimg);
            }
            private byte[] imageViewToByte (ImageView iv){

                Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prodid = pid.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(update_product.this);
                builder.setTitle("Delete product");
                builder.setMessage("Do you want to delete?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int del_product = db.delete_product(prodid);
                        Toast.makeText(update_product.this, " deleted ", Toast.LENGTH_LONG).show();
                        Intent m = new Intent(update_product.this, product_view.class);
                        startActivity(m);
                        finish();



                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(update_product.this, "  ", Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();








                Toast.makeText(update_product.this, "Product deleted succesfully ", Toast.LENGTH_LONG).show();

            }
        });

    }
    public static Bitmap getImage(byte[] image) {

        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}