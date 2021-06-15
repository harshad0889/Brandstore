package com.example.brandstore;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

//import android.widget.SearchView;

//import android.widget.SearchView;

public class search_activity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView searchview;
    ListView search_list;
    EditText search_edit;
    DatabaseHelper db2;
    String [] values= {"shirts","pants","trousers"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);
        searchview =findViewById(R.id.search_bar);
        search_list = findViewById(R.id.search_list);
        searchview.setSubmitButtonEnabled(true);
        searchview.setIconified(false);



        List<String> names = new ArrayList<>();
        db2 = new DatabaseHelper(this);
        Cursor cursor3 = db2.getData("SELECT  DISTINCT cat_name from cate_table");
        if(cursor3 != null){
            while(cursor3.moveToNext()){
                names.add(cursor3.getString(cursor3.getColumnIndex(DatabaseHelper.COL_ctname)));

            }
        }



        ArrayAdapter<String> adapter1;
        ArrayList<String> searchlist;
        searchlist = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,names);
        search_list.setAdapter(adapter);
        searchview.setOnQueryTextListener(this);
       //searchview.setQuery(adapter.getItem(0),false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setView(search_list);
        final  AlertDialog dialog = builder.create();
        //searchlist.contains(query);
        //search_edit.setText(adapter);
       // searchlist.get(onOptionsItemSelected());


        search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchview.setQuery(adapter.getItem(i),false);
            }
        });







}
    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent s1 = new Intent(this,cat_prod_view.class);
        s1.putExtra("catname",query);
        startActivity(s1);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.getFilter().filter(text);

        //searchview.setQuery( adapter.getPosition(newText),false);


        return false;
    }
}