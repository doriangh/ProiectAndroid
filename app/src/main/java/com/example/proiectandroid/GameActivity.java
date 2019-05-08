package com.example.proiectandroid;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private TextView countdown;
    private Button startCountdown;
    private Button share;

    private TextView score;

    private CountDownTimer countDownTimer;
    private long timeLeft = 5000;
    private boolean timerRunning;

    private String userScore;

    myDBAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        countdown = findViewById(R.id.countdown_text);
        startCountdown = findViewById(R.id.start_button);
        score = findViewById(R.id.score);
        share = findViewById(R.id.share);
        share.setVisibility(View.GONE);

        helper = new myDBAdapter(this);

        startCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareText = "I just got " + userScore + " in CountDown Game! Can you beat me?";
                String shareSubj = "Can you beat me?";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareSubj);
                intent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(intent, "Share using"));
            }
        });
    }

    public void startStop(){
        if (timerRunning){
            countdown.setVisibility(View.VISIBLE);
            stopTimer();
        }
        else
            startTimer();
    }

    public void startTimer(){

        countDownTimer = new CountDownTimer(timeLeft, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

                countdown.setText("YOU'VE LOST");
                countdown.setVisibility(View.VISIBLE);
                timerRunning = false;
                startCountdown.setText("Try Again?");
            }
        }.start();

        startCountdown.setText("STOP");

        timerRunning = true;
    }

    public void stopTimer(){

        countDownTimer.cancel();
        startCountdown.setText("START");
        timerRunning = false;
        share.setVisibility(View.VISIBLE);
//        countdown.setText("00:05");

        userScore = "" + (int)timeLeft % 60000 / 1000 + "." + timeLeft % 1000;

        helper.addScore(userScore);

        String scoreText;

        scoreText = "Your score: " + (int)timeLeft %60000 / 1000;
        scoreText += "." + timeLeft % 1000;

        score.setText(scoreText);

        timeLeft = 5000;

    }

    public void updateTimer(){
        int minutes = (int) timeLeft / 60000;
        int seconds = (int) timeLeft % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";


        timeLeftText += "0" + seconds;
        timeLeftText += ".";

        timeLeftText += timeLeft % 1000;

        if (seconds < 2) countdown.setVisibility(View.GONE);

        countdown.setText(timeLeftText);
    }
}
