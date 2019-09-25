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

    private  final String TAG = MainActivity.class.getSimpleName();
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
        madapter = new fileAdapter(this,allfiles);
        recyclerView.setAdapter(madapter);

//        newdata();






    }

//    private void newdata() {
//        int currentSize = madapter.getItemCount();
//        allfiles.add(new files("2","4"));
//        allfiles.add(new files("3","5"));
//        allfiles.add(new files("4","5"));
//        allfiles.add(new files("5","6"));
//        madapter.notifyItemRangeInserted(currentSize , allfiles.size() - currentSize);
//    }
}
