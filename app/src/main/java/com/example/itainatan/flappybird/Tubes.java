package com.example.itainatan.flappybird;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

public class Tubes{
    private int numOfTubes;
    private Bitmap topTube, bottomTube;
    private int gap;
    private int minTubeoffset, maxTubeoffset;
    private int distancebetweenTubes;
    private int tubeVelocity;
    int [] tubeX ;
    int [] topTubeY;

    public Tubes() {
    }

    public int getNumOfTubes() {
        return numOfTubes;
    }

    public void setNumOfTubes(int numOfTubes) {
        this.numOfTubes = numOfTubes;
    }

    public Bitmap getTopTube() {
        return topTube;
    }

    public void setTopTube(Bitmap topTube) {
        this.topTube = topTube;
    }

    public Bitmap getBottomTube() {
        return bottomTube;
    }

    public void setBottomTube(Bitmap bottomTube) {
        this.bottomTube = bottomTube;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public int getMinTubeoffset() {
        return minTubeoffset;
    }

    public void setMinTubeoffset(int minTubeoffset) {
        this.minTubeoffset = minTubeoffset;
    }

    public int getMaxTubeoffset() {
        return maxTubeoffset;
    }

    public void setMaxTubeoffset(int maxTubeoffset) {
        this.maxTubeoffset = maxTubeoffset;
    }

    public int getDistancebetweenTubes() {
        return distancebetweenTubes;
    }

    public void setDistancebetweenTubes(int distancebetweenTubes) {
        this.distancebetweenTubes = distancebetweenTubes;
    }

    public int getTubeVelocity() {
        return tubeVelocity;
    }

    public void setTubeVelocity(int tubeVelocity) {
        this.tubeVelocity = tubeVelocity;
    }

    public int[] getTubeX() {
        return tubeX;
    }

    public void setTubeX() {
        this.tubeX = new int[this.numOfTubes];
    }

    public int[] getTopTubeY() {
        return topTubeY;
    }

    public void setTopTubeY() {
        this.topTubeY = new int[this.numOfTubes];
    }

    public void DisplayTubes(int dWidth, int i){
        Random random = new Random();
            this.tubeX[i] = dWidth + i*this.distancebetweenTubes;
            this.topTubeY[i] = this.minTubeoffset +  random.nextInt(this.maxTubeoffset - this.minTubeoffset + 1);
    }

    public void DrawTubes(Canvas canvas,int i){
        canvas.drawBitmap(this.topTube, this.tubeX[i],this.topTubeY[i] - this.topTube.getHeight(), null);
        canvas.drawBitmap(this.bottomTube, this.tubeX[i], this.topTubeY[i] + this.gap, null);
    }

    public void updateTubes(int i){
        this.tubeX[i] -= this.tubeVelocity;
    }

    public void GenerateNewTube(int dWidth,int i) {
        Random random = new Random();
        if (this.tubeX[i] < -this.topTube.getWidth()) {
            this.tubeX[i] += this.numOfTubes * this.distancebetweenTubes;
            this.topTubeY[i] = this.minTubeoffset + random.nextInt(this.maxTubeoffset - this.minTubeoffset + 1);
        }
    }


}