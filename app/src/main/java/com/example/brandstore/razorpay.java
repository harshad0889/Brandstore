package com.example.brandstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class razorpay extends AppCompatActivity implements PaymentResultListener {
    EditText amt;
    ImageView iv_pay;
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razorpay);

        amt= findViewById(R.id.amount);
        iv_pay= findViewById(R.id.img_pay);
        pay=findViewById(R.id.bt_pay2);
        String Samt= "1";
        final int amount = Math.round(Float.parseFloat(Samt) * 100);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout checkout  = new Checkout();
                checkout.setKeyID("rzp_test_yAn2HwPrVyg1je");
                checkout.setImage(R.drawable.razor);
                //json object
                JSONObject object = new JSONObject();
                try {
                    object.put("name","harshad");
                    object.put("desc","test");
                    object.put("theme.color","#0093DD");
                    object.put("currency","INR");
                    object.put("amount",amount);
                    object.put("prefill.contact","9744520889");
                    object.put("prefill.email","harshadofs@gmail.com");
                    checkout.open(razorpay.this,object);

                }catch (JSONException e ){
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("payment ID");
        builder.setMessage(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();

    }
}