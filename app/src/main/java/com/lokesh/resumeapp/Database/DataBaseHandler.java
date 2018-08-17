package com.lokesh.resumeapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "local.db";
    private static final String TABLE_CONTACTS = "login";
    private static final String KEY_MOBILE = "mobile";
    private static final String CUS_ACTIVE="Cus_active";
    private static final String PIN="pin";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                    + KEY_MOBILE + " TEXT PRIMARY KEY,"
                    + CUS_ACTIVE + " TEXT,"
                    + PIN + " TEXT"
                    + ")";
            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
 }

    public void addContact(Database contact) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_MOBILE, contact.getMobile());
            values.put(CUS_ACTIVE, contact.getCusActiveStatus());
            values.put(PIN, contact.getPin());
            db.insert(TABLE_CONTACTS, null,values);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }

   }

    public List<Database> getAllContacts() {
        List<Database> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

       if (cursor.moveToFirst()) {
            do {
                Database contact = new Database();
                contact.setMobile(cursor.getString(0));
                contact.setCusActiveStatus(cursor.getString(1));
                contact.setPin(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }

    public List<Database> getActiveCus() {
        List<Database> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS + " WHERE  " + CUS_ACTIVE + " = '" + "1" + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Database contact = new Database();
                contact.setMobile(cursor.getString(0));
                contact.setCusActiveStatus(cursor.getString(1));
                contact.setPin(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public int updateActiveStatusCustomer(Database contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CUS_ACTIVE, contact.getCusActiveStatus());

        // updating row
        //return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
        //new String[] { String.valueOf(contact.getID()) });
        return db.update(TABLE_CONTACTS, values, KEY_MOBILE + " = ?", new String[]{String.valueOf(contact.getMobile())});
    }
    public List<Database> getCusInfoViaCusId(String id) {
        List<Database> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS + " WHERE  " + KEY_MOBILE + " = '" + id + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Database contact = new Database();
                contact.setMobile(cursor.getString(1));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public int updateContact(Database contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MOBILE, contact.getMobile());
        return db.update(TABLE_CONTACTS, values, KEY_MOBILE + " = ?", new String[] { String.valueOf(contact.getMobile()) });
    }
}
