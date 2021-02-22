package uk.ac.reading.cs2ja16;

//Other parts of the android libraries that we use
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.SoundPool;

import java.util.ArrayList;

public class TheGameHighway extends GameThread{

    public char[][] levelH = {{'O','O','O','O','O','O','O','O','O','P','O','O','O','O','O','O','O','O','P','O','O','O','O','O','O','O','O','O'},{'O','P','P','P','P','P','P','P','P','P','O','O','O','O','O','O','O','O','P','P','P','P','P','P','P','P','P','O'},{'O','P','W','W','W','P','W','W','W','P','O','O','O','O','O','O','O','O','P','W','W','W','P','W','W','W','P','O'},{'O','P','W','W','W','P','W','W','W','P','O','O','O','O','O','O','O','O','P','W','W','W','P','W','W','W','P','O'},{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},{'O','P','W','W','W','P','W','W','W','P','W','W','W','W','P','W','W','W','P','W','W','W','P','W','W','W','P','O'},{'O','P','W','W','W','P','W','W','W','P','W','W','W','W','P','W','W','W','P','W','W','W','P','W','W','W','P','O'},{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},{'O','O','P','W','W','W','P','W','W','P','W','W','W','P','W','W','W','W','P','W','W','P','W','W','W','P','O','O'},{'O','O','P','W','W','W','P','W','W','P','W','W','W','P','W','W','W','W','P','W','W','P','W','W','W','P','O','O'},{'O','O','P','W','W','W','P','W','W','P','W','W','W','P','W','W','W','W','P','W','W','P','W','W','W','P','O','O'},{'P','P','P','P','P','P','P','P','P','E','E','E','E','G','G','E','E','E','E','P','P','P','P','P','P','P','P','P'},{'O','O','P','W','W','W','P','W','W','E','B','B','B','B','B','B','B','B','E','W','W','P','W','W','W','P','O','O'},{'O','O','P','W','W','W','P','W','W','E','B','B','B','B','B','B','B','B','E','W','W','P','W','W','W','P','O','O'},{'P','P','P','P','P','P','P','W','W','E','B','B','B','B','B','B','B','B','E','W','W','P','P','P','P','P','P','P'}, {'O','O','P','W','W','W','P','W','W','E','B','B','B','B','B','B','B','B','E','W','W','P','W','W','W','P','O','O'},{'O','O','P','W','W','W','P','W','W','E','B','B','B','B','B','B','B','B','E','W','W','P','W','W','W','P','O','O'},{'P','P','P','P','P','P','P','P','P','E','E','E','E','E','E','E','E','E','E','P','P','P','P','P','P','P','P','P'},{'O','O','P','W','W','W','P','W','W','P','W','W','W','W','P','W','W','W','P','W','W','P','W','W','W','P','O','O'},{'O','O','P','W','W','W','P','W','W','P','W','W','W','W','P','W','W','W','P','W','W','P','W','W','W','P','O','O'},{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},{'O','O','P','W','W','W','P','W','W','P','W','W','W','W','W','W','W','W','P','W','W','P','W','W','W','P','O','O'},{'O','O','P','W','W','W','P','W','W','P','W','W','W','W','W','W','W','W','P','W','W','P','W','W','W','P','O','O'},{'P','P','P','P','P','P','P','P','P','P','P','P','P','S','S','P','P','P','P','P','P','P','P','P','P','P','P','P'},{'O','P','W','W','W','P','W','W','W','P','W','W','W','W','P','W','W','W','P','W','W','W','P','W','W','W','P','O'},{'O','P','W','W','W','P','W','W','W','P','W','W','W','W','P','W','W','W','P','W','W','W','P','W','W','W','P','O'},{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},{'O','P','W','W','W','P','W','W','W','P','O','O','O','O','O','O','O','O','P','W','W','W','P','W','W','W','P','O'},{'O','P','W','W','W','P','W','W','W','P','O','O','O','O','O','O','O','O','P','W','W','W','P','W','W','W','P','O'},{'O','P','P','P','P','P','P','P','P','P','O','O','O','O','O','O','O','O','P','P','P','P','P','P','P','P','P','O'},{'O','O','O','O','O','O','O','O','O','P','O','O','O','O','O','O','O','O','P','O','O','O','O','O','O','O','O','O'}};


    PlayArena pa;

    int movDirec = 0;//Sent to pacman

    float ratiox;
    float ratioxx;

    Bitmap upBtn;
    Bitmap rightBtn;
    Bitmap downBtn;
    Bitmap leftBtn;
    Bitmap blank;

    float timer = 0;

    //This is run before anything else, so we can prepare things here
    public TheGameHighway(GameView gameView) {
        //House keeping
        super(gameView);

        pa = new PlayArena(mGameView);

        pa.newMapLoad(levelH);

        upBtn = BitmapFactory.decodeResource(mGameView.getResources(), R.drawable.upbtn);
        rightBtn = BitmapFactory.decodeResource(mGameView.getResources(), R.drawable.rightbtn);
        downBtn = BitmapFactory.decodeResource(mGameView.getResources(), R.drawable.downbtn);
        leftBtn = BitmapFactory.decodeResource(mGameView.getResources(), R.drawable.leftbtn);
        blank = BitmapFactory.decodeResource(mGameView.getResources(), R.drawable.blankbtn);
    }
    /**
     * not really used in the game, no significance, some leftovers from early work on the game that don't affect the game
     */
    //This is run before a new game (also after an old game)
    @Override
    public void setupBeginning() {

        //pa.newMapLoad(levelH);
        //Initialise speeds
        //mCanvasWidth and mCanvasHeight are declared and managed elsewhere
        pa.pacman.speed =  80;

        pa.blinky.frameCount = 2;

        pa.inky.frameCount = 2;
        //Place the ball in the middle of the screen.
        //mBall.Width() and mBall.getHeigh() gives us the height and width of the image of the ball


        //Place Paddle in the middle of the screen


        //Place SmileyBall in the top middle of the screen

        //Place all SadBalls forming a pyramid underneath the SmileyBall


        //Get the minimum distance between a small ball and a bigball
        //We leave out the square root to limit the calculations of the program
        //Remember to do that when testing the distance as well

    }
    /**
     * Draw to the screen the arena, p-dots, power pellets, the ghosts, pacman and the buttons
     * @param canvas
     */
    @Override
    protected void doDraw(Canvas canvas) {
        //If there isn't a canvas to do nothing
        //It is ok not understanding what is happening here
        if(canvas == null) return;

        //House keeping
        super.doDraw(canvas);

        //canvas.drawBitmap(bitmap, x, y, paint) uses top/left corner of bitmap as 0,0
        //we use 0,0 in the middle of the bitmap, so negate half of the width and height of the ball to draw the ball as expected
        //A paint of null means that we will use the image without any extra features (called Paint)
        ratiox = mCanvasHeight/124;
        ratioxx = mCanvasHeight/31;
        //NOTE VALUES** one square is canvasHeight/31 by canvasHeight/31 draw from 0 Height to maxHeight,
        // canvasWidth/2 - 14(canvasHeight/31) to canvasWidth/2 + 14(canvasHeight/31) where 14 is the first and last square from canvasWidth centre giving 28 squares.
        // **Pacman and ghosts move in this maze with 8x more depth splitting the maze similar to maze components but using 248 as height and 112 instead of 14.
        for (int i = 0; i < pa.SpriteNumberList[0].length; i++){
            for (int j = 0; j < pa.SpriteNumberList.length; j++){
                canvas.drawBitmap(pa.Sprite.get(pa.SpriteNumberList[j][i]), null, new RectF(mCanvasWidth/2+(i-14)*ratioxx,ratioxx*j, mCanvasWidth/2+(i-13)*ratioxx, ratioxx*(j+1)), null);
            }
        }

        //Another for loop for PDot, PowerPellets, items etc

        for (int i = 0; i < pa.powerPelletsList.size(); i++){
            canvas.drawBitmap(pa.powerPelletsList.get(i).Sprite, null, new RectF((mCanvasWidth/2)+(pa.powerPelletsList.get(i).posx-14)*ratioxx, pa.powerPelletsList.get(i).posy*ratioxx, (mCanvasWidth/2)+(pa.powerPelletsList.get(i).posx-13)*ratioxx, pa.powerPelletsList.get(i).posy*ratioxx+1*ratioxx), null);
        }

        for (int i = 0; i < pa.pDotList.size(); i++){
            canvas.drawBitmap(pa.pDotList.get(i).Sprite, null, new RectF((mCanvasWidth/2)+(pa.pDotList.get(i).posx-14)*ratioxx, pa.pDotList.get(i).posy*ratioxx, (mCanvasWidth/2)+(pa.pDotList.get(i).posx-13)*ratioxx, pa.pDotList.get(i).posy*ratioxx+1*ratioxx), null);
        }

        //draw the image of the ball using the X and Y of the ball
        //canvas.drawBitmap(pacman.Sprite.get(pacman.frameCount), null, new RectF((mCanvasWidth/2)+pacman.posx*ratiox-pacman.Sprite.get(pacman.frameCount).getWidth()*ratiox, (mCanvasHeight/2)+pacman.posy*ratiox-pacman.Sprite.get(pacman.frameCount).getHeight()*ratiox, (mCanvasWidth/2)+pacman.posx*ratiox+pacman.Sprite.get(1).getWidth()*ratiox, (mCanvasHeight/2)+pacman.posy*ratiox+pacman.Sprite.get(pacman.frameCount).getHeight()*ratiox), null);
        //Pacman and other game sprites are smaller than they usually would be because of the new outer wall sprites
        canvas.drawBitmap(pa.pacman.Sprite.get(pa.pacman.frameCount), null, new RectF((mCanvasWidth/2)+pa.pacman.posx*ratioxx, pa.pacman.posy*ratioxx, (mCanvasWidth/2)+pa.pacman.posx*ratioxx+1*ratioxx, pa.pacman.posy*ratioxx+1*ratioxx), null);
        canvas.drawBitmap(pa.blinky.Sprite.get(pa.blinky.frameCount), null, new RectF((mCanvasWidth/2)+pa.blinky.posx*ratioxx, pa.blinky.posy*ratioxx, (mCanvasWidth/2)+pa.blinky.posx*ratioxx+1*ratioxx, pa.blinky.posy*ratioxx+1*ratioxx), null);
        canvas.drawBitmap(pa.inky.Sprite.get(pa.inky.frameCount), null, new RectF((mCanvasWidth/2)+pa.inky.posx*ratioxx, pa.inky.posy*ratioxx, (mCanvasWidth/2)+pa.inky.posx*ratioxx+1*ratioxx, pa.inky.posy*ratioxx+1*ratioxx), null);
        canvas.drawBitmap(pa.pinky.Sprite.get(pa.pinky.frameCount), null, new RectF((mCanvasWidth/2)+pa.pinky.posx*ratioxx, pa.pinky.posy*ratioxx, (mCanvasWidth/2)+pa.pinky.posx*ratioxx+1*ratioxx, pa.pinky.posy*ratioxx+1*ratioxx), null);
        canvas.drawBitmap(pa.clyde.Sprite.get(pa.clyde.frameCount), null, new RectF((mCanvasWidth/2)+pa.clyde.posx*ratioxx, pa.clyde.posy*ratioxx, (mCanvasWidth/2)+pa.clyde.posx*ratioxx+1*ratioxx, pa.clyde.posy*ratioxx+1*ratioxx), null);

        //Draw Paddle using X of paddle and the bottom of the screen (top/left is 0,0)
//        Paint paint = new Paint();//THIS DIDN'T WORK :(
//        paint.setColor(Color.WHITE);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawText(Integer.toString(mCanvasWidth) + "x" + Integer.toString(mCanvasHeight), mCanvasWidth/2, mCanvasHeight/2, paint);


        //Draw SmileyBall
        //canvas.drawBitmap(mSmileyBall, mSmileyBallX - mSmileyBall.getWidth() / 2, mSmileyBallY - mSmileyBall.getHeight() / 2, null);

        //Loop through all SadBall
//        for(int i = 0; i < mSadBallX.length; i++) {
//            //Draw SadBall in position i
//            canvas.drawBitmap(mSadBall, mSadBallX[i] - mSadBall.getWidth() / 2, mSadBallY[i] - mSadBall.getHeight() / 2, null);
//        }
        canvas.drawBitmap(leftBtn, null, new RectF((mCanvasWidth/2)+15*ratioxx, 20*ratioxx, (mCanvasWidth/2)+19*ratioxx, 24*ratioxx), null);
        canvas.drawBitmap(rightBtn, null, new RectF((mCanvasWidth/2)+23*ratioxx, 20*ratioxx, (mCanvasWidth/2)+27*ratioxx, 24*ratioxx), null);
        canvas.drawBitmap(upBtn, null, new RectF((mCanvasWidth/2)+19*ratioxx, 16*ratioxx, (mCanvasWidth/2)+23*ratioxx, 20*ratioxx), null);
        canvas.drawBitmap(downBtn, null, new RectF((mCanvasWidth/2)+19*ratioxx, 24*ratioxx, (mCanvasWidth/2)+23*ratioxx, 28*ratioxx), null);
        canvas.drawBitmap(blank, null, new RectF((mCanvasWidth/2)+19*ratioxx, 20*ratioxx, (mCanvasWidth/2)+23*ratioxx, 24*ratioxx), null);
    }

    /**
     * check touch falls within the button positions
     * @param x
     * @param y
     */
    //This is run whenever the phone is touched by the user
    @Override
    protected void actionOnTouch(float x, float y) {
        //Format xmin, xmax, ymin, ymax
        if (x >= (mCanvasWidth/2)+15*ratioxx && x <= (mCanvasWidth/2)+19*ratioxx && y >= 20*ratioxx && y <= 24*ratioxx){//left
            movDirec = 4;
            //updateScore(1);//Test
        }
        else if(x >= (mCanvasWidth/2)+23*ratioxx && x <= (mCanvasWidth/2)+27*ratioxx && y >= 20*ratioxx && y <= 24*ratioxx){//right
            movDirec = 2;
            //updateScore(1);//Test
        }
        else if(x >= (mCanvasWidth/2)+19*ratioxx && x <= (mCanvasWidth/2)+23*ratioxx && y >= 16*ratioxx && y <= 20*ratioxx){//up
            movDirec = 1;
            //updateScore(1);//Test
        }
        else if(x >= (mCanvasWidth/2)+19*ratioxx && x <= (mCanvasWidth/2)+23*ratioxx && y >= 24*ratioxx && y <= 28*ratioxx){//down
            movDirec = 3;
            //updateScore(1);//Test
        }
    }

    /**
     * Not used, not suitable for the game
     * @param xDirection
     * @param yDirection
     * @param zDirection
     */
    //This is run whenever the phone moves around its axises
    @Override
    protected void actionWhenPhoneMoved(float xDirection, float yDirection, float zDirection) {
        //Change the paddle speed
        //mPaddleSpeedX = mPaddleSpeedX + 70f * xDirection;

        //If paddle is outside the screen and moving further away
        //Move it into the screen and set its speed to 0
        //if(mPaddleX <= 0 && mPaddleSpeedX < 0) {
        //  mPaddleSpeedX = 0;
        //mPaddleX = 0;
        //}
        //if(mPaddleX >= mCanvasWidth && mPaddleSpeedX > 0) {
        //  mPaddleSpeedX = 0;
        //mPaddleX = mCanvasWidth;
        //}

    }

    /**
     * update the game. check if pacman is dead and adjust score before any pick ups, then pac-dot and power pellet checks and adjust score
     * next is a time for the power up. Move pacman and the all ghosts.
     * @param secondsElapsed
     */
    //This is run just before the game "scenario" is printed on the screen
    @Override
    protected void updateGame(float secondsElapsed) {
        updateScore(pa.deadCheck());
        updateScore(pa.pickUp());
        if (pa.pacman.activePowerUp == 1){//activate power pellet timer
            timer += secondsElapsed;
            if (timer > 6){
                timer = 0;
                pa.pacman.activePowerUp = 0;
                pa.blinky.weak = false;
                pa.inky.weak = false;
                pa.pinky.weak = false;
                pa.clyde.weak = false;
            }
        }
        if (movDirec != 0){
            pa.movePacman(movDirec);
            movDirec = 0;
        }
        pa.moveAll();

        //If the ball moves down on the screen
//        if(mBallSpeedY > 0) {
//            //Check for a paddle collision
//            updateBallCollision(mPaddleX, mCanvasHeight);
//        }
//
//        //Move the ball's X and Y using the speed (pixel/sec)
//        mBallX = mBallX + secondsElapsed * mBallSpeedX;
//        mBallY = mBallY + secondsElapsed * mBallSpeedY;
//
//        //Move the paddle's X and Y using the speed (pixel/sec)
//        mPaddleX = mPaddleX + secondsElapsed * mPaddleSpeedX;
//
//
//        //Check if the ball hits either the left side or the right side of the screen
//        //But only do something if the ball is moving towards that side of the screen
//        //If it does that => change the direction of the ball in the X direction
//        if((mBallX <= mBall.getWidth() / 2 && mBallSpeedX < 0) || (mBallX >= mCanvasWidth - mBall.getWidth() / 2 && mBallSpeedX > 0) ) {
//            mBallSpeedX = -mBallSpeedX;
        //}

        //Check for SmileyBall collision
//        if(updateBallCollision(mSmileyBallX, mSmileyBallY)) {
//            //Increase score
//            updateScore(1);
//        }
//
//        //Loop through all SadBalls
//        for(int i = 0; i < mSadBallX.length; i++) {
//            //Perform collisions (if necessary) between SadBall in position i and the red ball
//            updateBallCollision(mSadBallX[i], mSadBallY[i]);
//        }
//
//        //If the ball goes out of the top of the screen and moves towards the top of the screen =>
//        //change the direction of the ball in the Y direction
//        if(mBallY <= mBall.getWidth() / 2 && mBallSpeedY < 0) {
//            mBallSpeedY = -mBallSpeedY;
//        }
//
//        //If the ball goes out of the bottom of the screen => lose the game
//        if(mBallY >= mCanvasHeight) {
//            setState(GameThread.STATE_LOSE);
//        }

    }
    /**
     * Not used, old game
     * @param x
     * @param y
     * @return
     */
    //Collision control between mBall and another big ball
    private boolean updateBallCollision(float x, float y) {
//        //Get actual distance (without square root - remember?) between the mBall and the ball being checked
//        float distanceBetweenBallAndPaddle = (x - mBallX) * (x - mBallX) + (y - mBallY) *(y - mBallY);
//
//        //Check if the actual distance is lower than the allowed => collision
//        if(mMinDistanceBetweenBallAndPaddle >= distanceBetweenBallAndPaddle) {
//            //Get the present speed (this should also be the speed going away after the collision)
//            float speedOfBall = (float) Math.sqrt(mBallSpeedX*mBallSpeedX + mBallSpeedY*mBallSpeedY);
//
//            //Change the direction of the ball
//            mBallSpeedX = mBallX - x;
//            mBallSpeedY = mBallY - y;
//
//            //Get the speed after the collision
//            float newSpeedOfBall = (float) Math.sqrt(mBallSpeedX*mBallSpeedX + mBallSpeedY*mBallSpeedY);
//
//            //using the fraction between the original speed and present speed to calculate the needed
//            //velocities in X and Y to get the original speed but with the new angle.
//            mBallSpeedX = mBallSpeedX * speedOfBall / newSpeedOfBall;
//            mBallSpeedY = mBallSpeedY * speedOfBall / newSpeedOfBall;
//
//            return true;
//        }
//
        return false;
    }
}

// This file is part of the course "Begin Programming: Build your first mobile game" from futurelearn.com
// Copyright: University of Reading and Karsten Lundqvist
// It is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// It is is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
//
// You should have received a copy of the GNU General Public License
// along with it.  If not, see <http://www.gnu.org/licenses/>.
