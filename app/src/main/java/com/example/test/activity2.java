package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

            Toolbar toolbar=findViewById(R.id.tool);
            setSupportActionBar(toolbar);

            RecyclerView recyclerView = findViewById(R.id.rec1);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);

            db = new dbhelper(this);
            allfiles.addAll(db.getAllData());
            madapter = new fileAdapter(this, allfiles);
            recyclerView.setAdapter(madapter);
            madapter.notifyDataSetChanged();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_name:
                Toast.makeText(this, "name sort", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sort_time:
                Toast.makeText(this, "time sort", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
