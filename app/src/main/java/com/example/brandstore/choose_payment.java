package com.example.brandstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class choose_payment extends AppCompatActivity {
        RadioGroup payment;
        RadioButton upi,cod,selected_pay;
        Button pay;
        TextView total_amount,cp_tot,sub_total;
        SharedPreferences sp;
        String uid,username,delivery_charge,order_status,p_mode,quantity;
        DatabaseHelper db;


@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_payment);
        sp = choose_payment.this.getSharedPreferences("user_details", Context.MODE_PRIVATE);
        uid = sp.getString("uid",null);
         username  = sp.getString("username",null);
         delivery_charge = "free";
         quantity = "5";
         order_status="packed";

    db = new DatabaseHelper(this);


        payment=(RadioGroup) findViewById(R.id.radiogroup_pay);
        pay = (Button) findViewById(R.id.bt_pay);
        total_amount = findViewById(R.id.total_amount);
        upi = findViewById(R.id.rad_upi);
       cod  = findViewById(R.id.rad_COD);
        sub_total  = findViewById(R.id.sub_total);



    Intent intent = getIntent();

    final String total_amt = intent.getStringExtra("tot");
    pay.setText("PAY "+total_amt);
    total_amount.setText(total_amt);



        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_pay = (RadioButton) findViewById(payment.getCheckedRadioButtonId());
                p_mode = selected_pay.getText().toString();



                if (upi.isChecked()){

                    String tot = total_amount.getText().toString();
                    Intent in = new Intent(choose_payment.this,gpay_sample.class);
                    in.putExtra("tot",tot);
                    startActivity(in);
                    Toast.makeText(choose_payment.this, ""+p_mode, Toast.LENGTH_SHORT).show();
                }else if(cod.isChecked()){
                    //insert order table values
                    addorder(uid,total_amt,delivery_charge,quantity,order_status,p_mode);

                    Intent r = new Intent(choose_payment.this, Home2.class);
                    startActivity(r);
                    finish();

                    Toast.makeText(choose_payment.this, ""+p_mode, Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(choose_payment.this, "error", Toast.LENGTH_SHORT).show();
                }

            }

            private void addorder (String uid,String total_amt,String delivery_charge,String quantity,String order_status,String p_mode){

                boolean insertcardata = db.insert_order_data(uid,total_amt,delivery_charge,quantity,order_status,p_mode);
            }
        });

  }


 }



        // selectedp_method = (RadioButton) findViewById(pay.getCheckedRadioButtonId());