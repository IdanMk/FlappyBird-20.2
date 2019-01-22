package com.example.itainatan.flappybird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class gameOver extends AppCompatActivity {
    int points;
    TextView pointTV;
    ImageButton restart,records,menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        points = getIntent().getIntExtra("points",0);
        menu = findViewById(R.id.gameover_backtomenu);
        restart = findViewById(R.id.gameover_gamerestart);
        records = findViewById(R.id.gameover_recordlist);


        pointTV = findViewById(R.id.gameover_gamepoints);
        pointTV.setText("Your points is :" + " " + points);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameOver.this,StartGame.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
    }
}
