package com.example.brandstore;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

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


        Cursor cursor = db.getData("SELECT * FROM sale_table");
        list.clear();
        while (cursor.moveToNext()) {
            int sid = cursor.getInt(0);
            String s_title = cursor.getString(1);
            String s_desc = cursor.getString(2);
            String prod_name = cursor.getString(3);
            String sdate = cursor.getString(4);
            String edate = cursor.getString(5);
            byte[] image = cursor.getBlob(7);

            list.add(new sale( sid,  s_title,s_desc, prod_name,  sdate,  edate, image));
        }
        adapter.notifyDataSetChanged();
    }

}
