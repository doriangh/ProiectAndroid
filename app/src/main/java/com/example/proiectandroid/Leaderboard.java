package com.example.proiectandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Leaderboard extends AppCompatActivity {

    private TextView scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        scores = findViewById(R.id.score);

        SQLiteDatabase db = new myDBAdapter(this).getReadableDatabase();

        String[] projection = {
                myDBContract.Score._ID,
                myDBContract.Score.COLUMN_NAME,
                myDBContract.Score.COLUMN_SCORE
        };

        Cursor cursor = db.query(
                myDBContract.Score.TABLE_NAME,
                projection,
                null,
                null,
                myDBContract.Score.COLUMN_SCORE,
                null,
                null
        );

        StringBuffer buffer = new StringBuffer();
        int i = 1;
        while (cursor.moveToNext()){
//            int cid = cursor.getInt(cursor.getColumnIndex(myDBContract.Score._ID));
            String name = cursor.getString(cursor.getColumnIndex(myDBContract.Score.COLUMN_NAME));
            String score = cursor.getString(cursor.getColumnIndex(myDBContract.Score.COLUMN_SCORE));
            buffer.append(i + ". " + name + " " + score + "s \n");
            i ++;
//            buffer.append(cid + ". " + score + " \n");
        }

        scores.setText(buffer.toString());

    }
}
