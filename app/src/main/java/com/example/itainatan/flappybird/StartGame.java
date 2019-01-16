package com.example.itainatan.flappybird;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StartGame extends Activity {

    GameView gameView;
    TextView pointsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        pointsTV = findViewById(R.id.game_points);
        gameView = new GameView(this);
        setContentView(gameView);
    }
}
