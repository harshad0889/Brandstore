package com.example.brandstore;

import android.Manifest;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Add_sale extends AppCompatActivity {

    DatabaseHelper db;
    EditText sale_title,sale_desc,s_start,s_end;
    TextView prodlist;
    Button add_sale,cancel,addimage,chooseprod;
    final int REQUEST_CODE_GALLERY = 999;
    SharedPreferences sp;
    ListView lv_category;
    ImageView iv;
    private int mdate,mmonth,myear;
    String[] listitems;
    boolean[] checkeditems;
    String[] day = {"pants","shirts"};
    String sl_start,sl_end;
    final Calendar myCalendar = Calendar.getInstance();

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
        prodlist= findViewById(R.id.prod_list);
        chooseprod =  findViewById(R.id.choose_prod);
        iv = findViewById(R.id.iv);
        lv_category = findViewById(R.id.lvcat);
        s_start = findViewById(R.id.start_date);
        s_end = findViewById(R.id.end_date);

      //  listitems= getResources().getStringArray(R.array.)



        //date picker dialogue
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayofmonth);
                String Myformat = "dd MMMM YYYY";
                SimpleDateFormat sdf = new SimpleDateFormat(Myformat, Locale.US);
                s_start.setText(sdf.format(myCalendar.getTime()));


            }
        };

        //date picker dialogue
        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayofmonth);
                String Myformat = "dd MMMM YYYY";
                SimpleDateFormat sdf = new SimpleDateFormat(Myformat, Locale.US);
                s_end.setText(sdf.format(myCalendar.getTime()));


            }
        };

        //date picker

        s_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Add_sale.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

               // String Myformat = "dd MMMM YYYY";
               // SimpleDateFormat sdf = new SimpleDateFormat(Myformat, Locale.US);
               // s_start.setText(sdf.format(myCalendar.getTime()));


            }
        });



        s_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Add_sale.this,date2,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();



            }
        });



        List<String> names = new ArrayList<>();
        Cursor cursor = db.getData("SELECT DISTINCT pcategory from product_table");
        if(cursor != null){
            while(cursor.moveToNext()){
                names.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_pcat)));

            }
        }
        lv_category = new ListView(this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);
        lv_category.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(Add_sale.this);
        builder.setCancelable(true);
        builder.setView(lv_category);
        final  AlertDialog dialog = builder.create();
//tried many times had no result to display the list of items in array to choose them in a mult select drop down list
       // listitems = names.toArray();
       // names= Arrays.asList(String );
        //checkeditems= new boolean[day.length];
        //listitems= getResources().getStringArray();



        chooseprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                lv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        prodlist.setText(adapter.getItem(i));
                        dialog.dismiss();
                    }
                });
            }
        });



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
                String s_product= prodlist.getText().toString();
                String sale_sdate= s_start.getText().toString();
                String sale_edate= s_end.getText().toString();


                if (sale_title.length() == 0) {
                    sale_title.requestFocus();
                    sale_title.setError("please enter sale name");
                } else if (sale_desc.length() == 0) {
                    sale_desc.requestFocus();
                    sale_desc.setError("please enter sale desc ");
                }else if (s_start.length() == 0) {
                    s_start.requestFocus();
                    s_start.setError("please enter sale date ");
                }
                else if (s_end.length() == 0) {
                    s_end.requestFocus();
                    s_end.setError("please enter sale date ");
                }
                else {



                    byte[] newentryimg = imageViewToByte(iv);

                    add_sale(stitle, sdesc,s_product,sale_sdate,sale_edate, newentryimg);
                    Toast.makeText(Add_sale.this, "sale added succesfully ", Toast.LENGTH_LONG).show();

                    Intent r = new Intent(Add_sale.this, Home.class);
                    startActivity(r);
                    finish();

                }

            }
            private void add_sale(String stitle, String sdesc,String s_product,String sale_sdate, String sale_edate, byte[] newentryimg) {

                boolean insertsaledata = db.insert_sale_data(stitle, sdesc,s_product,sale_sdate,sale_edate, newentryimg);
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