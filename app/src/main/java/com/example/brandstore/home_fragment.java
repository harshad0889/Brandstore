package com.example.brandstore;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class home_fragment  extends Fragment {
    productListAdapter adapter = null;
    DatabaseHelper db2;
    GridView rv;
    SearchView searchview;
    TextView search;
    ListView search_list;
    ImageView cartlogo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment1,container,false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db2 = new DatabaseHelper(getContext());
        rv = view.findViewById(R.id.gv_products_frag1);
        search = view.findViewById(R.id.search_tv);
        cartlogo = view.findViewById(R.id.cartlogo);
        final ArrayList<product> list;
        list = new ArrayList<>();
        final Context context;
        adapter = new productListAdapter(getContext(), R.layout.product_items, list);
        rv.setAdapter(adapter);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent s1 = new Intent(getContext(),search_activity.class);
                startActivity(s1);


            }
        });

        cartlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });




        Cursor cursor = db2.getData("SELECT * FROM product_table");
        list.clear();
        while (cursor.moveToNext()) {
            int pid = cursor.getInt(0);
            String p_name = cursor.getString(1);
            String p_category = cursor.getString(2);
            String p_desc = cursor.getString(3);
            String p_size = cursor.getString(4);
            String a_price = cursor.getString(5);
            //String o_price = cursor.getString(6);
           // String p_off = cursor.getString(8);
            String p_sale = cursor.getString(6);
            byte[] image = cursor.getBlob(7);

            list.add(new product( pid,  p_name, p_category, p_desc,p_size,a_price,  p_sale,   image));
        }
        adapter.notifyDataSetChanged();




        rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvid = view.findViewById(R.id.prod_id);
                TextView act_price = (TextView) view.findViewById(R.id.act_Price);
                TextView prod_desc = (TextView) view.findViewById(R.id.prod_desc);
                TextView prod_name = (TextView) view.findViewById(R.id.prod_name);
                TextView category = (TextView) view.findViewById(R.id.category);
                TextView sale = (TextView) view.findViewById(R.id.p_sale);
                TextView p_size = (TextView) view.findViewById(R.id.p_size);

                ImageView ivimage = view.findViewById(R.id.imgFood);



                String pid = tvid.getText().toString();
                String sa_price = act_price.getText().toString();
                String p_desc = prod_desc.getText().toString();
                String p_name = prod_name.getText().toString();
                String p_cat = category.getText().toString();
                String p_sale = sale.getText().toString();
                String size = p_size.getText().toString();


                Intent s = new Intent(getContext(),p_grid_selecteditem.class);
                s.putExtra("pid",pid);
                s.putExtra("p_price",sa_price);
                s.putExtra("p_desc",p_desc);
                s.putExtra("p_name",p_name);
                s.putExtra("p_cat",p_cat);
                s.putExtra("p_sale",p_sale);
                s.putExtra("size",size);


                startActivity(s);
            }
        });



    }
}
