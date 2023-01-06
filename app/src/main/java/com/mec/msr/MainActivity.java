package com.mec.msr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelperRequests dbHelper = new MyDBHelperRequests(this);

        _Adapter = new MyRequestsAdapter();
        _MyRequestsArrayList = dbHelper.getMyRequestsArrayList(MainActivity.this);

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
                dbHelper.deleteRequest(MyRequestObjectId);
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
                _Adapter.setNewData(dbHelper.getMyRequestsArrayList(MainActivity.this));
            }

        });
    }
}