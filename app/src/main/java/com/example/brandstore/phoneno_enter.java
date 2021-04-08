package com.example.brandstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class phoneno_enter extends AppCompatActivity {

    Button sendotp;
    EditText phone_enter;
    private FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneno_enter);


        sendotp = findViewById(R.id.bt_sendotp);
        phone_enter = findViewById(R.id.phone_no);
        auth = FirebaseAuth.getInstance();
        pref = getSharedPreferences("user_details",MODE_PRIVATE);

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = "91";
                String phone = phone_enter.getText().toString();
                String phonenumber = "+" + code + "" + phone;
                if (!phone.isEmpty()){
                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth).setPhoneNumber(phonenumber).setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(phoneno_enter.this)
                            .setCallbacks(mCallBacks)
                            .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }
                else
                {
                    phone_enter.setError("please enter your number");

                }
            }
        });
        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signIn(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                phone_enter.setText(e.getMessage());
                phone_enter.setTextColor(Color.RED);
                phone_enter.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCodeSent(@NonNull final String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                phone_enter.setText("otp has been send");
                phone_enter.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent otpintent = new Intent(phoneno_enter.this, verify_otp.class);
                        otpintent.putExtra("auth",s);
                        startActivity(otpintent);
                    }
                },10000);


            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user !=null){
            sendTomain();

        }
    }

    private void sendTomain() {
        Intent intent = new Intent(phoneno_enter.this, Logout_activity.class);
        startActivity(intent);
        finish();

    }
    private void signIn(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    sendTomain();
                }else{
                    phone_enter.setText(task.getException().getMessage());
                    phone_enter.setTextColor(Color.RED);
                    phone_enter.setVisibility(View.VISIBLE);

                }
            }
        });
    }


}