package com.rameshmkll.dnd;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.rameshmkll.dnd.utils.ColoredSnackbar;
import com.rameshmkll.dnd.utils.Utilities;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ((RadioGroup) findViewById(R.id.rgCompare)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbf:
                        break;
                    case R.id.rbl:
                        break;
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if (((EditText) findViewById(R.id.etPhone)).getText().toString().trim().equals("")) {
                    Utilities.shakeView(MainActivity.this, findViewById(R.id.etPhone));
                    ((EditText) findViewById(R.id.etPhone)).setError("Please enter mobile number");
                } else {
                    SQLiteDatabase database = MyApplication.getDatabase(MainActivity.this);
                    ContentValues cv = new ContentValues();
                    cv.put("PHONE_NUMBER", ((EditText) findViewById(R.id.etPhone)).getText().toString().trim());
                    long i = database.insert("PHONE_NUMBERS", null, cv);
                    if (i > 0) {
                        ColoredSnackbar.info(Utilities.showSnackBarLongTime2(MainActivity.this, "Number added successfully")).show();
                    }
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, BlockedNumbers.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
