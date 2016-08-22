package com.mohammed.shameem.addressbook.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.mohammed.shameem.addressbook.constants.Constants.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shameem on 5/18/2016.
 */
public class DBTools extends SQLiteOpenHelper {
    public DBTools(Context applicationContext) {
        super(applicationContext,DataBaseDetails.DATABASE_MAME, null, DataBaseDetails.DATABASE_VERSION);
    }

    String CREATE_CONTACT_TABLE_QUERY = "CREATE TABLE " + ContactDetails.CONTACT + "(" +
            ContactDetails.CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ContactDetails.FIRST_NAME + " TEXT, " +
            ContactDetails.LAST_NAME + " TEXT, " +
            ContactDetails.PHONE_NUMBER + " TEXT, " +
            ContactDetails.EMAIL_ADDRESS + " TEXT, " +
            ContactDetails.FLASH_SWITCH + " TEXT)";
    String DROP_CONTACT_TABLE_QUERY = "DROP TABLE IF EXISTS " + ContactDetails.CONTACT;

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_CONTACT_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(DROP_CONTACT_TABLE_QUERY);
        onCreate(database);
    }

    //==============================================================================================================//
    //=============================================================================================================//
    // The data type of the data accepted into this method is HashMap<String,String> with both key and value as Strings
    public void insertContact(HashMap<String, String> queryValues) {
        // "this" Here refers to SQLiteOpenHelper class
        // that is the class that is extended primaryly
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues insertValues = new ContentValues();
        insertValues.put(ContactDetails.FIRST_NAME, queryValues.get(ContactDetails.FIRST_NAME));
        insertValues.put(ContactDetails.LAST_NAME, queryValues.get(ContactDetails.LAST_NAME));
        insertValues.put(ContactDetails.PHONE_NUMBER, queryValues.get(ContactDetails.PHONE_NUMBER));
        insertValues.put(ContactDetails.EMAIL_ADDRESS, queryValues.get(ContactDetails.EMAIL_ADDRESS));
        //Newly Added field
        insertValues.put(ContactDetails.FLASH_SWITCH, queryValues.get(ContactDetails.FLASH_SWITCH));
        database.insert(ContactDetails.CONTACT, null, insertValues);
        database.releaseReference();
    }

    // The data type of the data accepted into this method is HashMap<String,String> with both key and value as Strings
    public int updateContact(HashMap<String, String> queryValues) {
        // "this" Here refers to SQLiteOpenHelper class
        // that is the class that is extended primaryly
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put(ContactDetails.FIRST_NAME, queryValues.get(ContactDetails.FIRST_NAME));
        updateValues.put(ContactDetails.LAST_NAME, queryValues.get(ContactDetails.LAST_NAME));
        updateValues.put(ContactDetails.PHONE_NUMBER, queryValues.get(ContactDetails.PHONE_NUMBER));
        updateValues.put(ContactDetails.EMAIL_ADDRESS, queryValues.get(ContactDetails.EMAIL_ADDRESS));
        updateValues.put(ContactDetails.FLASH_SWITCH, queryValues.get(ContactDetails.FLASH_SWITCH));
        return database.update(ContactDetails.CONTACT, updateValues, ContactDetails.CONTACT_ID + "=?", new String[]{queryValues.get(ContactDetails.CONTACT_ID)});

    }

    // The data type of the data accepted into this method is String
    public void deleteContact(String CCNTACT_ID) {
        // "this" Here refers to SQLiteOpenHelper class
        // that is the class that is extended primaryly
        SQLiteDatabase database = this.getWritableDatabase();
        String DELETE_QUERY = "DELETE FROM "+ContactDetails.CONTACT+" WHERE "+ContactDetails.CONTACT_ID+"='" + CCNTACT_ID + "'";
        database.execSQL(DELETE_QUERY);
    }

    public ArrayList<HashMap<String, String>> getAllContacts() {
        ArrayList<HashMap<String, String>> contactArrayList = new ArrayList<>();
        String SELECT_ALL_CONTACT_QUERY = "SELECT * FROM "+ContactDetails.CONTACT +" ORDER BY "+ContactDetails.LAST_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        Cursor cursor = database.rawQuery(SELECT_ALL_CONTACT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> contactMap = new HashMap<>();
                contactMap.put(ContactDetails.CONTACT_ID, cursor.getString(0));
                contactMap.put(ContactDetails.FIRST_NAME, cursor.getString(1));
                contactMap.put(ContactDetails.LAST_NAME, cursor.getString(2));
                contactMap.put(ContactDetails.PHONE_NUMBER, cursor.getString(3));
                contactMap.put(ContactDetails.EMAIL_ADDRESS, cursor.getString(4));
                //Newly Added field
                contactMap.put(ContactDetails.FLASH_SWITCH, cursor.getString(5));
                contactArrayList.add(contactMap);
            } while (cursor.moveToNext());
        }
        return contactArrayList;
    }

    public HashMap<String, String> getContactInformation(String ID) {
        HashMap<String, String> contactHashMap = new HashMap<>();
        String SELECT_QUERY = "SELECT * FROM "+ContactDetails.CONTACT+" WHERE "+ContactDetails.CONTACT_ID+"='" + ID + "'";
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues updateValues = new ContentValues();
        Cursor cursor = database.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {

                contactHashMap.put(ContactDetails.CONTACT_ID, cursor.getString(0));
                contactHashMap.put(ContactDetails.FIRST_NAME, cursor.getString(1));
                contactHashMap.put(ContactDetails.LAST_NAME, cursor.getString(2));
                contactHashMap.put(ContactDetails.PHONE_NUMBER, cursor.getString(3));
                contactHashMap.put(ContactDetails.EMAIL_ADDRESS, cursor.getString(4));
                //Newly Added field
                contactHashMap.put(ContactDetails.FLASH_SWITCH, cursor.getString(5));
            } while (cursor.moveToNext());
        }
        return contactHashMap;
    }


}
