package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;

import java.util.ArrayList;

public abstract class Enemy extends MovingObject {
    ArrayList<Bitmap> Sprite;//Array list of all required sprites. All sprites are loaded to memory ready for use
    private int frameCount = 1;//intial frame
    int posx;//x-coord position of obj
    int posy;//y-coord position of obj

    public int posyy;//8x grid detail position
    public int posxx;//8x grid detail position
    public int direcHolder;//Current Direction

    private int boxMove = 1;//In box movement

    boolean weak = false;//vulnerable to pacman
    boolean dead = false;//moving back to box

    /**
     * Move Enemy in the play arena. Consists of various checks and randomness to guide the enemy and move in a
     * reasonable way.
     */
    abstract void Move();

    /**
     * move in random motion
     */
    abstract void randomMove();

    /**
     * when coord positions match move player to start and reduce life
     */
    abstract void capturePlayer();

    /**
     * when coord positions match move ghost to start
     */
    abstract void captureByPlayer();//+200,+400,+800,+1600,+more for snake boss
    //Blinky, Pinky, Inky and Clyde ++Snake extra
}
