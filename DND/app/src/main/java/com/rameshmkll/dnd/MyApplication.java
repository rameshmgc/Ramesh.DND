package com.rameshmkll.dnd;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.rameshmkll.dnd.Database.DataBaseHandler;

/**
 * Created by MRamesh on 18-05-2016.
 */
public class MyApplication extends Application {
    static SQLiteDatabase sqLiteDatabase;

    public static synchronized SQLiteDatabase getDatabase(Activity activity) {

        return sqLiteDatabase;
    }

    public static void closeDatabase() {
        sqLiteDatabase.close();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this, "PHONE_BOOK", null, 1);
        sqLiteDatabase = dataBaseHandler.getWritableDatabase();


    }
}
