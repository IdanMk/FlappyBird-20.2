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
import android.media.MediaPlayer;
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
    Coins coin;
    final int UPDATE_MILLIS = 20;
    TextView pointsTV;
    Bitmap background;
    Bitmap nightBackground;
    Display display;
    Point point;
    int dWidth ,dHeight;
    Rect rect;
    boolean gameState = false;
    Random random;
    int gamePoints = 0 ;
    boolean isDead;
    Paint paint;
    Bitmap topTube,bottomTube;
    boolean isFirstRun = true;

    MediaPlayer mp;


    Bitmap[] gameStart;

    Bitmap[] birds;
    Bitmap[] coins;


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
        nightBackground = BitmapFactory.decodeResource(getResources(), R.drawable.nightbackground);
        display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0, 0, dWidth,dHeight);
        random = new Random();
        mp = MediaPlayer.create(contextCopy,R.raw.point);

        // Bird initalize
        bird = new Bird();
        birds = new Bitmap[3];
        birds[0] = BitmapFactory.decodeResource(getResources(), R.drawable.yellowbird_1);
        birds[1] = BitmapFactory.decodeResource(getResources(), R.drawable.yellowbird_2);
        birds[2] = BitmapFactory.decodeResource(getResources(), R.drawable.yellowbird_3);
        bird.setBirds(birds);
        bird.setBirdX(dWidth / 2 - bird.getBirds()[0].getWidth());
        bird.setBirdY(dHeight / 2 - bird.getBirds()[0].getHeight()/2);
        bird.setCurrentBirdFrame(0);
        bird.setVelocity(0);
        bird.setGravity(3);

        // Tubes initalization
        tubes =  new Tubes();
        tubes.setGap(300);
        tubes.setNumOfTubes(5);
        tubes.setTopTubeY();
        tubes.setTubeX();
        tubes.setDistancebetweenTubes(dWidth * 7/8);
        tubes.setTubeVelocity(12);
        tubes.setMinTubeoffset(tubes.getGap());
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
        coin = new Coins();
        coin.setCoins(coins);
        coin.setCoinOffset(40);
        coin.setNumOfCoins(5);
        coin.setCoinX();
        coin.setCoinY();
        coin.setDistancebetweenCoins(dWidth * 7/8);
        coin.setCoinVelocity(12);
        coin.setMinOffset(tubes.getGap()/2);
        coin.setMaxOffset(dHeight - tubes.getMinTubeoffset() - tubes.getGap());



        // points counter text view
        pointsTV = new TextView(getContext());
        pointsTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(48);


        for(int i=0; i<tubes.getNumOfTubes();i++){
            tubes.DisplayTubes(dWidth,i);
            coin.CoinSetPosition(dWidth,i);
        }
    }



    @Override
    protected void onDraw(final Canvas canvas) {
        // this function will draw the elements on the canvas
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(48);
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
        if(bird.getPoints() > 3){
            canvas.drawBitmap(nightBackground,null,rect,null);
        }else{
            canvas.drawBitmap(background,null,rect,null);
        }



        bird.BirdFrameEngine();
        coin.CoinFrameEngine();



        if(gameState) {
            if ((bird.getBirdY() < dHeight - bird.getBirds()[0].getHeight() || bird.getVelocity() < 0)) {
                bird.BirdUpdate();
            }
            for (int i = 0; i < tubes.getNumOfTubes(); i++) {
                tubes.updateTubes(i);
                coin.CoinUpdate(i);
                tubes.GenerateNewTube(dWidth,i);
                coin.GenerateNewCoin(dWidth,i,bird.coinCollect(coin,i));
                if(bird.isDead(tubes,i)) {
                    gameState = false;
                   // isDead = true;

                    Intent intent = new Intent(getContext(),gameOver.class);
                    intent.putExtra("points",bird.getPoints());
                    contextCopy.startActivity(intent);
                }
                else{
                    if(bird.pointsGenerator(tubes,i)){
                        mp.start();
                    }
                }

                tubes.DrawTubes(canvas,i);
                coin.DrawCoins(canvas,i);

            }

        }

        canvas.drawText("Your Points is :" + bird.getPoints(),60,60,paint);
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
