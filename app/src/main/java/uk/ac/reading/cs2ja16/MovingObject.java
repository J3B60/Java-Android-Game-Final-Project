package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;

import java.util.ArrayList;

public abstract class MovingObject extends GameObject {
    ArrayList<Bitmap> Sprite;//List of sprites for objecy
    private int frameCount;
    public int coinCounter;
    public int speed;
    int posx;//x coord
    int posy;//y coord
    abstract void Move();//move to a coord/change current coords by an amount
}
