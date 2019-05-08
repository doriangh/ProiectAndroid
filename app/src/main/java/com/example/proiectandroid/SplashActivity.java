package com.example.proiectandroid;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SplashActivity extends Activity {

    myDBAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new myDBAdapter(this);


        Button startGame = (Button)findViewById(R.id.startGame);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, GameActivity.class));
            }
        });

        Button leaderboard = findViewById(R.id.leaderBoard);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = new myDBAdapter(v.getContext()).getReadableDatabase();

                String[] projection = {
                        myDBContract.Score._ID,
                        myDBContract.Score.COLUMN_SCORE
                };

                Cursor cursor = db.query(
                        myDBContract.Score.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null
                );

                Toast.makeText(v.getContext(), "Entries number: " + cursor.getCount(), Toast.LENGTH_LONG).show();


                startActivity(new Intent(SplashActivity.this, Leaderboard.class));

//                String scores = helper.getAllScores();
//                Toast.makeText(v.getContext(), scores, Toast.LENGTH_LONG).show();
            }
        });
    }
}
