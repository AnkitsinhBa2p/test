package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbhelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "files.db";
    private static final String TABLE_NAME = "file_table";
    private static final String COL_1 = "ID";
    private final String COL_2 = "NAME";
    private  final String COL_3 = "TIME";

    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void insertData(files file) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, file.getFname());
        contentValues.put(COL_3, file.getTime());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public ArrayList<files> getAllData() {


        ArrayList<files> filesList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for (int i=0;i<cursor.getCount();i++){
                files file = new files();
                file.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
                file.setFname(cursor.getString(cursor.getColumnIndex(COL_2)));
                file.setTime(cursor.getString(cursor.getColumnIndex(COL_3)));

                filesList.add(file);
                cursor.moveToNext();
            }
        }


        db.close();

        return filesList;

    }



}
