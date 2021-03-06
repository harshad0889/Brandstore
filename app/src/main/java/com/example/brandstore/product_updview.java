package com.example.brandstore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class product_updview extends AppCompatActivity {


    GridView gridView;
    ArrayList<product> list;
    productListAdapter adapter = null;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_updview);

        db = new DatabaseHelper(this);




        db=new DatabaseHelper(this);
        gridView =  findViewById(R.id.gv_uprod);
        list = new ArrayList<>();
        adapter = new productListAdapter(this, R.layout.product_items, list);
        gridView.setAdapter(adapter);




        Cursor cursor = db.getData("SELECT * from product_table left join sale_table on product_table.subcat = sale_table.sale_title");
        list.clear();
        while (cursor.moveToNext()) {
            int pid = cursor.getInt(0);
            String p_name = cursor.getString(1);
            String p_category = cursor.getString(2);
            String p_subcat = cursor.getString(3);
            String p_desc = cursor.getString(4);
            String p_size = cursor.getString(5);
            String a_price = cursor.getString(6);
           // String o_price = cursor.getString(6);
            String p_off = cursor.getString(11);
            String p_sale = cursor.getString(7);
            byte[] image = cursor.getBlob(8);

            list.add(new product( pid,  p_name,p_category,p_subcat,p_desc,p_size,a_price,  p_sale,    image,p_off));
        }
        adapter.notifyDataSetChanged();




        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {




                TextView tv_pid = (TextView) view.findViewById(R.id.prod_id);
                TextView tv_pname = (TextView) view.findViewById(R.id.prod_name);
                TextView tv_cat = (TextView) view.findViewById(R.id.category);
                TextView tv_subcat = (TextView) view.findViewById(R.id.sub_cat);
                TextView tv_desc = (TextView) view.findViewById(R.id.prod_desc);
                TextView tv_size = (TextView) view.findViewById(R.id.p_size);
                TextView tv_actprice = (TextView) view.findViewById(R.id.act_Price);
                TextView tv_offprice = view.findViewById(R.id.off_Price);
                TextView tv_sale = (TextView) view.findViewById(R.id.p_sale);
                TextView tv_off = (TextView) view.findViewById(R.id.p_off);
                ImageView iv_image = view.findViewById(R.id.imgFood);



                String sp_id = tv_pid.getText().toString();
                String sp_name = tv_pname.getText().toString();
                String sp_cat = tv_cat.getText().toString();
                String sp_subcat = tv_subcat.getText().toString();
                String sp_desc = tv_desc.getText().toString();
                String sp_size = tv_size.getText().toString();
                String sp_actprice = tv_actprice.getText().toString();
                //String sp_offprice = tv_offprice.getText().toString();
                String sp_sale = tv_sale.getText().toString();
               // String sp_off = tv_off.getText().toString();

                Intent s = new Intent(product_updview.this,update_product.class);
                s.putExtra("pid",sp_id);
                s.putExtra("pname",sp_name);
                s.putExtra("pcat",sp_cat);
                s.putExtra("psubcat",sp_subcat);
                s.putExtra("pdesc",sp_desc);
                s.putExtra("psize",sp_size);
                s.putExtra("pactprice",sp_actprice);
                //s.putExtra("pofprice",sp_offprice);
                s.putExtra("psale",sp_sale);
                //s.putExtra("poff",sp_off);

                startActivity(s);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), product_view.class);
        startActivity(in);
        finish();
    }
}