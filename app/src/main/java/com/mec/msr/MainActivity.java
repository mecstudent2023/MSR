package com.mec.msr;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mec.msr.myclasses.MyDBHelperRequests;
import com.mec.msr.myclasses.MyRequest;
import com.mec.msr.myclasses.MyRequestsAdapter;
import com.mec.msr.myclasses.OnRequestClickedListener;
import com.mec.msr.myclasses.OnRequestDeleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyRequestsAdapter _Adapter;
    private ArrayList<MyRequest> _MyRequestsArrayList;
    MyDBHelperRequests _DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("userId", "onCreate: " + getUserId());


        _DBHelper = new MyDBHelperRequests(this);

        _Adapter = new MyRequestsAdapter();
        _MyRequestsArrayList = _DBHelper.getMyRequestsArrayList(MainActivity.this);

        RecyclerView recyclerViewRequests = findViewById(R.id.recyclerViewRequests);
        _Adapter.setNewData(_MyRequestsArrayList);
        // Attach the adapter to the recyclerview to populate items
        recyclerViewRequests.setAdapter(_Adapter);

        //set on itemClickListener
        _Adapter.setOnItemClickListener(new OnRequestClickedListener() {
            @Override
            public void onRequestClicked(MyRequest myRequestObject) {
                Intent intent = new Intent(MainActivity.this, UpdateEquipmentActivity.class);
                intent.putExtra("my_request", myRequestObject);
                startActivity(intent);
            }
        });

        //set on itemDeleteClickListener
        _Adapter.setOnItemDeleteClickListener(new OnRequestDeleteListener() {
            @Override
            public void OnDeleteClicked(int MyRequestObjectId) {
                displayDialog(MyRequestObjectId);
            }
        });

        // Set layout manager to position the items
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewRequests.setLayoutManager(layoutManager);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEquipmentActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton floatingActionButtonRefresh = findViewById(R.id.floatingActionButtonRefersh);
        floatingActionButtonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Adapter.refresh();
                _Adapter.setNewData(_DBHelper.getMyRequestsArrayList(MainActivity.this));
            }

        });
    }


    private void displayDialog(int MyRequestObjectId) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Alert")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setMessage("Are sure you that you want to delete this item")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        _DBHelper.deleteRequest(MyRequestObjectId);
                    }
                }).show();
    }

    private int getUserId() {
        SharedPreferences sharedPref = getSharedPreferences("msr_pref", Context.MODE_PRIVATE);
        int defaultValue = 1;
        int userId = sharedPref.getInt("user_id", defaultValue);
        return userId;
    }
}