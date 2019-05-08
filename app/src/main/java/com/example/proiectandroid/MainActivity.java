package com.example.proiectandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, SplashActivity.class));

        finish();

//        Button startGame = (Button)findViewById(R.id.startGame);
//
//        startGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, GameActivity.class));
//            }
//        });
//
//        Button leaderboard = findViewById(R.id.leaderBoard);
//        leaderboard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GetAll();
//            }
//        });
    }

    public void GetAll(){
        SQLiteDatabase db = new myDBAdapter(this).getReadableDatabase();

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

        Toast.makeText(this, "Entries number: " + cursor.getCount(), Toast.LENGTH_LONG).show();
    }
}
