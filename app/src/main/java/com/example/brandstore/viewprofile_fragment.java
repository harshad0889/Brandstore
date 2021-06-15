package com.example.brandstore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
                if(sp_manager.getUser2(getContext()).length() == 0)
                {
                    Toast.makeText(getContext(), "Please login ", Toast.LENGTH_LONG).show();
                    Intent homeIntent = new Intent(getContext(),verify_phno.class);
                    startActivity(homeIntent);
                }else{

                    Intent regIntent = new Intent(getContext(),whishlist.class);
                    startActivity(regIntent);

                }



            }
        });
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sp_manager.getUser2(getContext()).length() == 0)
                {
                    Toast.makeText(getContext(), "Please login ", Toast.LENGTH_LONG).show();
                    Intent homeIntent = new Intent(getContext(),verify_phno.class);
                    startActivity(homeIntent);
                }else{

                    Intent in = new Intent(getContext(),upadate_profile.class);
                    startActivity(in);

                }

            }
        });

        tv_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("LOGOUT");
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        sp_manager.getInstance(getContext()).clearuser(getContext());
                        Toast.makeText(getContext(), " Logging out! ", Toast.LENGTH_LONG).show();
                        Intent m = new Intent(getContext(), verify_phno.class);
                        startActivity(m);




                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "  ", Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();










               // finish();
            }
        });


        my_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sp_manager.getUser2(getContext()).length() == 0)
                {
                    Toast.makeText(getContext(), "Please login ", Toast.LENGTH_LONG).show();
                    Intent homeIntent = new Intent(getContext(),verify_phno.class);
                    startActivity(homeIntent);
                }else{

                    Intent m = new Intent(getContext(), my_orders.class);
                    startActivity(m);
                    // finish();

                }

            }
        });

    }








}
//"+'"'+2+'"'+""