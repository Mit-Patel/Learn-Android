package com.learnandy.mitpatel.mcad;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MyAdapter extends ArrayAdapter {

    Context context;
    String[] strings;

    public MyAdapter(@NonNull Context context, @NonNull Object[] objects) {
        super(context, R.layout.list_item, objects);
        this.context = context;
        this.strings = (String[]) objects;
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        String item = strings[position];
//
//
//    }
}
