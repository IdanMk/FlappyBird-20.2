package com.example.itainatan.flappybird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.renderscript.Sampler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


class GameView extends View {
    // this is our custom View class
    Handler handler;
    Runnable runnable;
    Context contextCopy;
    Bird bird;
    Tubes tubes;
    final int UPDATE_MILLIS = 20;
    TextView pointsTV;
    Bitmap background;
    Display display;
    Point point;
    int dWidth ,dHeight;
    Rect rect;
    boolean gameState = false;
    int numOfCoins = 5;
    int [] coinX = new int[numOfCoins];
    int [] coinY = new int[numOfCoins];
    Random random;
    int gamePoints = 0 ;
    boolean isDead;
    Paint paint;
    int Coinoffset = 40;
    Bitmap topTube,bottomTube;

    Bitmap[] birds;
    Bitmap[] coins;

    int coinFrame = 0;

    public GameView(Context context) {
        super(context);
        contextCopy = context;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background_day);
        display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0, 0, dWidth, dHeight);
        random = new Random();

        // Bird initalize
        bird = new Bird();
        birds = new Bitmap[3];
        birds[0] = BitmapFactory.decodeResource(getResources(), R.drawable.yellowbird_1);
        birds[1] = BitmapFactory.decodeResource(getResources(), R.drawable.yellowbird_2);
        birds[2] = BitmapFactory.decodeResource(getResources(), R.drawable.yellowbird_3);
        bird.setBirds(birds);
        bird.setBirdX(dWidth / 2 - bird.getBirds()[0].getWidth());
        bird.setBirdY(dWidth / 2 - bird.getBirds()[0].getHeight()/2);
        bird.setCurrentBirdFrame(0);
        bird.setVelocity(0);
        bird.setGravity(3);

        // Tubes initalization
        tubes =  new Tubes();
        tubes.setGap(300);
        tubes.setNumOfTubes(3);
        tubes.setTopTubeY();
        tubes.setTubeX();
        tubes.setDistancebetweenTubes(dWidth * 7/8);
        tubes.setTubeVelocity(12);
        tubes.setMinTubeoffset(tubes.getGap()/2);
        tubes.setMaxTubeoffset(dHeight - tubes.getMinTubeoffset() - tubes.getGap());
        bottomTube = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_greenbottom);
        topTube = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_greentop);
        tubes.setBottomTube(bottomTube);
        tubes.setTopTube(topTube);



        // coins initalize
        coins = new Bitmap[4];
        coins[0] = BitmapFactory.decodeResource(getResources(),R.drawable.coin_1);
        coins[1] = BitmapFactory.decodeResource(getResources(),R.drawable.coin_2);
        coins[2] = BitmapFactory.decodeResource(getResources(),R.drawable.coin_3);
        coins[3] = BitmapFactory.decodeResource(getResources(),R.drawable.coin_4);

        // points counter text view
        pointsTV = new TextView(getContext());
        pointsTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(48);

        tubes.DisplayTubes(dWidth);
//            coinX[i] = dWidth + i*distancebetweenTubes + distancebetweenTubes/2;
//            coinY[i] = minTubeoffset + random.nextInt(maxTubeoffset - minTubeoffset + 1) + Coinoffset;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        // this function will draw the elements on the canvas
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(48);
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
        canvas.drawBitmap(background,null,rect,null);

        bird.BirdFrameEngine();

        if(coinFrame == 0) {
            coinFrame = 1;
        }else if (coinFrame == 1){
            coinFrame = 2;
        }else if(coinFrame == 2){
            coinFrame = 3;
        }else if(coinFrame == 3){
            coinFrame = 0;
        }


        if(gameState) {
            if ((bird.getBirdY() < dHeight - bird.getBirds()[0].getHeight() || bird.getVelocity() < 0)) {
                bird.BirdUpdate();
            }
            for (int i = 0; i < tubes.getNumOfTubes(); i++) {
                tubes.getTubeX()[i] -= tubes.getTubeVelocity();
//                coinX[i] -= tubeVelocity;

                if(tubes.getTubeX()[i] < - tubes.getTopTube().getWidth()) {
                    tubes.getTubeX()[i] += tubes.getNumOfTubes() * tubes.getDistancebetweenTubes();
                    tubes.getTopTubeY()[i] = tubes.getMinTubeoffset() + random.nextInt(tubes.getMaxTubeoffset() - tubes.getMinTubeoffset() + 1);
                }
//                if(coinX[i] < - coins[0].getWidth()){
//                    coinX[i] += numOfCoins * distancebetweenTubes + distancebetweenTubes/2;
//                    coinY[i] = minTubeoffset + random.nextInt(maxTubeoffset - minTubeoffset + 1) + Coinoffset;
//                }

                if((bird.getBirdX() + birds[0].getWidth() >= tubes.tubeX[i] && bird.getBirdX() <= tubes.tubeX[i] + tubes.getTopTube().getWidth()) && !(bird.getBirdY()>= tubes.topTubeY[i] && bird.getBirdY() + birds[0].getHeight() <= tubes.topTubeY[i] + tubes.getGap()) ){
                    isDead = true;
                    gameState = false;

                    Intent intent = new Intent(getContext(),MainActivity.class);
                    contextCopy.startActivity(intent);
                }
                else if(bird.getBirdX() <= tubes.getTubeX()[i] + tubes.getTopTube().getWidth() && bird.getBirdX() >= tubes.getTubeX()[i] + tubes.getTopTube().getWidth() - tubes.getTubeVelocity() ){
                    gamePoints++;
                }

//                canvas.drawBitmap(coins[coinFrame], coinX[i], coinY[i], null);
                canvas.drawText("Your Points is :" + gamePoints,60,60,paint);
            }

        }
        else if(isDead) {
            for (int i = 0; i < tubes.getNumOfTubes(); i++) {
                gameState = false;
                if ((bird.getBirdY() < dHeight - birds[0].getHeight() || bird.getVelocity() < 0)) {
                    bird.BirdUpdate();
                }
//                canvas.drawBitmap(topTube, tubeX[i], topTubeY[i] - topTube.getHeight(), null);
//                canvas.drawBitmap(bottomTube, tubeX[i], topTubeY[i] + gap, null);

            }
        }

        tubes.DrawTubes(canvas);
        bird.DrawBird(canvas);
        handler.postDelayed(runnable,UPDATE_MILLIS);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            if(!isDead){
                bird.setVelocity(-30);
                gameState = true;
            }
        }

        return true; // By returning true indicates that we finished with the touch event .
    }
}
