package com.example.test;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import java.io.File;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Environment;
import android.util.Log;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


public class MainActivity extends AppCompatActivity {



    private int count = 0;
    private static final String DNAME = "myfiles";
    private long startMillis = 0;
    dbhelper db = new dbhelper(this);
    private static final int PERMISSION_REQUEST_CODE = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
        Button bt=(Button)findViewById(R.id.button1);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,activity2.class);
                startActivity(i);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {


        int eventaction = event.getAction() & MotionEvent.ACTION_MASK;
        if (event.getPointerCount() == 3) {
            switch (eventaction) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    update();
            }
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
        String Time = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(new Date());
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
            mOutput.flush();
            mOutput.getFD().sync();
            mOutput.close();
            db.insertData(new files(FILENAME, Time));


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }



    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager
                .PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("success", "Granted");
        } else {
            finish();
        }
    }
}


