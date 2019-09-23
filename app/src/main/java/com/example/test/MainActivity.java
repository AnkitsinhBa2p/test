package com.example.test;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    private int count = 0;
    private static final String DNAME = "myfiles";
    private long startMillis = 0;
    dbhelper db = new dbhelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public boolean onTouchEvent(MotionEvent event) {


        int cnt = event.getPointerCount();
        int eventaction = event.getAction() & MotionEvent.ACTION_MASK;
        if (cnt == 3) {

            update();
            cnt=0;


        }


        if (eventaction == MotionEvent.ACTION_UP) {


            long time = System.currentTimeMillis();


            if (startMillis == 0 || (time - startMillis > 3000)) {
                startMillis = time;
                count = 1;
            } else {
                count++;
            }

            if (count == 3) {
                update();

            }
            return true;
        }


        return false;


    }

    private void update() {
        String FILENAME = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss'.txt'").format(new Date());
        String Time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File rootPath = new File(Environment.getExternalStorageDirectory(), DNAME);
        if (!rootPath.exists()) {

            if (rootPath.mkdirs()) {
                Log.d("ANDROID_TEST", "Directory create success :" + rootPath.getAbsolutePath());

            } else {
                Log.d("ANDROID_TEST", "FAILED TO CREATE DIRECTORY :" + rootPath.getAbsolutePath());

            }
        }

        File dataFile = new File(rootPath, FILENAME);


        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "Cannot use storage.", Toast.LENGTH_SHORT).show();
            finish();

        } else {
            Log.d("ANDROID_TEST", " Found external dir :" + dataFile.getAbsolutePath());
        }


        try {
            FileOutputStream mOutput = new FileOutputStream(dataFile, true);
            Toast.makeText(this, "sucess", Toast.LENGTH_SHORT).show();
//            String filename = FILENAME;
//            String tt = Time;
            mOutput.flush();
            mOutput.getFD().sync();
            mOutput.close();
            db.insertData(new files(FILENAME, Time));
            getData();


        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    private void getData() {
        Log.d("Reading: ", "Reading all contacts..");
        ArrayList<files> file = db.getAllData();

        for (files cn : file) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getFname() + " ,Time: " +
                    cn.getTime();
            // Writing Contacts to log
            Log.d("Name: ", log);

        }


    }
}


