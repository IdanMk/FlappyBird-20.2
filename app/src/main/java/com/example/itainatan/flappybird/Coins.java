package com.example.itainatan.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Coins {

    private int numOfCoins;
    private int currentFrame;
    private int [] coinX;
    private int [] coinY;
    private int coinOffset;
    private Bitmap [] coins;
    private int distancebetweenCoins;
    private int coinVelocity;
    private int maxOffset,minOffset;
    private Random random;

    public int getMaxOffset() {
        return maxOffset;
    }

    public void setMaxOffset(int maxOffset) {
        this.maxOffset = maxOffset;
    }

    public int getMinOffset() {
        return minOffset;
    }

    public void setMinOffset(int minOffset) {
        this.minOffset = minOffset;
    }

    public int getDistancebetweenCoins() {
        return distancebetweenCoins;
    }

    public void setDistancebetweenCoins(int distancebetweenCoins) {
        this.distancebetweenCoins = distancebetweenCoins;
    }

    public int getCoinVelocity() {
        return coinVelocity;
    }

    public void setCoinVelocity(int coinVelocity) {
        this.coinVelocity = coinVelocity;
    }

    public Coins(int numOfCoins, int[] coinX, int[] coinY, int coinOffset, Bitmap[] coins) {
        this.numOfCoins = numOfCoins;
        this.currentFrame = 0;
        this.coinX = coinX;
        this.coinY = coinY;
        this.coinOffset = coinOffset;
        this.coins = coins;
    }

    public Coins(){
        this.currentFrame = 0;
    }

    public int getNumOfCoins() {
        return numOfCoins;
    }

    public void setNumOfCoins(int numOfCoins) {
        this.numOfCoins = numOfCoins;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int[] getCoinX() {
        return coinX;
    }

    public void setCoinX() {
        this.coinX = new int[this.numOfCoins];
    }

    public int[] getCoinY() {
        return coinY;
    }

    public void setCoinY() {
        this.coinY = new int[this.numOfCoins];
    }

    public int getCoinOffset() {
        return coinOffset;
    }

    public void setCoinOffset(int coinOffset) {
        this.coinOffset = coinOffset;
    }

    public Bitmap[] getCoins() {
        return coins;
    }

    public void setCoins(Bitmap[] coins) {
        this.coins = coins;
    }

    public void CoinFrameEngine(){
        this.currentFrame = (this.currentFrame + 1)%(coins.length);
    }

    public void DrawCoins(Canvas canvas,int i){
        canvas.drawBitmap(this.coins[this.currentFrame], this.coinX[i], this.coinY[i], null);
    }

    public void CoinUpdate(int i){
        this.coinX[i] -= this.coinVelocity;
    }

    public void CoinSetPosition(int dWidth,int i){
        random = new Random();
        coinX[i] = dWidth + i*this.distancebetweenCoins + this.distancebetweenCoins/2;
        coinY[i] = this.minOffset + random.nextInt(this.maxOffset - this.minOffset + 1) + this.coinOffset;
    }

    public void GenerateNewCoin(int dWidth,int i, boolean isEaten) {
        Random random = new Random();
        if (this.coinX[i] < - this.coins[0].getWidth() && !isEaten ) {
            this.coinX[i] += this.numOfCoins * this.distancebetweenCoins;
            this.coinY[i] = this.minOffset + random.nextInt(this.maxOffset - this.minOffset + 1);
        }
        else if(isEaten){
            this.coinX[i] += this.numOfCoins * this.distancebetweenCoins;
            this.coinY[i] = this.minOffset + random.nextInt(this.maxOffset - this.minOffset + 1);

        }
    }
}
