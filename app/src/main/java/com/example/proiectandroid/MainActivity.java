package com.example.proiectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }
}
