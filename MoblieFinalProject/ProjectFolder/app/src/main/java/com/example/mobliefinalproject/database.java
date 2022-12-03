package com.example.mobliefinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "items.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "item";
    private static final String COL_ID ="id" ;
    private static final String COL_NAME = "itemName";
    private static final String COL_PRICE = "itemPrice";
    private static final String COL_QUANTITY = "itemQuantity";

    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " Integer PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " Text NOT NULL," +
                COL_PRICE + " number DEFAULT 0," +
                COL_QUANTITY + " number DEFAULT 0)" + ";" ;
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table " + TABLE_NAME + ";");
        this.onCreate(db);
    }

    public void addEntry(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, itemName);
        values.put(COL_PRICE, itemPrice);
        values.put(COL_QUANTITY, itemQuantity);


        long newRowId = db.insert(TABLE_NAME, null, values);
    }


}
