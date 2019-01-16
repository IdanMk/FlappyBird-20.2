package com.example.itainatan.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public class Bird {
    private int velocity;
    private int gravity;
    private int birdX,birdY;
    private Bitmap[] birds;
    private int currentBirdFrame;

    public Bird() {

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

}
