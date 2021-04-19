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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class cart_fragment extends Fragment {
    cartlistAdapter adapter = null;
    DatabaseHelper db2;
    GridView gv_cart;
    ArrayList<cart> cartlist;
    SharedPreferences sp;
    String uid,username;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmen_cart1,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db2 = new DatabaseHelper(getContext());
        sp = this.getActivity().getSharedPreferences("user_details",Context.MODE_PRIVATE);
        uid = sp.getString("uid",null);
        username  = sp.getString("username",null);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        db2 = new DatabaseHelper(getContext());
        gv_cart = view.findViewById(R.id.gv_cart_frag1);
        ArrayList<cart> cartlist;
        cartlist = new ArrayList<>();
        final Context context;
        adapter = new cartlistAdapter(getContext(), R.layout.cart_item, cartlist);
        gv_cart.setAdapter(adapter);


         Cursor cursor = db2.getData(String.format("SELECT * FROM cart_table JOIN product_table on  cart_table.pid = product_table.pid WHERE _id ='%s'",uid));
        cartlist.clear();
        while (cursor.moveToNext()) {
            int pid = cursor.getInt(1);
            String p_name = cursor.getString(6);
            String price = cursor.getString(10);
            String p_desc = cursor.getString(8);
            String p_qty = cursor.getString(11);

            String uid = cursor.getString(2);
            byte[] image = cursor.getBlob(12);

            cartlist.add(new cart( pid,  p_name, price, p_desc,p_qty,uid,  image));
        }
        adapter.notifyDataSetChanged();
    }
}
//"+'"'+2+'"'+""