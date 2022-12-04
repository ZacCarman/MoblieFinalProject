package com.example.mobliefinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "items.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "items";
    private static final String COL_ID ="id" ;
    private static final String COL_NAME = "name";
    private static final String COL_PRICE = "price";
    private static final String COL_QUANTITY = "quantity";

    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " TEXT PRIMARY KEY," +
                COL_NAME + " TEXT NOT NULL," +
                COL_PRICE + " INTEGER DEFAULT 0," +
                COL_QUANTITY + " DOUBLE DEFAULT 0)" + ";" ;
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table " + TABLE_NAME + ";");
        this.onCreate(db);
    }

    public void addEntry(String name, String id, int quantity, double price) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_NAME, name);
        values.put(COL_PRICE, price);
        values.put(COL_QUANTITY, quantity);

        if (!checkID().contains(id)){
            db.insert(TABLE_NAME, null, values);
        }


        db.close();

    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public ArrayList<String> checkID() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorItems = db.rawQuery("SELECT id FROM " + TABLE_NAME, null);

        ArrayList<String> itemModalArrayList = new ArrayList<>();

        if (cursorItems.getCount()>0) {

            if (cursorItems.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    itemModalArrayList.add(cursorItems.getString(0));
                } while (cursorItems.moveToNext());

            }
        }
        return itemModalArrayList;
    }
}
