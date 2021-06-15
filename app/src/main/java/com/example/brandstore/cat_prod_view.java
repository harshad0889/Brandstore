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

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class cat_prod_view extends AppCompatActivity {
    GridView gvcat_prodview;
    ArrayList<product> list;
    private NavigationView nv;

    productListAdapter adapter = null;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_prod_view);
        db=new DatabaseHelper(this);
        gvcat_prodview =  findViewById(R.id.gv_cat_product);
        nv = findViewById(R.id.nav);


        list = new ArrayList<>();
        adapter = new productListAdapter(this, R.layout.product_items, list);
        gvcat_prodview.setAdapter(adapter);

        Intent intent = getIntent();

       // String catid = intent.getStringExtra("catid");
        String spname= intent.getStringExtra("catname");

       // Bitmap bitmap = intent.getParcelableExtra("catimage");
        final String date2 = new SimpleDateFormat("dd MMMM YYYY", Locale.getDefault()).format(new Date());

        //code used before
        //SELECT* from product_table left join sale_table on product_table.pcategory = sale_table.pname WHERE pcategory = '%s' or product_table.pname like '%s%%'

        Cursor cursor = db.getData(String.format("SELECT * from product_table left join sale_table on product_table.subcat = sale_table.sale_title AND  '%s' BETWEEN start_date and  end_date WHERE pcategory = '%s' AND psale>0 or product_table.pname like '%%%s%%'",date2,spname,spname));

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

            list.add(new product( pid,  p_name,p_category,p_subcat,p_desc, p_size,a_price, p_sale,   image,p_off));
        }
        adapter.notifyDataSetChanged();


        gvcat_prodview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvid = view.findViewById(R.id.prod_id);
                TextView act_price = (TextView) view.findViewById(R.id.act_Price);
                TextView prod_desc = (TextView) view.findViewById(R.id.prod_desc);
                TextView prod_name = (TextView) view.findViewById(R.id.prod_name);
                TextView category = (TextView) view.findViewById(R.id.category);
                TextView tv_subcat = (TextView) view.findViewById(R.id.sub_cat);
                TextView sale = (TextView) view.findViewById(R.id.p_sale);
                TextView p_size = (TextView) view.findViewById(R.id.p_size);
                TextView p_off = (TextView) view.findViewById(R.id.off1);

                ImageView ivimage = view.findViewById(R.id.imgFood);



                String pid = tvid.getText().toString();
                String sa_price = act_price.getText().toString();
                String p_desc = prod_desc.getText().toString();
                String p_name = prod_name.getText().toString();
                String p_cat = category.getText().toString();
                String sp_subcat = tv_subcat.getText().toString();
                String p_sale = sale.getText().toString();
                String size = p_size.getText().toString();
                String off2 = p_off.getText().toString();


                Intent s = new Intent(cat_prod_view.this,p_grid_selecteditem.class);
                s.putExtra("pid",pid);
                s.putExtra("p_price",sa_price);
                s.putExtra("p_desc",p_desc);
                s.putExtra("p_name",p_name);
                s.putExtra("p_cat",p_cat);
                s.putExtra("psubcat",sp_subcat);
                s.putExtra("p_sale",p_sale);
                s.putExtra("size",size);
                s.putExtra("off",off2);


                startActivity(s);
            }
        });


    }
}