package com.example.brandstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;

import java.util.ArrayList;

public class review_avg_adapter extends BaseAdapter {


    private Context context;
    private  int layout;
    private ArrayList<reviewavg> revlist;
    DatabaseHelper db;
    public review_avg_adapter(Context context, int layout, ArrayList<reviewavg> revlist) {
    }

    @Override
    public int getCount() {
        return revlist.size();
    }

    @Override
    public Object getItem(int position) {
        return revlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();
        db = new DatabaseHelper(context);

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);



            holder.rating_bar =  (RatingBar) row.findViewById(R.id.rating);

            row.setTag(holder);
        }
        return null;
    }

    static class ViewHolder{


        RatingBar rating_bar;
    }
}
