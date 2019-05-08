package com.example.proiectandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static android.provider.BaseColumns._ID;



public class myDBAdapter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "projectDatabase";
    private static final int DATABASE_VERSION = 4;

    private static myDBAdapter instance;


    public myDBAdapter(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("SQLITEHelper", "Context reached");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(myDBContract.Score.CREATE_TABLE);
        Log.d("SQLITEHelper", "DB Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion){
            db.execSQL("DELETE FROM " + myDBContract.Score.TABLE_NAME);
        }

//        db.execSQL("DROP TABLE IF EXISTS " + myDBContract.Score.TABLE_NAME);
//        onCreate(db);
    }

//    public void addScore(String score){
//        SQLiteDatabase db = getWritableDatabase();
//
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//
//            values.put(SCORE, score);
//            db.insertOrThrow(TABLE_NAME, null, values);
//            db.setTransactionSuccessful();
//
//        } catch (Exception e){
//            Log.d(TAG, "Error adding");
//        } finally {
//            db.endTransaction();
//        }
//    }

//    public String getAllScores(){
//
//        StringBuffer buffer = new StringBuffer();
//        String allScores = "SELECT * FROM " + SCORE;
//        SQLiteDatabase db = getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(allScores, null);
//
//        try{
//            if (cursor.moveToFirst()){
//                do {
//                    int userId = cursor.getInt(cursor.getColumnIndex(UID));
//                    String score = cursor.getString(cursor.getColumnIndex(SCORE));
//                    buffer.append(userId + " " + score + " \n");
//                } while (cursor.moveToNext());
//            }
//        }catch (Exception e){
//            Log.d(TAG, "Error getting");
//        } finally {
//            if (cursor != null && !cursor.isClosed())
//                cursor.close();
//        }
//        return buffer.toString();
//    }
}
