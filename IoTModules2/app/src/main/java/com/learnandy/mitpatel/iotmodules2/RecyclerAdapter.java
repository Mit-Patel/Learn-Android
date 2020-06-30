package com.learnandy.mitpatel.iotmodules2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<UserDataFields> list;
    private Context context;
    private OnClickAndBindViewHolder onClickAndBindViewHolder;

    public RecyclerAdapter(List<UserDataFields> list, Context context) {
        this.list = list;
        this.context = context;
        try {
            onClickAndBindViewHolder = (OnClickAndBindViewHolder) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement OnUpdateClickListener");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final UserDataFields fields = list.get(i);

        holder.textHead.setText(fields.getFname() + " " + fields.getLname());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAndBindViewHolder.onDeleteClick(fields);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static interface OnClickAndBindViewHolder {
        void onDeleteClick(UserDataFields fields);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textHead;
        ImageButton btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textHead = itemView.findViewById(R.id.text_view_heading);
            btnDelete = itemView.findViewById(R.id.button_delete);
        }
    }
}
