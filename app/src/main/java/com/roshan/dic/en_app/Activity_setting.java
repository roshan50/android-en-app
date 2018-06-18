package com.roshan.dic.en_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_setting extends AppCompatActivity {
    private SwitchCompat switchCompat;

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
        setContentView(R.layout.activity_setting);

        switchCompat = (SwitchCompat) findViewById(R.id.switch_settings);
        switchCompat.setChecked(theme);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = shared.edit();
                if(! switchCompat.isChecked()){
                    editor.putBoolean("theme?",false);
                }else {
                    editor.putBoolean("theme?",true);
                }
                editor.commit();
                recreate();
                Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        String[] fruitArray = {"apple","banana","chabbage","cherry",
                "eggplant","glans","kiwi","mellon","onion","orange","pomegranate","straberry","tomato"};
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fruitArray);
        ListView listView = (ListView) findViewById(R.id.lll);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Toast.makeText(Activity_setting.this, item, Toast.LENGTH_SHORT).show();
            }

        });
    }
}
