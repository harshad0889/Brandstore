package com.example.brandstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

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
    cart_fragment cartfragment;
    ArrayList<String> cart_ids;


    SharedPreferences sp;
    String uid,username;
    Button rmv,place_order;
   public static TextView Cart_total;





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
        Cart_total = view.findViewById(R.id.tv_total);
        ArrayList<cart> cartlist;
        cartlist = new ArrayList<>();
        cart_ids = new ArrayList<>();
        final Context context;
        adapter = new cartlistAdapter(getContext(), R.layout.cart_item, cartlist);
        gv_cart.setAdapter(adapter);
        //Cart_total.setText(cartlistAdapter.ViewHolder.getPrice());
        place_order = view.findViewById(R.id.place_ord);
        //Cart_total.setText(cartlistAdapter.totalPrice);


       // Cart_total.setText();

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String total_amount = Cart_total.getText().toString();
                Intent in = new Intent(getContext(),choose_payment.class);
                in.putExtra("tot",total_amount);
                in.putStringArrayListExtra("cart_id's", cart_ids);
                startActivity(in);
                Toast.makeText(getContext(), "Product added succesfully ", Toast.LENGTH_LONG).show();

            }

        });



         Cursor cursor = db2.getData(String.format("SELECT * FROM cart_table JOIN product_table on  cart_table.pid = product_table.pid WHERE _id ='%s'",uid));
        cartlist.clear();
        while (cursor.moveToNext()) {
            int pid = cursor.getInt(1);
            String cart_id = cursor.getString(0);
            String p_name = cursor.getString(8);
            String price = cursor.getString(12);
            String p_desc = cursor.getString(10);
            String p_qty = cursor.getString(3);

            String uid = cursor.getString(2);
            byte[] image = cursor.getBlob(14);

            cartlist.add(new cart( pid,cart_id,  p_name, price, p_desc,p_qty,uid,  image));
            cart_ids.add(cart_id);
        }
        adapter.notifyDataSetChanged();


    }








}
//"+'"'+2+'"'+""