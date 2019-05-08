package com.example.proiectandroid;

import android.provider.BaseColumns;

public class myDBContract {
    private myDBContract(){}

    public static class Score implements BaseColumns{
        public static final String TABLE_NAME = "leaderboard";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SCORE = "score";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SCORE + " TEXT" + ")";
    }
}
