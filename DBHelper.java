package com.example.a1.timon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DAATABASE_NAME = "taskDb";
    public static final String TABLE_TASKS= "tasks";

    public static final String KEY_ID = "_id";
    public static final String price = "price";
    public static final String title = "title";
    public  static final String time = "time";
    public static final String category= "category";
    public static final String description = "description";
    public static final String wayOfPaying = "way_of_paying";
    public static final String imageViewInt = "image_int" ;
    public static final String  imageViewUri = "image_uri";



    public DBHelper(Context context) {

        super(context, DAATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tasks" +
                "(_id  INTEGER PRIMARY KEY, " +
                price + " TEXT, " +
                title + " TEXT, "+
                time + " TEXT, " +
                category + " TEXT, " +
                description + " TEXT, "+
                wayOfPaying  + " TEXT, " +
                imageViewUri + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table  if exists " + TABLE_TASKS);

        onCreate(db);

    }
}
