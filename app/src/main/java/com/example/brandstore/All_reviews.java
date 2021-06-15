package com.example.brandstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class All_reviews extends AppCompatActivity {

    private ListView listView;
    private LinearLayout lin;
    private SimpleCursorAdapter adapter4;
    DatabaseHelper db;
    SharedPreferences sd;
    String uid,username,product_id;

    final String[] from = new String[]{db.COL_pro_id,db.COL_user,db.COL_rating,db.COL_review,db.COL_rdate};

    final int[] to = new int[]{R.id.prod_id2, R.id.user2,R.id.rating, R.id.preview2,R.id.r_date};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews);

        db = new DatabaseHelper(this);
        sd = getSharedPreferences("user_details",MODE_PRIVATE);
        uid = sd.getString("uid",null);
        username  = sd.getString("username",null);

        listView = findViewById(R.id.lv_review2);

        final Intent intent = getIntent();

        final String pid = intent.getStringExtra("pid");



        //String sp_id = pid.getText().toString();
        Cursor cursor4 = db.review_list(pid);
        Log.e( "cursor",pid);



        adapter4 = new SimpleCursorAdapter(this,R.layout.review_items, cursor4, from, to,0);
        adapter4.notifyDataSetChanged();

        listView.setAdapter(adapter4);
    }
    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), p_grid_selecteditem.class);
        startActivity(in);
        finish();
    }*/
}