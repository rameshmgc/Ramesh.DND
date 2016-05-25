package com.rameshmkll.dnd.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MRamesh on 18-05-2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {
    String CREATE_TABLE = "CREATE TABLE  IF NOT EXISTS PHONE_NUMBERS(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,PHONE_NUMBER INTEGER NOT NULL)";

    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
