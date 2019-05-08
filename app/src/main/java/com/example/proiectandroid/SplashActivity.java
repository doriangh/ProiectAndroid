package com.example.proiectandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


//        Thread thread = new Thread(){
//
//        };


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
                String scores = helper.getAllScores();
                Toast.makeText(v.getContext(), scores, Toast.LENGTH_LONG).show();
            }
        });

    }
}
