package com.example.brandstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_review extends AppCompatActivity {

    RatingBar ratingBar;
    EditText comment;
    TextView ratecount,showrating,prod_id,user;
    Button submit;
    float ratevalue;
    String temp;
    SharedPreferences sd;
    String username;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        db = new DatabaseHelper(this);
        ratingBar= findViewById(R.id.ratingbar);
        comment= findViewById(R.id.comment);
        ratecount= findViewById(R.id.ratecount);
        submit= findViewById(R.id.submit);
        showrating= findViewById(R.id.showrating);
        prod_id= findViewById(R.id.pid);
        user= findViewById(R.id.uid);

        sd = getSharedPreferences("user_details",MODE_PRIVATE);
        username  = sd.getString("username",null);

        Intent intent = getIntent();
        final String pro_id = intent.getStringExtra("prodid");

        prod_id.setText(pro_id);
        user.setText(username);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratevalue = ratingBar.getRating();
                if (ratevalue<=1 && ratevalue>0)
                    ratecount.setText(+ratevalue +"");
                else if (ratevalue<=2 && ratevalue>1)
                    ratecount.setText(+ratevalue +"");
                else if (ratevalue<=3 && ratevalue>2)
                    ratecount.setText(+ratevalue +"");
                else if (ratevalue<=4 && ratevalue>3)
                    ratecount.setText(+ratevalue +"");
                else if (ratevalue<=5 && ratevalue>4)
                    ratecount.setText(+ratevalue +"");
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = ratecount.getText().toString();

                showrating.setText( comment.getText());


                String review=showrating.getText().toString();

                db.p_review(pro_id, username, review,temp);
                Toast.makeText(Add_review.this, "reviwed succcesfully ", Toast.LENGTH_LONG).show();
                comment.setText("");
                ratingBar.setRating(0);
                ratecount.setText("");
            }
        });
    }
}