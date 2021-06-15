package com.example.brandstore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class return_reason extends AppCompatActivity {

    RadioGroup reason;
    RadioButton accident,size,other,selected_pay;
    Button return_ord;

    SharedPreferences sp;
    String uid,username,delivery_charge,order_status,p_mode,quantity,ustatus,scart_id,s_order_id,s_pid_ret;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_reason);

        sp = return_reason.this.getSharedPreferences("user_details", Context.MODE_PRIVATE);
        uid = sp.getString("uid",null);
        username  = sp.getString("username",null);


        db = new DatabaseHelper(this);

        final Intent intent = getIntent();

          scart_id = intent.getStringExtra("scart_id");
         s_order_id= intent.getStringExtra("s_order_id");
         s_pid_ret= intent.getStringExtra("s_pid_ret");
        String e_date= intent.getStringExtra("e_date");


        reason=(RadioGroup) findViewById(R.id.radiogroup_reason);
        return_ord = (Button) findViewById(R.id.bt_return);

        //upi = findViewById(R.id.rad_upi);
        //cod  = findViewById(R.id.rad_COD);
        return_ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_pay = (RadioButton) findViewById(reason.getCheckedRadioButtonId());
                p_mode = selected_pay.getText().toString();
                ustatus = "RETURN";

                alert2(view);


            }
        });



    }
    public void alert2(View view2){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Return Order..");
        builder2.setMessage("Do you want to return order?");
        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Intent r = new Intent(myorders_selected.this, Home.class);
                // startActivity(r);
                // finish();

                int update = db.update_status(scart_id,s_order_id,ustatus);
                //insert status table for list

                db.insert_status(scart_id, ustatus);
                Toast.makeText(return_reason.this, " order returned! ", Toast.LENGTH_LONG).show();

                db.in_ret_reason(s_order_id,uid,s_pid_ret,p_mode);
                Toast.makeText(return_reason.this, ""+p_mode, Toast.LENGTH_SHORT).show();



            }
        });
        builder2.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(return_reason.this, " not reutrned! ", Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();

            }
        });
        AlertDialog dialog = builder2.create();
        dialog.show();

    }
}