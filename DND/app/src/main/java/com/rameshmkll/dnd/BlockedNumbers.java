package com.rameshmkll.dnd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by MRamesh on 19-05-2016.
 */
public class BlockedNumbers extends AppCompatActivity {
   RecyclerView recyclerView;
    ArrayList<NumbersPojo> arrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocked_numbers);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        getNumbers();
    }

    public void getNumbers() {
        arrayList=new ArrayList<>();
        Log.i("get","numbers");
        ArrayList<String> numbers=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=MyApplication.getDatabase(BlockedNumbers.this);
        Cursor cursor=sqLiteDatabase.query("PHONE_NUMBERS",new String[]{"ID","PHONE_NUMBER"},null,null,null,null,null);
        while (cursor.moveToNext()){
            String number=cursor.getString(cursor.getColumnIndex("PHONE_NUMBER"));
            int id=cursor.getInt(cursor.getColumnIndex("ID"));
            NumbersPojo numbersPojo=new NumbersPojo();
            numbersPojo.id=id;
            numbersPojo.number=number;
            arrayList.add(numbersPojo);

        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        NumbersAdapter numbersAdapter=new NumbersAdapter(arrayList,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(numbersAdapter);
        Log.i("numberssize", cursor.getCount() + "");


    }
}
