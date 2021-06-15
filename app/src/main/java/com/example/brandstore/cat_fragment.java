package com.example.brandstore;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cat_fragment extends Fragment {
    productListAdapter adapter = null;
    DatabaseHelper db2;
    RecyclerView rV_cat;
    ArrayList<category> catlists;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        db2 = new DatabaseHelper(getContext());
        rV_cat =  view.findViewById(R.id.rv_categoreis);
        catlists = new ArrayList<>();
        catlistAadapter2 catadapter = new catlistAadapter2(getContext(), catlists);
        rV_cat.setLayoutManager(new GridLayoutManager(getContext(),2));
        rV_cat.setAdapter(catadapter);


        Cursor cursor2 = db2.getData("SELECT * FROM cate_table WHERE cat_status='PRODUCT'");

        while (cursor2.moveToNext()) {
            int cat_id = cursor2.getInt(0);
            String cat_name = cursor2.getString(1);
            byte[] img = cursor2.getBlob(3);

            catlists.add(new category(cat_id, cat_name, img));
        }
    }
}
