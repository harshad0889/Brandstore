package com.example.brandstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class upadate_profile extends AppCompatActivity {
    EditText username;

    EditText password;

    EditText phnno;
    EditText c_password,address,pin;
    String s_name,s_phn, s_cpass, s_pass,s_address,s_pin;

    Button btupdate,reset;
    DatabaseHelper MyDB;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upadate_profile);
        MyDB = new DatabaseHelper(this);
        username = (EditText) findViewById(R.id.username);
        phnno = (EditText) findViewById(R.id.phnno);
        password = (EditText) findViewById(R.id.password);
        c_password = (EditText) findViewById(R.id.cpassword);
        address = (EditText) findViewById(R.id.address);
        pin = (EditText) findViewById(R.id.pin);
        btupdate = (Button) findViewById(R.id.bt_update);
        sp = getSharedPreferences("user_details", Context.MODE_PRIVATE);
        final String uid = sp.getString("uid",null);
        //ArrayList<s> list = new ArrayList<>();



        Cursor cursor = MyDB.getData(String.format("SELECT * FROM registrationtable WHERE _id ='%s'",uid));

        while (cursor.moveToNext()) {


            String u_username = cursor.getString(1);
            String u_address = cursor.getString(4);
            String u_phone = cursor.getString(2);
            String u_pin = cursor.getString(5);

            username.setText(u_username);
            phnno.setText(u_phone);
            address.setText(u_address);
            pin.setText(u_pin);



        }



        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s_name = username.getText().toString();
                s_phn = phnno.getText().toString();


                s_address = address.getText().toString();
                s_pin = pin.getText().toString();

                if (username.length() == 0) {
                    username.requestFocus();
                    username.setError("please enter your name");
                }
                else if (phnno.length() == 0) {
                    phnno.requestFocus();
                    phnno.setError("please enter your phone");
                }

                else if (phnno.length() != 10) {
                    phnno.requestFocus();
                    phnno.setError("please enter valid mobile number");
                }


                else if (address.length() == 0) {
                    address.requestFocus();
                    address.setError("please enter your address");
                } else if (pin.length() == 0) {
                    pin.requestFocus();
                    pin.setError("please enter your pincode");

                }
                else {

                    MyDB.update_user_data(s_name, s_phn,s_address,s_pin,uid);

                    Toast.makeText(upadate_profile.this, "User updated successfully! ", Toast.LENGTH_LONG).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, Toast.LENGTH_LONG);


                    Intent intent = new Intent(upadate_profile.this, Home2.class);

                    startActivity(intent);
                }

            }
        });

    }
}