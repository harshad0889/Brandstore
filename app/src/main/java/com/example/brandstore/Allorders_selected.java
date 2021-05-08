package com.example.brandstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Allorders_selected extends AppCompatActivity {

    DatabaseHelper db;
    String uid,username;
    SharedPreferences sd;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allorders_selected);

        db = new DatabaseHelper(this);
        sd = getSharedPreferences("user_details",MODE_PRIVATE);
        uid = sd.getString("uid",null);
        username  = sd.getString("username",null);
        TextView order_id = (TextView) findViewById(R.id.order_id);
        TextView uid = (TextView) findViewById(R.id.uid);
        TextView delivery_amt = (TextView) findViewById(R.id.del_amt);
        final TextView status = (TextView) findViewById(R.id.status);
        TextView pay_mode = (TextView) findViewById(R.id.p_mode);
        TextView o_date = (TextView) findViewById(R.id.o_date);
        TextView username = (TextView) findViewById(R.id.u_name);
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView address = (TextView) findViewById(R.id.address);
        TextView pin = (TextView) findViewById(R.id.pin);
        TextView cart_id = (TextView) findViewById(R.id.cart_id);
        TextView pid = (TextView)findViewById(R.id.pid);
        TextView qty = (TextView)findViewById(R.id.qty);
        ImageView imageView = (ImageView) findViewById(R.id.img_product);
        TextView p_name = (TextView) findViewById(R.id.prod_name);
        TextView cart_psize = (TextView) findViewById(R.id.c_size);
        TextView p_cat = (TextView) findViewById(R.id.p_cat);
        TextView price = (TextView) findViewById(R.id.price);
        TextView total = (TextView) findViewById(R.id.tot);
         listview =  findViewById(R.id.lv_status);



        Intent intent = getIntent();

        String sorder_id = intent.getStringExtra("sorder_id");
        String suid= intent.getStringExtra("suid");
        String sdel_amt= intent.getStringExtra("sdel_amt");
        String s_status= intent.getStringExtra("s_status");
        String s_pmode= intent.getStringExtra("s_pmode");
        String s_odate= intent.getStringExtra("s_odate");
        String s_uname= intent.getStringExtra("s_uname");
        String s_phone = intent.getStringExtra("s_phone");
        String s_address = intent.getStringExtra("s_address");
        String s_pin= intent.getStringExtra("s_pin");
        String s_cartid= intent.getStringExtra("s_cartid");
        String s_pid= intent.getStringExtra("s_pid");
        String s_qty= intent.getStringExtra("s_qty");
        String s_pname= intent.getStringExtra("s_pname");
        String s_cartsize= intent.getStringExtra("s_cartsize");
        String s_pcat = intent.getStringExtra("s_pcat");
        String s_price = intent.getStringExtra("s_price");
        String s_total= intent.getStringExtra("s_total");

        Bitmap bitmap = intent.getParcelableExtra("bitmap");


        order_id.setText(sorder_id);
        uid.setText(suid);
        delivery_amt.setText(sdel_amt);
        status.setText(s_status);
        pay_mode.setText(s_pmode);
        o_date.setText(s_odate);
        username.setText(s_uname);
        phone.setText(s_phone);
        address.setText(s_address);
        pin.setText(s_pin);
        cart_id.setText(s_cartid);
        pid.setText(s_pid);
        qty.setText(s_qty);
        p_name.setText(s_pname);
        cart_psize.setText(s_cartsize);
        p_cat.setText(s_pcat);
        price.setText(s_price);
        total.setText(s_total);


        byte[] bytes = db.prodImage(s_pid);
        imageView.setImageBitmap(getImage(bytes));






        listview = new ListView(this);
        List<String> data = new ArrayList<>();
        data.add("ORDERED");
        data.add("PACKED");
        data.add("SHIPPED");
        data.add("DELIVERY EXPECTED");
        data.add("DELIVERED");
        data.add("RETURN REQUESTED");
        data.add("RETURN ");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        listview.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(Allorders_selected.this);
        builder.setCancelable(true);
        builder.setView(listview);
        final  AlertDialog dialog = builder.create();




        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        status.setText(adapter.getItem(i));
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), All_orders.class);
        startActivity(in);
        finish();
    }

}