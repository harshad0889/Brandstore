package com.example.brandstore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class generate_bill extends AppCompatActivity {


    TextView total,del_a,gst,sub_total,total3;
    DatabaseHelper db;
    String ps_cat,stock_qty;
    private ListView listView;

    private SimpleCursorAdapter adapter7;

    final String[] from = new String[]{db.COL_cartid,db.COL_pname,db.COL_paprice,db.COL_qty};

    final int[] to = new int[]{R.id.cart_id, R.id.pname,R.id.bill_amount,R.id.qty};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_bill);


        db=new DatabaseHelper(this);
        listView= findViewById(R.id.lv_bill);
        total= findViewById(R.id.tot_amount2);
        total3= findViewById(R.id.tot_amount3);
        del_a= findViewById(R.id.del_amount2);
        gst= findViewById(R.id.gst_amount2);
        sub_total= findViewById(R.id.sub_total2);


        Intent intent = getIntent();

        final String ord_id = intent.getStringExtra("ord_id");
        final String tot = intent.getStringExtra("tot");
        final String del_c = intent.getStringExtra("del_c");
        String gst_value = "5";



        del_a.setText(del_c);
        total.setText(tot);
        gst.setText("5%");


        String per = "100";

        Double s_total = 0.0;

        s_total = Double.parseDouble(tot) + (Double.parseDouble(tot) * Double.parseDouble(gst_value)/Double.parseDouble(per));
        //(Double.parseDouble(total_amt) * Double.parseDouble(per));
        //Log.e(s_total + "", "=");

        sub_total.setText(String.valueOf(s_total));
        //pay.setText("PAY "+String.valueOf(s_total));



        Cursor cursor = db.getData(String.format("SELECT * from cart_table left join product_table where order_id = %s and cart_table.pid= product_table.pid",ord_id));

        adapter7 = new SimpleCursorAdapter(this,R.layout.bill_items, cursor, from, to,0);
        adapter7.notifyDataSetChanged();

        listView.setAdapter(adapter7);



        Cursor cursor2 = db.getData(String.format("SELECT sum(qty*act_price) from cart_table left join product_table where order_id = %s and cart_table.pid= product_table.pid",ord_id));
        while (cursor2.moveToNext()) {

           final String total_amt = cursor2.getString(0);
            total3.setText(total_amt);

        }


        //Cursor cursor7 = db.bill_list(ord_id);





    }
}