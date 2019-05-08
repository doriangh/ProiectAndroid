package com.example.proiectandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;


public class myDBAdapter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "projectDatabase";
    private static final String TABLE_NAME = "leaderboard";
    private static final int DATABASE_Version = 1;
    private static final String UID = "_id";
    private static final String SCORE = "score";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static myDBAdapter instance;


    public static synchronized myDBAdapter getInstance(Context context){
        if (instance == null){
            instance = new myDBAdapter(context.getApplicationContext());
        }
        return instance;
    }

    public myDBAdapter(Context context){
        super(context, DATABASE_NAME, null, DATABASE_Version);
        Log.d("SQLITEHelper", "Context reached");
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        super.onConfigure(db);

//        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "( " +
                UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SCORE + " VARCHAR(255)" +
                " )";
        db.execSQL(CREATE_TABLE);
        Log.d("SQLITEHelper", "DB Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void addScore(String score){
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();

            values.put(SCORE, score);
            db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e){
            Log.d(TAG, "Error adding");
        } finally {
            db.endTransaction();
        }
    }

    public String getAllScores(){

        StringBuffer buffer = new StringBuffer();
        String allScores = "SELECT * FROM " + SCORE;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(allScores, null);

        try{
            if (cursor.moveToFirst()){
                do {
                    int userId = cursor.getInt(cursor.getColumnIndex(UID));
                    String score = cursor.getString(cursor.getColumnIndex(SCORE));
                    buffer.append(userId + " " + score + " \n");
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d(TAG, "Error getting");
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return buffer.toString();
    }
}
