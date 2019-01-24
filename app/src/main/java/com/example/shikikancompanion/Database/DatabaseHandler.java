package com.example.shikikancompanion.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static String DB_NAME = "GLFADB.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, 1);
        if(Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir+"/databases/";
        }else{
            DB_PATH = "/data/data/"+context.getPackageName()+"/databases/";
        }

        this.myContext = context;
    }

    @Override
    public synchronized void close(){
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //database does't exist yet.
            Log.e("SQLiteException", "Database doesn't exist");
        }
        if (checkDB != null) {
            checkDB.close();
            return true;
        }else{
            return false;
        }
    }

    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase(){

        boolean dbExist = checkDataBase();

        if (!dbExist){
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db1) {
//        db1.execSQL(
//                ("CREATE TABLE recipe (id INTEGER PRIMARY KEY, name TEXT, rarity INTEGER, time TEXT, standard TEXT, heavy TEXT)")
//        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            try{
                copyDataBase();
            }catch (IOException e){
                Log.e("onUpgrade:", "Unable to copy database");
            }
        }
    }

//    public void insertItem(String name, int rarity, String time, String standard, String heavy) {
//        SQLiteDatabase db3 = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("rarity", rarity);
//        values.put("time", time);
//        values.put("standard", standard);
//        values.put("heavy", heavy);
//        db3.insert("recipe", null, values);
//        db3.close();
//    }

//    public ArrayList<String> getNameS(int rare, String set) {
//        ArrayList<String> temp = new ArrayList<>();
//        SQLiteDatabase db4 = this.getWritableDatabase();
//        String selectQuery = "SELECT name FROM recipe WHERE rarity =" + rare + " AND standard = '" + set + "'";
//        Log.e("query", selectQuery);
//
//        String s = db4.getPath();
//        Log.e("get path", s);
//        Cursor cursor = db4.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                temp.add(cursor.getString(0));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db4.close();
//        return temp;
//    }
//
//    public ArrayList<String> getTimeS(int rare, String set) {
//        ArrayList<String> temp = new ArrayList<>();
//        SQLiteDatabase db4 = this.getWritableDatabase();
//        String selectQuery = "SELECT time FROM recipe WHERE rarity =" + rare + " AND standard = '" + set + "'";
//        Log.e("query", selectQuery);
//        String s = db4.getPath();
//        Cursor cursor = db4.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                temp.add(cursor.getString(0));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db4.close();
//        return temp;
//    }

    public String getNameS(int rare, String set) {
        String temp = "";
        SQLiteDatabase db4 = this.getWritableDatabase();
        String selectQuery = "SELECT name FROM recipe WHERE rarity =" + rare + " AND standard = '" + set + "'";
        Log.e("query", selectQuery);

        String s = db4.getPath();
        Log.e("get path", s);
        Cursor cursor = db4.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                temp += (cursor.getString(0) + "\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        db4.close();
        return temp;
    }

    public String getTimeS(int rare, String set) {
        String temp = "";
        SQLiteDatabase db4 = this.getWritableDatabase();
        String selectQuery = "SELECT time FROM recipe WHERE rarity =" + rare + " AND standard = '" + set + "'";
        Log.e("query", selectQuery);
        String s = db4.getPath();
        Cursor cursor = db4.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                temp += (cursor.getString(0) + "\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        db4.close();
        return temp;
    }

    public String getNameH(int rare, String set) {
        String temp = "";
        SQLiteDatabase db4 = this.getWritableDatabase();
        String selectQuery = "SELECT name FROM recipe WHERE rarity =" + rare + " AND heavy = '" + set + "'";
        Log.e("query", selectQuery);

        String s = db4.getPath();
        Log.e("get path", s);
        Cursor cursor = db4.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                temp += (cursor.getString(0) + "\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        db4.close();
        return temp;
    }

    public String getTimeH(int rare, String set) {
        String temp = "";
        SQLiteDatabase db4 = this.getWritableDatabase();
        String selectQuery = "SELECT time FROM recipe WHERE rarity =" + rare + " AND heavy = '" + set + "'";
        Log.e("query", selectQuery);
        String s = db4.getPath();
        Cursor cursor = db4.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                temp += (cursor.getString(0) + "\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        db4.close();
        return temp;
    }
}