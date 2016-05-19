package com.mohammed.shameem.addressbook;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shameem on 5/18/2016.
 */
public class DBTools extends SQLiteOpenHelper {
    public DBTools(Context applicationContext) {
        super(applicationContext, "addressbook.db", null, 1);
    }
    String CREATE_CONTACT_TABLE_QUERY = "CREATE TABLE CONTACT(CONTACT_ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT, LAST_NAME TEXT, PHONE_NUMBER TEXT, EMAIL_ADDRESS TEXT)";
    String DROP_CONTACT_TABLE_QUERY = "DROP TABLE IF EXISTS CONTACT";
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_CONTACT_TABLE_QUERY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database , int oldVersion, int newVersion) {
        database.execSQL(DROP_CONTACT_TABLE_QUERY);
        onCreate(database);
    }
//==============================================================================================================//
//=============================================================================================================//
    // The data type of the data accepted into this method is HashMap<String,String> with both key and value as Strings
    public void insertContact(HashMap<String,String> queryValues){
        // "this" Here refers to SQLiteOpenHelper class
        // that is the class that is extended primaryly
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues insertValues =new ContentValues();
        insertValues.put("FIRST_NAME",queryValues.get("FIRST_NAME"));
        insertValues.put("LAST_NAME",queryValues.get("LAST_NAME"));
        insertValues.put("PHONE_NUMBER",queryValues.get("PHONE_NUMBER"));
        insertValues.put("EMAIL_ADDRESS",queryValues.get("EMAIL_ADDRESS"));
        database.insert("CONTACT",null,insertValues);
        database.releaseReference();
    }

    // The data type of the data accepted into this method is HashMap<String,String> with both key and value as Strings
    public int updateContact(HashMap<String,String> queryValues){
        // "this" Here refers to SQLiteOpenHelper class
        // that is the class that is extended primaryly
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues updateValues =new ContentValues();
        updateValues.put("FIRST_NAME",queryValues.get("FIRST_NAME"));
        updateValues.put("LAST_NAME",queryValues.get("LAST_NAME"));
        updateValues.put("PHONE_NUMBER",queryValues.get("PHONE_NUMBER"));
        updateValues.put("EMAIL_ADDRESS",queryValues.get("EMAIL_ADDRESS"));
        return database.update("CONTACT",updateValues,"CONTACT_ID"+"=?",new String[]{queryValues.get("CONTACT_ID")});

    }

    // The data type of the data accepted into this method is String
    public void deleteContact(String CCNTACT_ID){
        // "this" Here refers to SQLiteOpenHelper class
        // that is the class that is extended primaryly
        SQLiteDatabase database=this.getWritableDatabase();
        String DELETE_QUERY="DELETE FROM CONTACT WHERE CONTACT_ID='"+CCNTACT_ID+"'";
        database.execSQL(DELETE_QUERY);
    }

public ArrayList<HashMap<String,String>>getAllContacts(){
    ArrayList<HashMap<String,String>>contactArrayList=new ArrayList<>();
    String SELECT_ALL_CONTACT_QUERY="SELECT * FROM CONTACT";
    SQLiteDatabase database=this.getWritableDatabase();
    ContentValues updateValues =new ContentValues();
    Cursor cursor=database.rawQuery(SELECT_ALL_CONTACT_QUERY,null);
    if(cursor.moveToFirst()){
        do{
            HashMap<String,String>contactMap=new HashMap<>();
            contactMap.put("CONTACT_ID",cursor.getString(0));
            contactMap.put("FIRST_NAME",cursor.getString(1));
            contactMap.put("LAST_NAME",cursor.getString(2));
            contactMap.put("PHONE_NUMBER",cursor.getString(3));
            contactMap.put("EMAIL_ADDRESS",cursor.getString(4));
            contactArrayList.add(contactMap);
        }while (cursor.moveToNext());
    }
    return contactArrayList;
}
    public HashMap<String,String>getContactInformation(String ID){
        HashMap<String,String>contactHashMap=new HashMap<>();
        String SELECT_QUERY="SELECT * FROM CONTACT WHERE CONTACT_ID='"+ID+"'";
        SQLiteDatabase database=this.getReadableDatabase();
        ContentValues updateValues =new ContentValues();
        Cursor cursor=database.rawQuery(SELECT_QUERY,null);
        if(cursor.moveToFirst()){
            do{

                contactHashMap.put("CONTACT_ID",cursor.getString(0));
                contactHashMap.put("FIRST_NAME",cursor.getString(1));
                contactHashMap.put("LAST_NAME",cursor.getString(2));
                contactHashMap.put("PHONE_NUMBER",cursor.getString(3));
                contactHashMap.put("EMAIL_ADDRESS",cursor.getString(4));

            }while (cursor.moveToNext());
        }
        return contactHashMap;
    }


}
