package com.example.brandstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verify_phno extends AppCompatActivity {


    EditText usernamelog,passlog;
    Button login,signup,otplogin;
    ProgressBar progressbar;
    DatabaseHelper MyDB;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phno);


        usernamelog = (EditText) findViewById(R.id.usernamelog);
        passlog = (EditText) findViewById(R.id.passwordlog);
        login =  findViewById(R.id.login);
        signup =  findViewById(R.id.sign_up);
        MyDB = new DatabaseHelper(this);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        otplogin =  findViewById(R.id.verify_phone);


        otplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(verify_phno.this, phoneno_enter.class);
                startActivity(intent);
                finish();

            }
        });




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToDas = new Intent(verify_phno.this, Registration.class);
                startActivity(goToDas);
                finish();

            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s_username = usernamelog.getText().toString();
                String s_password = passlog.getText().toString();
                //Authenticate User
                UserModel currentUser = MyDB.Authenticate(new UserModel(s_username, s_password));


                //Check Authentication is successful or not
                if (currentUser != null) {


                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("uid", currentUser.user_id);
                    editor.putString("username",currentUser.username);
                    editor.commit();
                    Intent goToDash = new Intent(verify_phno.this, Home.class);
                    startActivity(goToDash);
                    finish();
                    Toast.makeText(verify_phno.this, " Login Successful!", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(verify_phno.this, "Invalid mobile number or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });





















       // progressbar =  findViewById(R.id.progress_bar);

        //final String phoneNo  = getIntent().getStringExtra("phoneno");

       // sendverificationcodeuser(phoneNo);

      /*  verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otp.getText().toString().trim().isEmpty()){
                    Toast.makeText(verify_phno.this, "enter mobile",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressbar.setVisibility(View.VISIBLE);
                verify.setVisibility(View.VISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + phoneNo,60,
                        TimeUnit.SECONDS,
                        verify_phno.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                progressbar.setVisibility(View.GONE);
                                verify.setVisibility(View.VISIBLE);
                            }


                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                progressbar.setVisibility(View.GONE);
                                verify.setVisibility(View.VISIBLE);
                                Toast.makeText(verify_phno.this,  e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(String verificationid, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationid, forceResendingToken);
                                progressbar.setVisibility(View.GONE);
                                verify.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(getApplicationContext(),verify_phno.class);
                                intent.putExtra("phoneno",otp.getText().toString());
                                intent.putExtra("verificationid",verificationid);
                                startActivity(intent);

                            }
                        }
                );


            }
        });*/


    }

   /* private void sendverificationcodeuser(String phoneNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }*/
}