package com.learnandy.mitpatel.iotmodules2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnClickAndBindViewHolder {
    //UI references
    FloatingActionButton fabCreateUser;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;
    CoordinatorLayout coordinatorLayout;
    DBHelper mydb;
    List<UserDataFields> list;

    //session manager object
    private SessionManager sessionManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        list = new ArrayList<>();

        //views in control
        coordinatorLayout = findViewById(R.id.main);
        fabCreateUser = findViewById(R.id.fab_add);
        recyclerView = findViewById(R.id.recycler_view_users);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        swipeRefreshLayout = findViewById(R.id.main_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                loadRecyclerViewData();
            }
        });

        //get session variable
        sessionManager = new SessionManager(getApplicationContext());

        //check if user is logged in
        sessionManager.checkLogin();

        //make toast
        if (sessionManager.isLoggedIn()) {
            Toast.makeText(this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error while login!", Toast.LENGTH_SHORT).show();
        }

        loadRecyclerViewData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        swipeRefreshLayout.setRefreshing(true);
        loadRecyclerViewData();
        swipeRefreshLayout.setRefreshing(false);
    }


    //load the users and show into recycler view
    private void loadRecyclerViewData() {
        list.clear();

        mydb = new DBHelper(this);
        Cursor res = mydb.getAllUsers(sessionManager.getHouseId());
        if (res.getCount() <= 0) {
            Toast.makeText(this, "No users found!", Toast.LENGTH_SHORT).show();
            return;
        }

        res.moveToFirst();
        while (!res.isAfterLast()) {
            UserDataFields listItem = new UserDataFields(
                    res.getString(res.getColumnIndex(DBHelper.COL_FNAME)),
                    res.getString(res.getColumnIndex(DBHelper.COL_LNAME)),
                    res.getString(res.getColumnIndex(DBHelper.COL_USERNAME)),
                    res.getString(res.getColumnIndex(DBHelper.COL_PASSWORD)),
                    res.getString(res.getColumnIndex(DBHelper.COL_EMAIL)),
                    res.getString(res.getColumnIndex(DBHelper.COL_MOBILE_NO)),
                    Integer.parseInt(res.getString(res.getColumnIndex(DBHelper.COL_HOUSE_ID))),
                    Integer.parseInt(res.getString(res.getColumnIndex(DBHelper.COL_ID)))
            );

            list.add(listItem);
            res.moveToNext();
        }
        res.close();
        mydb.close();

        adapter = new RecyclerAdapter(list, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    //actions when options menu items clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.op_menu_logout:
                sessionManager.logoutUser();
                finish();
                break;
            case R.id.op_menu_share_house_id:
                startActivity(new Intent(this, ShareHouseIdActivity.class));
                break;
            case R.id.op_menu_get_house_id:
                startActivityForResult(new Intent(this, GetHouseIdActivity.class),1);
                break;
            case R.id.op_menu_feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
        }

        return true;
    }

    //result from gethouseid
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Common.showAlertMessage(this,data.getStringExtra("title"),data.getStringExtra("message"));
            }
            if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    public void fabCreateUserOnClick(View view) {
        startActivity(new Intent(this, CreateUserActivity.class));
    }

    //deleting the user
    @Override
    public void onDeleteClick(UserDataFields fields) {
        if (mydb.deleteUser(fields.getUser_id() + "") == 1) {
            list.remove(fields);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "User deleted!", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(true);
            loadRecyclerViewData();
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
