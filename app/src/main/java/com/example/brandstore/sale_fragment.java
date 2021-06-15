package com.example.brandstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class sale_fragment extends Fragment {


    GridView gridview_sale;
    ArrayList<sale> list;
    salelistAdapter adapter = null;
    DatabaseHelper db;
    SharedPreferences sp;
    String uid,username;







    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmen_sale,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(getContext());
        sp = this.getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        uid = sp.getString("uid",null);
        username  = sp.getString("username",null);
        final String date2 = new SimpleDateFormat("dd MMMM YYYY", Locale.getDefault()).format(new Date());



        /*rmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cursor int = db2.remove_item(uid);
                removeitem(uid);
            }
            private void removeitem(String uid){
                int remove_item = db2.remove_item(uid);

            }
        });*/
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        db = new DatabaseHelper(getContext());
        gridview_sale = view.findViewById(R.id.gv_sale);
        ArrayList<sale> list;
        list = new ArrayList<>();
        final Context context;
        adapter = new salelistAdapter(getContext(), R.layout.sale_items, list);
        gridview_sale.setAdapter(adapter);
        final String date2 = new SimpleDateFormat("dd MMMM YYYY", Locale.getDefault()).format(new Date());


        Cursor cursor = db.getData(String.format("SELECT * FROM sale_table "));
        list.clear();
        while (cursor.moveToNext()) {
            int sid = cursor.getInt(0);
            String s_title = cursor.getString(1);
            String s_desc = cursor.getString(2);
           // String prod_name = cursor.getString(3);
            String sdate = cursor.getString(3);
            String edate = cursor.getString(4);
            byte[] image = cursor.getBlob(6);

            list.add(new sale( sid,  s_title,s_desc,  sdate,  edate, image));
        }
        adapter.notifyDataSetChanged();

        gridview_sale.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TextView pname = view.findViewById(R.id.s_pname);
                TextView sdate = view.findViewById(R.id.sdate);
                TextView edate = view.findViewById(R.id.edate);
                TextView s_title = view.findViewById(R.id.s_title);

               // String p_name = pname.getText().toString();
                String s_date = sdate.getText().toString();
                String e_date = edate.getText().toString();
                String stitle = s_title.getText().toString();

               Cursor cursor = db.getData(String.format("SELECT * FROM sale_table where '%s' BETWEEN '%s' and  '%s' ",date2,s_date,e_date));
                if (cursor.getCount() == 0){
                    Toast.makeText(getContext(), "  sale start on "+s_date, Toast.LENGTH_LONG).show();
                }else{

                    Intent s = new Intent(getContext(),sale_product_view.class);
                    s.putExtra("spname",stitle);



                    startActivity(s);

                }


            }
        });
    }

}
