package com.roshan.dic.en_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Activity_word extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final SharedPreferences shared = getSharedPreferences("prefse",0);
        final Boolean theme = shared.getBoolean("theme?",false);

        if(!theme){
            setTheme(R.style.AppTheme);
        }else {
            setTheme(R.style.myAppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// get table name from previous activity(word504)
        Bundle b = getIntent().getExtras();
        String table = b.getString("table");
// send table name to fragment
        Bundle data = new Bundle();
        data.putString("table", table);
        fragment_word fragmentWord = new fragment_word();
        fragmentWord.setArguments(data);

//        Toast.makeText(this, table, Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



}