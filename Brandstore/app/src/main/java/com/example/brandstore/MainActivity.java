package com.example.brandstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    EditText username;
    EditText email;
    EditText password;
    EditText address;
    EditText phnno;
    EditText location;
    String s_name,s_gen,s_address, s_mob,s_location, s_email, s_pass;

    Button btnAddData,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setContentView(R.layout.activity_registration);

        username = (EditText) findViewById(R.id.username);

        phnno = (EditText) findViewById(R.id.phnno);
        location = (EditText) findViewById(R.id.loc);

        password = (EditText) findViewById(R.id.password);

        btnAddData = (Button) findViewById(R.id.bt_register);
        reset = (Button) findViewById(R.id.reset);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username.getText().clear();
                email.getText().clear();
                password.getText().clear();
                address.getText().clear();
                phnno.getText().clear();
                location.getText().clear();
            }
        });
    }
}