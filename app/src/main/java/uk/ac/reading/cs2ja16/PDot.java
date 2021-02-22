package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PDot extends Collectible {
    public void incrementPDotCounter(){};
    public Bitmap Sprite;
    public int posx;
    public int posy;

    public PDot(GameView gv, int x, int y){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//remove blur

        Sprite = (BitmapFactory.decodeResource(gv.getResources(), R.drawable.pacdot, options));//load sprite

        posx = x;// set the x coord
        posy = y;//set the y coord
    }

    @Override
    void addToCounter() {
        //+10
    }

    @Override
    void collectedByPlayer() {

    }
}
