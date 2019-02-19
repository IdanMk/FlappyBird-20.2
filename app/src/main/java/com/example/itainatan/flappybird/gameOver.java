package com.example.itainatan.flappybird;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class gameOver extends AppCompatActivity {

    int points;
    TextView pointTV;
    EditText gameovertext;
    ImageButton restart,records,menu, save;
    String name;
    private ArrayList<listOfRecord>  myList = new ArrayList<>();


    public static final String MY_PREFS_NAME = "RECORD_TABLE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        points = getIntent().getIntExtra("points",0);

        menu = findViewById(R.id.gameover_backtomenu);
        restart = findViewById(R.id.gameover_gamerestart);
        records = findViewById(R.id.gameover_recordlist);
        save = findViewById(R.id.gameover_save);
        gameovertext=findViewById(R.id.gameover_textname);


        pointTV = findViewById(R.id.gameover_gamepoints);
        pointTV.setText("Your points is :" + " " + points);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameOver.this,StartGame.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameOver.this,MainActivity.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = gameovertext.getText().toString();
                if(name.isEmpty()) {
                    Toast.makeText(gameOver.this, "Enter a name!",
                            Toast.LENGTH_SHORT).show();
                    return;}

                gameovertext.setText("SAVED!");
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String restoredText = prefs.getString("JSON", "");
                Gson gson = new Gson();
                String json;
                if(restoredText!="") {
                    listOfRecord[] list = gson.fromJson(restoredText, listOfRecord[].class);
                    for (int k = 0; k < list.length; k++) {
                        myList.add(list[k]);
                    }
                }
                listOfRecord lor = new listOfRecord(name, points);
                myList.add(lor);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                json = gson.toJson(myList);
                editor.putString("JSON", json);
                editor.apply();
                gameovertext.setEnabled(false);
                save.setEnabled(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
