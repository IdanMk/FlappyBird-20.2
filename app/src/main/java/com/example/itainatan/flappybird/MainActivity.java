package com.example.itainatan.flappybird;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    ImageButton PlayButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlayButton = findViewById(R.id.homepage_playbutton);


        // Play button onclick event in order to start the game
        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(MainActivity.this,StartGame.class);
                startActivity(intent2);

                Intent intent = new Intent(MainActivity.this,startDialog.class);
                startActivity(intent);

            }
        });
    }
}
