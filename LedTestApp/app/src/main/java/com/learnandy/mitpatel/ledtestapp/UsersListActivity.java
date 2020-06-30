package com.learnandy.mitpatel.ledtestapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsersListActivity extends AppCompatActivity implements MyListAdapter.OnUpdateClickListener {

    RecyclerView recyclerView;

    DBHelper mydb;
    List<ListItem> listItems;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        recyclerView = findViewById(R.id.recycler_view_users);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        loadRecyclerViewData();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                loadRecyclerViewData();
            }
        });
    }

    public void showMessage(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void loadRecyclerViewData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        listItems.clear();

        mydb = new DBHelper(this);
        Cursor res = mydb.getAllUsers();
        if(res.getCount()<= 0){
            showMessage("Alert","No data found!");
        }

        res.moveToFirst();
        while (!res.isAfterLast()) {
            ListItem listItem = new ListItem(
                    res.getString(res.getColumnIndex(DBHelper.COL_FNAME)),
                    res.getString(res.getColumnIndex(DBHelper.COL_LNAME)),
                    res.getString(res.getColumnIndex(DBHelper.COL_USERNAME)),
                    res.getString(res.getColumnIndex(DBHelper.COL_PASSWORD)),
                    res.getString(res.getColumnIndex(DBHelper.COL_EMAIL)),
                    res.getString(res.getColumnIndex(DBHelper.COL_MOBILE_NO)),
                    Integer.parseInt(res.getString(res.getColumnIndex(DBHelper.COL_HOUSE_ID))),
                    Integer.parseInt(res.getString(res.getColumnIndex(DBHelper.COL_ID))),
                    Integer.parseInt(res.getString(res.getColumnIndex(DBHelper.COL_IS_ADMIN)))
            );

            listItems.add(listItem);
            res.moveToNext();
        }
        res.close();
        mydb.close();

        RecyclerView.Adapter adapter = new MyListAdapter(listItems, this);
        recyclerView.setAdapter(adapter);

        progressDialog.dismiss();
    }

    @Override
    public void onUpdateClick(ListItem listItem) {
        Intent intent = new Intent();
        intent.putExtra("updateValues", (Serializable) listItem);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onDeleteClick(ListItem listItem) {
        DBHelper mydb = new DBHelper(this);
        int res = mydb.deleteUser(listItem.getUser_id() + "");
        mydb.close();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("User Data");
        if (res > 0)
            builder.setMessage("User deleted successfully!");
        else
            builder.setMessage("User not deleted!");

        builder.show();

        swipeRefreshLayout.setRefreshing(true);
        loadRecyclerViewData();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onMoreInfoClick(ListItem listItem) {

        DBHelper mydb = new DBHelper(this);
        Cursor res = mydb.getUser(listItem.getUser_id() + "");
        StringBuffer stringBuffer = new StringBuffer();
        res.moveToFirst();
        while (!res.isAfterLast()) {
            stringBuffer.append("\n Id: " + res.getString(0));
            stringBuffer.append("\n Fname: " + res.getString(1));
            stringBuffer.append("\n Lname: " + res.getString(2));
            stringBuffer.append("\n Username: " + res.getString(3));
            stringBuffer.append("\n Password: " + res.getString(4));
            stringBuffer.append("\n House Id: " + res.getString(5));
            stringBuffer.append("\n Email: " + res.getString(6));
            stringBuffer.append("\n Mob no: " + res.getString(7));
            stringBuffer.append("\n Admin: " + res.getString(8));

            res.moveToNext();
        }
        res.close();
        mydb.close();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("User Data");
        builder.setMessage(stringBuffer.toString());
        builder.show();
    }
}
