package com.example.itainatan.flappybird;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class startDialog extends AppCompatActivity {

    ImageButton outbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_dialog);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*1), (int) (height*1));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setBackgroundDrawable((new ColorDrawable(android.graphics.Color.TRANSPARENT)));


        getWindow().setAttributes(params);

        outbtn = (ImageButton) findViewById(R.id.out);
        outbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(startDialog.this,MainActivity.class);
        startActivity(intent);
    }
}
