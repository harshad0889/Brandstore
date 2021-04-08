package com.example.brandstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class verify_otp extends AppCompatActivity {

    EditText enterotp;
    Button verifyotp;
    FirebaseAuth auth;
    private  String otp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        enterotp = findViewById(R.id.otpenter);
        verifyotp = findViewById(R.id.bt_verifyotp);
        auth = FirebaseAuth.getInstance();

        otp = getIntent().getStringExtra("auth");
        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verificationcode = enterotp.getText().toString();
                if (!verificationcode.isEmpty()){
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otp, verificationcode);
                    signIn(credential);
                }else{
                    Toast.makeText(verify_otp.this, "please enter ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void signIn(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    sendTomain();
                }else{
                    Toast.makeText(verify_otp.this, "failed ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = auth.getCurrentUser();
        if (currentuser!=null){
            sendTomain();
        }
    }

    private void sendTomain(){
        startActivity(new Intent(verify_otp.this, Logout_activity.class));
        finish();
    }
}