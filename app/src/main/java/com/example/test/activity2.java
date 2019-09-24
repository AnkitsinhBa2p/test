package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class activity2 extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private dbhelper mdb;
    private ArrayList<files> allfiles=new ArrayList<>();
    private fileAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rec1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        mdb = new dbhelper(this);
        allfiles = mdb.getAllData();



            Log.d("Reading: ", "Reading all contacts..");
            allfiles = mdb.getAllData();
            madapter = new fileAdapter(this,allfiles);
            recyclerView.setAdapter(madapter);




    }
}
