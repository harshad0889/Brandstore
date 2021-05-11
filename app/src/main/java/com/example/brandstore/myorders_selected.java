package com.example.brandstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class myorders_selected extends AppCompatActivity {

    DatabaseHelper db;
   String uid,username;
    SharedPreferences sd;

    private ListView lv_status;
    private SimpleCursorAdapter adapter2;

    final String[] from = new String[]{db.s_cartid,db.s_status,db.s_date};

    final int[] to = new int[]{R.id.cartid, R.id.status, R.id.s_date};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders_selected);

        db = new DatabaseHelper(this);
        sd = getSharedPreferences("user_details",MODE_PRIVATE);
        uid = sd.getString("uid",null);
        username  = sd.getString("username",null);




        TextView order_id = (TextView) findViewById(R.id.order_id);
        TextView uid = (TextView) findViewById(R.id.uid);
        TextView delivery_amt = (TextView) findViewById(R.id.del_amt);
        TextView status = (TextView) findViewById(R.id.status);
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

        //listing of tracking details


        String status_cart_id = cart_id.getText().toString();
        Cursor cursor4 = db.status_list(status_cart_id);
        //Log.e( "cursor",sp_id);

        lv_status = findViewById(R.id.lv_status);

        adapter2 = new SimpleCursorAdapter(this,R.layout.status_items, cursor4, from, to,0);
        adapter2.notifyDataSetChanged();

        lv_status.setAdapter(adapter2);
    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), my_orders.class);
        startActivity(in);
        finish();
    }
}