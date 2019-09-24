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
    private dbhelper db;
    private ArrayList<files> allfiles=new ArrayList<>();
    private fileAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rec1);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        db = new dbhelper(this);
        allfiles.addAll(db.getAllData());
//        String detail= "";
//
//        for(int i=0; i<allfiles.size();i++){
//            files file=new files();
//            file=allfiles.get(i);
//            detail += "\n Name: " + file.getFname() + " time : " + file.getTime() + " \n";
//
//        }

//            Log.d("Reading: ", "Reading all contacts..");
//            allfiles = mdb.getAllData();
            madapter = new fileAdapter(this,allfiles);
            recyclerView.setAdapter(madapter);




    }
}
