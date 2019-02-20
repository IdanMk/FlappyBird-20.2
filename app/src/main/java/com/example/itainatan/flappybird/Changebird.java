package com.example.itainatan.flappybird;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Changebird extends AppCompatActivity {
    ImageButton bird1, bird2, bird3, bird4;
    String thechosen = "1";
    TextView setChosenBird;
    public static final String MY_PREFS_NAME = "RECORD_TABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changebird);

        bird1 = findViewById(R.id.changebird_bird1);
        bird2 = findViewById(R.id.changebird_bird2);
        bird3 = findViewById(R.id.changebird_bird3);
        bird4 = findViewById(R.id.changebird_bird4);
        setChosenBird = findViewById(R.id.changebird_setText);


        bird1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thechosen = "1";
                set_text(thechosen);
            }
        });

        bird2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thechosen = "2";
                set_text(thechosen);
            }
        });

        bird3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thechosen = "3";
                set_text(thechosen);
            }
        });

        bird4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thechosen = "4";
                set_text(thechosen);
            }
        });
    }

    public void set_text(String thechosen){
        setChosenBird.setText("YOU CHOSOE BIRD" + thechosen);
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("CHOOSEN_BIRD", thechosen);
        editor.apply();
    }
}
