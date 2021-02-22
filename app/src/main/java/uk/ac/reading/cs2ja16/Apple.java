package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Apple extends Collectible {
    public Bitmap Sprite;

    public Apple(GameView gv){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//scaling blur fix

        Sprite = (BitmapFactory.decodeResource(gv.getResources(), R.drawable.apple, options));//load sprite
    }

    @Override
    void addToCounter() {

    }

    @Override
    void collectedByPlayer() {

    }
}
