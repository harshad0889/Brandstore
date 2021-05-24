package com.example.brandstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class viewprofile_fragment extends Fragment {
    cartlistAdapter adapter = null;
    DatabaseHelper db2;


    SharedPreferences sp;
    String uid,username;

    TextView edit_profile,my_orders,tv_username,tv_signout,whishlist;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewprofile,container,false);
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
        edit_profile = view.findViewById(R.id.tv_edit_profile);
        my_orders = view.findViewById(R.id.tv_my_orders);
        tv_signout = view.findViewById(R.id.tv_signout);
        whishlist = view.findViewById(R.id.tv_whishlist);
        tv_username = view.findViewById(R.id.tv_username);

        tv_username.setText(username);

        whishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent regIntent = new Intent(getContext(),whishlist.class);
                startActivity(regIntent);

            }
        });
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(),upadate_profile.class);
                startActivity(in);
            }
        });

        tv_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp_manager.getInstance(getContext()).clearuser(getContext());

               // finish();
            }
        });


        my_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(getContext(), my_orders.class);
                startActivity(m);
                // finish();
            }
        });

    }








}
//"+'"'+2+'"'+""