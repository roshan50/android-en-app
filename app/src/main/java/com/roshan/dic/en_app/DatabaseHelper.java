package com.roshan.dic.en_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Asus on 5/29/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase myDatabase;

    public DatabaseHelper(Context context) {
        super(context, Info_db.DB_NAME , null, Info_db.DB_VERSION);

        if(Build.VERSION.SDK_INT > 15){
            Info_db.DB_PATH = context.getApplicationInfo().dataDir+"/databases/";
        }else {
            Info_db.DB_PATH = Environment.getDataDirectory()+"/data/"+context.getPackageName()+" /databases/ ";
        }

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void checkAndCopyDatabase(){
        boolean dbExist = checkDatabase();
        if(dbExist == true){
            Log.d("TAG","database already exits");
        }else {
            this.getReadableDatabase();
        }

        try {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG","Error Copy Database");
        }
    }

    public boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try {
            String myPath = Info_db.DB_PATH + Info_db.DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB !=null ? true : false;
    }

    private void copyDatabase() throws IOException{
        InputStream myInput = context.getAssets().open(Info_db.DB_NAME);
        String outFileName = Info_db.DB_PATH + Info_db.DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0){
            myOutput.write(buffer, 0 , length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase(){
        String myPath = Info_db.DB_PATH + Info_db.DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close(){
        if(myDatabase != null){
            myDatabase.close();
        }

        super.close();
    }

    public Cursor QueryData(String query){
        Cursor res = myDatabase.rawQuery(query,null);
        return res;
    }
}
