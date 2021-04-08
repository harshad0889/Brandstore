package com.example.brandstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {


    EditText username;

    EditText password;

    EditText phnno;
    EditText c_password;
    String s_name,s_phn, s_cpass, s_pass;

    Button btnreg,reset;
    DatabaseHelper MyDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        MyDB = new DatabaseHelper(this);


        username = (EditText) findViewById(R.id.username);
        phnno = (EditText) findViewById(R.id.phnno);
        password = (EditText) findViewById(R.id.password);
        c_password = (EditText) findViewById(R.id.cpassword);
        btnreg = (Button) findViewById(R.id.bt_register);



        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s_name = username.getText().toString();
                s_phn = phnno.getText().toString();
                s_pass = password.getText().toString();
                s_cpass = c_password.getText().toString();

                if (username.length() == 0) {
                    username.requestFocus();
                    username.setError("please enter your name");
                }
                else if (phnno.length() == 0) {
                    phnno.requestFocus();
                    phnno.setError("please enter your phone");
                }
                else if (password.length() == 0) {
                    password.requestFocus();
                    password.setError("please enter your password");
                }
                else if (phnno.length() != 10) {
                    phnno.requestFocus();
                    phnno.setError("please enter valid mobile number");
                }
                else if (password.length() == 0) {
                    password.requestFocus();
                    password.setError("please enter your password");
                }
                else if (password != c_password) {
                    password.requestFocus();
                    password.setError(" password miss match");
                }
                else {

                    MyDB.insertdata(s_name, s_phn, s_pass);

                    Toast.makeText(Registration.this, "User created successfully! Please Login ", Toast.LENGTH_LONG).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, Toast.LENGTH_LONG);


                    Intent intent = new Intent(getApplicationContext(), verify_phno.class);
                    intent.putExtra("phoneno", s_phn);
                    startActivity(intent);
                }

            }
        });





    }
    }