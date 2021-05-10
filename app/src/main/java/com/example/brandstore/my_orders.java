package com.example.brandstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class my_orders extends AppCompatActivity {
    GridView gridView;

    ArrayList<order> myorders;
    MyorderlistAdapter adapter = null;
    SharedPreferences sp;
    DatabaseHelper db;
    String uid,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        db=new DatabaseHelper(this);
        gridView =  findViewById(R.id.gv_myorders);
        sp = getSharedPreferences("user_details", Context.MODE_PRIVATE);
        uid = sp.getString("uid",null);
        username  = sp.getString("username",null);


        myorders = new ArrayList<>();
        adapter = new MyorderlistAdapter(this, R.layout.myorder_item, myorders);
        gridView.setAdapter(adapter);

        Cursor cursor = db.getData(String.format("SELECT * from (SELECT * from order_table join registrationtable where order_table._id = registrationtable._id and  registrationtable._id =%s) as x JOIN ( SELECT * from cart_table join product_table where cart_table.pid = product_table.pid and _id=%s) as y WHERE  x.order_id = y.order_id",uid,uid));
        myorders.clear();
        while (cursor.moveToNext()) {
            int order_id = cursor.getInt(0);
            int uid = cursor.getInt(1);
            String delivery_amt = cursor.getString(3);
            String status = cursor.getString(19);
            String pay_mode = cursor.getString(6);
            String o_date = cursor.getString(7);
            String username = cursor.getString(9);
            String phone = cursor.getString(10);
             String address = cursor.getString(12);
            String pin = cursor.getString(13);
            int cart_id = cursor.getInt(14);
            int pid = cursor.getInt(15);
            int qty = cursor.getInt(17);
            String cart_psize = cursor.getString(18);
            String p_name = cursor.getString(22);
            String p_cat = cursor.getString(23);
            int price = cursor.getInt(26);
            int total = cursor.getInt(2);

            byte[] image = cursor.getBlob(28);

            myorders.add(new order(  order_id,  uid,  delivery_amt,  status,  pay_mode,  o_date,  username,  phone,  address,  pin,  cart_id,  pid,  qty, cart_psize,  p_name,  p_cat,  price,  image,  total));
        }
        adapter.notifyDataSetChanged();



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView order_id = (TextView) view.findViewById(R.id.order_id);
                TextView uid = (TextView) view.findViewById(R.id.uid);
                TextView delivery_amt = (TextView) view.findViewById(R.id.del_amt);
                TextView status = (TextView) view.findViewById(R.id.status);
                TextView pay_mode = (TextView) view.findViewById(R.id.p_mode);
                TextView o_date = (TextView) view.findViewById(R.id.o_date);
                TextView username = (TextView) view.findViewById(R.id.u_name);
                TextView phone = (TextView) view.findViewById(R.id.phone);
                TextView address = (TextView) view.findViewById(R.id.address);
                TextView pin = (TextView) view.findViewById(R.id.pin);
                TextView cart_id = (TextView) view.findViewById(R.id.cart_id);
                TextView pid = (TextView) view.findViewById(R.id.pid);
                TextView qty = (TextView) view.findViewById(R.id.qty);
                ImageView imageView = (ImageView) view.findViewById(R.id.img_product);
                TextView p_name = (TextView) view.findViewById(R.id.prod_name);
                TextView cart_psize = (TextView) view.findViewById(R.id.c_size);
                TextView p_cat = (TextView) view.findViewById(R.id.p_cat);
                TextView price = (TextView) view.findViewById(R.id.price);
                TextView total = (TextView) view.findViewById(R.id.tot);

               // ImageView ivimage = view.findViewById(R.id.imgFood);



                String sorder_id = order_id.getText().toString();
                String suid = uid.getText().toString();
                String sdel_amt = delivery_amt.getText().toString();
                String s_status = status.getText().toString();
                String s_pmode = pay_mode.getText().toString();
                String s_odate = o_date.getText().toString();
                String s_uname = username.getText().toString();
                String s_phone = phone.getText().toString();
                String s_address = address.getText().toString();
                String s_pin = pin.getText().toString();
                String s_cartid = cart_id.getText().toString();
                String s_pid = pid.getText().toString();
                String s_qty = qty.getText().toString();
                String s_pname = p_name.getText().toString();
                String s_cartsize = cart_psize.getText().toString();
                String s_pcat = p_cat.getText().toString();
                String s_price = price.getText().toString();
                String s_total = total.getText().toString();



                Intent s = new Intent(my_orders.this,myorders_selected.class);
                s.putExtra("sorder_id",sorder_id);
                s.putExtra("suid",suid);
                s.putExtra("sdel_amt",sdel_amt);
                s.putExtra("s_status",s_status);
                s.putExtra("s_pmode",s_pmode);
                s.putExtra("s_odate",s_odate);
                s.putExtra("s_uname",s_uname);
                s.putExtra("s_phone",s_phone);
                s.putExtra("s_address",s_address);
                s.putExtra("s_pin",s_pin);
                s.putExtra("s_cartid",s_cartid);
                s.putExtra("s_pid",s_pid);
                s.putExtra("s_qty",s_qty);
                s.putExtra("s_pname",s_pname);
                s.putExtra("s_cartsize",s_cartsize);
                s.putExtra("s_pcat",s_pcat);
                s.putExtra("s_price",s_price);
                s.putExtra("s_total",s_total);



                startActivity(s);
            }
        });
    }
}