package com.learnandy.mitpatel.ledtestapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;
    private OnUpdateClickListener onUpdateClickListener;

    public MyListAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        try {
            onUpdateClickListener = (OnUpdateClickListener) context;
        }
        catch(ClassCastException e){
            throw  new ClassCastException("Activity must implement OnUpdateClickListener");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.users_list,viewGroup,false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);

        holder.textHead.setText(listItem.getFname() + " " + listItem.getLname() );
        holder.textDesc.setText(listItem.getUsername());
        holder.btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateClickListener.onMoreInfoClick(listItem);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateClickListener.onDeleteClick(listItem);
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateClickListener.onUpdateClick(listItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static interface OnUpdateClickListener{
        void onUpdateClick(ListItem listItem);
        void onDeleteClick(ListItem listItem);
        void onMoreInfoClick(ListItem listItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textHead,textDesc;
        Button btnMoreInfo,btnDelete,btnUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textHead = itemView.findViewById(R.id.text_view_heading);
            textDesc = itemView.findViewById(R.id.text_view_desc);
            btnMoreInfo = itemView.findViewById(R.id.button_more_info);
            btnDelete = itemView.findViewById(R.id.button_delete);
            btnUpdate = itemView.findViewById(R.id.button_update);
        }
    }
}
