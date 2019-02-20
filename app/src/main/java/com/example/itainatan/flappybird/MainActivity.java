package com.example.itainatan.flappybird;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {
    Button PlayButton , Customize , RecordsTable;
    ImageView Logo;
    public static final String MY_PREFS_NAME = "RECORD_TABLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlayButton = findViewById(R.id.homepage_playbutton);
        Customize = findViewById(R.id.homepage_backgroundChange);
        RecordsTable = findViewById(R.id.homepage_recordbutton);

        Logo = findViewById(R.id.homepage_logo);

        Animation LogoAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.logo_movement);
        final Animation ltrButtonAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.ltr_button_animation);
        final Animation rtlButtonAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rtl_button_animation);

        Logo.startAnimation(LogoAnimation);

        Customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this ,Customize.class);
                startActivity(intent);
            }
        });

        LogoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                PlayButton.setVisibility(View.VISIBLE);
                RecordsTable.setVisibility(View.VISIBLE);
                Customize.setVisibility(View.VISIBLE);

                PlayButton.startAnimation(ltrButtonAnimation);
                RecordsTable.startAnimation(rtlButtonAnimation);
                Customize.startAnimation(ltrButtonAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

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
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
