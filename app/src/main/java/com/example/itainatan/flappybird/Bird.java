package com.example.itainatan.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Toast;

public class Bird {
    private int velocity;
    private int gravity;
    private int birdX,birdY;
    private Bitmap[] birds;
    private int currentBirdFrame;
    private int points;
    private int margin;
    private MediaPlayer mp;

    public Bird() {
        this.margin = 8;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getBirdX() {
        return birdX;
    }

    public void setBirdX(int birdX) {
        this.birdX = birdX;
    }

    public int getBirdY() {
        return birdY;
    }

    public void setBirdY(int birdY) {
        this.birdY = birdY;
    }

    public Bitmap[] getBirds() {
        return birds;
    }

    public void setBirds(Bitmap[] birds) {
        this.birds = birds;
    }

    public int getCurrentBirdFrame() {
        return currentBirdFrame;
    }

    public void setCurrentBirdFrame(int currentBirdFrame) {
        this.currentBirdFrame = currentBirdFrame;
    }

    public Bird(int birdX, int birdY,int gravity,int velocity) {
        this.velocity = velocity;
        this.gravity = gravity;
        this.birdX = birdX;
        this.birdY = birdY;
        this.currentBirdFrame = 0;
        this.points = 0;
    }

    public void BirdFrameEngine(){
        this.currentBirdFrame = (this.currentBirdFrame + 1)%(birds.length);
    }

    public void BirdUpdate(){
        this.velocity += this.gravity;
        this.birdY += this.velocity;
    }

    public void DrawBird(Canvas canvas){
        canvas.drawBitmap(birds[currentBirdFrame],birdX,birdY,null);
    }

    boolean isDead(Tubes tubes ,int i){
        if((this.birdX + this.birds[0].getWidth() >= tubes.tubeX[i] && this.birdX <= tubes.tubeX[i] + tubes.getTopTube().getWidth()) && !(this.birdY>= tubes.topTubeY[i] && this.birdY + this.birds[0].getHeight() <= tubes.topTubeY[i] + tubes.getGap()) ) {
            return true;
        }else
            return false;
        }

    public boolean pointsGenerator(Tubes tubes,int i){
        if(this.birdX <= tubes.getTubeX()[i] + tubes.getTopTube().getWidth() && this.birdX >= tubes.getTubeX()[i] + tubes.getTopTube().getWidth() - tubes.getTubeVelocity() ){
            this.points++;
            return true;
        }else{
            return false;
        }

    }

    public boolean coinCollect(Coins coin , int i){

        if( (this.birdX <= coin.getCoinX()[i] + coin.getCoins()[0].getWidth() - margin && this.birdX >= coin.getCoinX()[i] - coin.getCoinVelocity())
            &&
            (this.birdY >= coin.getCoinY()[i] - margin && this.birdY <= coin.getCoinY()[i]+ coin.getCoins()[0].getHeight() + margin) ){
            this.points += 2;
            return true;
            }else
            return false;
    }

}
