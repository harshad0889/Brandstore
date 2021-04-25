package com.example.brandstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class gpay_sample extends AppCompatActivity {
    EditText amount;
    Button pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpay_sample);
        amount  = findViewById(R.id.amount);
        pay = findViewById( R.id.bt_pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentusinggooglepay();
            }
        });
        Intent intent = getIntent();

        String total_amt = intent.getStringExtra("tot");
        amount.setText(total_amt);
    }
   // String amt=amount.getText().toString();
    private void paymentusinggooglepay(){

        String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa","9744520889@okbizaxis")//upi id
                .appendQueryParameter("pn","pay for brand factory")//name
                .appendQueryParameter("mc","")
                .appendQueryParameter("tid","02125412")
                .appendQueryParameter("tr","25584584")
                .appendQueryParameter("tn","for testing")//enter note here
                .appendQueryParameter("am","1")//amount
                .appendQueryParameter("cu","INR")//currency type
                .appendQueryParameter("url","")
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
        try {
            startActivityForResult(intent,101);
        } catch(Exception e){
            Toast.makeText(this, "googel pay not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==101){
            if (requestCode==RESULT_OK){
                if (data!=null){
                    String value = data.getStringExtra("response");
                    ArrayList<String> list = new ArrayList<>();
                    list.add(value);
                    Toast.makeText(this, "payment success", Toast.LENGTH_SHORT).show();

                }
            }else{
                Toast.makeText(this, "payment failed", Toast.LENGTH_SHORT).show();

            }
        }
    }
    private void getStatus(String data){

        boolean ispaymentcancel=false;
        boolean ispaymentsuccess=false;
        boolean paymentfailed=false;
        String value[]=data.split("&");
        for (int i=0;i<value.length;i++){
             String checkString[]=value[i].split("=");
             if (checkString.length>=2){
                 if (checkString[0].toLowerCase().equals("status")){
                     if (checkString[1].equals("success")){
                         ispaymentsuccess = true;

                     }

                 }

             }else
             {
                 ispaymentcancel=true;
             }

        }
        if (ispaymentsuccess){
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }else if (ispaymentcancel){
            Toast.makeText(this, "payment cancel", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "payment failed", Toast.LENGTH_SHORT).show();
        }
    }
}