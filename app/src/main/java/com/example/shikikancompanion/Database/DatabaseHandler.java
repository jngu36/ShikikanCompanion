package com.example.shikikancompanion.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.shikikancompanion.Doll;
import com.example.shikikancompanion.indexHolder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static String DB_NAME = "scdb.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, 1);
        if (Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }

        this.myContext = context;
        try {
            copyDataBase();
        } catch (IOException e) {

        }
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
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
        } else {
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
    public void createDataBase() {

        boolean dbExist = checkDataBase();

        if (dbExist) {
        } else {
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e("onUpgrade:", "Unable to copy database");
            }
        }
    }


    public String getNameS(int rare, String set) {
        String temp = "";
        SQLiteDatabase db4 = this.getWritableDatabase();
        String selectQuery = "SELECT name FROM dolls WHERE rarity =" + rare + " AND standard = '" + set + "'";
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
        String selectQuery = "SELECT time FROM dolls WHERE rarity =" + rare + " AND standard = '" + set + "'";
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
        String selectQuery = "SELECT name FROM dolls WHERE rarity =" + rare + " AND heavy = '" + set + "'";
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
        String selectQuery = "SELECT time FROM dolls WHERE rarity =" + rare + " AND heavy = '" + set + "'";
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

    public ArrayList<Integer> getIndexList() {
        ArrayList<Integer> temp = new ArrayList<>();

        SQLiteDatabase db4 = this.getWritableDatabase();
        String selectQuery = "SELECT id FROM dolls";
        Log.e("query", selectQuery);
        Cursor cursor = db4.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                temp.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db4.close();
        return temp;
    }

    public indexHolder getDollInfo(int id) {
        indexHolder d = new indexHolder();
        SQLiteDatabase db4 = this.getWritableDatabase();
        String selectQuery = "SELECT id, name, rarity, type, time, obtain, hp, firepower, evasion, accuracy, rof, clip, armor, speed, ap, critical, skill, skilld, skillic, skillcd, skillUrl, tile, tiled, intro, pictureUrl, pictureDUrl FROM dolls WHERE id = " + id;
        Cursor cursor = db4.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                d = new indexHolder(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getInt(8),
                        cursor.getInt(9),
                        cursor.getInt(10),
                        cursor.getInt(11),
                        cursor.getInt(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getInt(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getInt(18),
                        cursor.getInt(19),
                        cursor.getString(20),
                        cursor.getString(21),
                        cursor.getString(22),
                        cursor.getString(23),
                        cursor.getString(24),
                        cursor.getString(25)
                );
            } while (cursor.moveToNext());
        }
        return d;
    }

    public ArrayList<indexHolder> getIndex() {
        ArrayList<indexHolder> temp = new ArrayList<>();
        indexHolder hold;
        SQLiteDatabase db4 = this.getWritableDatabase();
        String selectQuery = "SELECT id, name, thumbURL, type, rarity FROM dolls";
        Log.e("query", selectQuery);
        Cursor cursor = db4.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                hold = new indexHolder(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)
                );
                temp.add(hold);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db4.close();
        return temp;
    }

    public ArrayList<Doll> getAll() {
        ArrayList<Doll> arr = new ArrayList<>();
        Doll temp;

        SQLiteDatabase db4 = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM dolls";
        Log.e("query", selectQuery);
        Cursor cursor = db4.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                temp = new Doll(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getInt(9),
                        cursor.getInt(10),
                        cursor.getInt(11),
                        cursor.getInt(12),
                        cursor.getInt(13),
                        cursor.getInt(14),
                        cursor.getInt(15),
                        cursor.getInt(16),
                        cursor.getInt(17),
                        cursor.getInt(18),
                        cursor.getString(19),
                        cursor.getString(20),
                        cursor.getInt(21),
                        cursor.getString(22),
                        cursor.getString(23),
                        cursor.getInt(24),
                        cursor.getInt(25),
                        cursor.getInt(26),
                        cursor.getInt(27),
                        cursor.getInt(28),
                        cursor.getString(29),
                        cursor.getString(30)
                );
                arr.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db4.close();
        return arr;
    }
}