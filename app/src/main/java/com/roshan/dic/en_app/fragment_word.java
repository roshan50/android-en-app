package com.roshan.dic.en_app;


import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_word extends Fragment
{
    private RecyclerView recyclerView;
    private AdapterWord adapterWord;
    private ArrayList<Info_data> arrayList = new ArrayList<Info_data>();
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private String table;

    public fragment_word() {
        // Required empty public constructor
//        String table = getArguments().getString("table");
//        Log.d("table",table);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_word, container, false);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recyWord);
        adapterWord = new AdapterWord(getActivity(), arrayList);
        recyclerView.setAdapter(adapterWord);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


try {
//    table = getArguments().getString("table");
    Toast.makeText(getActivity(), this.table, Toast.LENGTH_SHORT).show();
}catch (Exception e){
    System.out.println("Error: " + e.getMessage());
    e.printStackTrace();
}

//        String table = getArguments().getString("table");


        loadDatabase();
        return viewGroup;
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
////        String bundle = getArguments().getString("table");
////        Log.d("table",bundle);
//    }

    public void loadDatabase(){
        databaseHelper = new DatabaseHelper(getActivity());

        try {
            databaseHelper.checkAndCopyDatabase();
            databaseHelper.openDatabase();
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        try {

//            Toast.makeText(getContext(), this.table, Toast.LENGTH_SHORT).show();

            cursor = databaseHelper.QueryData("select * from table_en");

            if(cursor != null){
                if(cursor.moveToFirst()){
                    do {
                        Info_data info_data = new Info_data();
                        info_data.setWord(cursor.getString(1));
                        info_data.setMeaning(cursor.getString(2));
                        arrayList.add(info_data);
                    }while (cursor.moveToNext());
                }
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    public void setTable(String table1){
        this.table = table1;
    }

}
