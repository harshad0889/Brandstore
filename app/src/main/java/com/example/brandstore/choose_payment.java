package com.example.brandstore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class choose_payment extends AppCompatActivity {
        RadioGroup payment;
        RadioButton upi,cod,selected_pay;
        Button pay;
        TextView total_amount,cp_tot,sub_total;
        SharedPreferences sp;
        String uid,username,delivery_charge,order_status,p_mode,quantity;
        DatabaseHelper db;
        Cursor cursor;
        int order_id;
        //String s_total = null;
        ArrayList<String> cartList;


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

         cartList = new ArrayList<>();
         cartList = getIntent().getStringArrayListExtra("cart_id's");

    db = new DatabaseHelper(this);


        payment=(RadioGroup) findViewById(R.id.radiogroup_pay);
        pay = (Button) findViewById(R.id.bt_pay);
        total_amount = findViewById(R.id.total_amount);
        upi = findViewById(R.id.rad_upi);
       cod  = findViewById(R.id.rad_COD);
        sub_total  = findViewById(R.id.sub_total);


    Intent intent = getIntent();

    final String total_amt = intent.getStringExtra("tot");

    total_amount.setText(total_amt);
    String per = "100";
    String gst = "5";
    Double s_total = 0.0;

     s_total = Double.parseDouble(total_amt) + (Double.parseDouble(total_amt) * Double.parseDouble(gst)/Double.parseDouble(per));
             //(Double.parseDouble(total_amt) * Double.parseDouble(per));
    Log.e(s_total + "", "=");

    sub_total.setText(String.valueOf(s_total));
    String subto = sub_total.getText().toString();
    pay.setText("PAY "+String.valueOf(s_total));



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




                    AlertDialog.Builder builder = new AlertDialog.Builder(choose_payment.this);
                    builder.setTitle("Order placed");
                    builder.setMessage("order placed successfully!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Toast.makeText(choose_payment.this, " order placed successfully ", Toast.LENGTH_LONG).show();
                            Intent r = new Intent(choose_payment.this, Home2.class);
                            startActivity(r);
                            finish();





                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();




                }else
                {
                    Toast.makeText(choose_payment.this, "error", Toast.LENGTH_SHORT).show();
                }

            }

            private void addorder (String uid,String total_amt,String delivery_charge,String quantity,String order_status,String p_mode){

                long insertcardata = db.insert_order_data(uid,total_amt,delivery_charge,quantity,order_status,p_mode);
                Log.e( "orderId: ", String.valueOf(insertcardata));
                for (int i=0;i<cartList.size();i++)
                {
                    String cart_id = cartList.get(i);
                    Log.e( "cart_id: ", cart_id );
                     //db.getData2(String.format("UPDATE cart_table SET order_id= '%d' WHERE cart_id = %s",insertcardata,cart_id));
                     int updatecart = db.update_cart(String.valueOf(insertcardata),cart_id);

                }
            }
        });

  }


 }



        // selectedp_method = (RadioButton) findViewById(pay.getCheckedRadioButtonId());