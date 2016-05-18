package com.mohammed.shameem.addressbook;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shameem on 5/18/2016.
 */
public class DBTools extends SQLiteOpenHelper {
    public DBTools(Context applicationContext, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(applicationContext, "addressbook.db",null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
