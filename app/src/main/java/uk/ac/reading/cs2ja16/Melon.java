package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Melon extends Collectible {
    public Bitmap Sprite;

    public Melon(GameView gv){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//fix blur

        Sprite = (BitmapFactory.decodeResource(gv.getResources(), R.drawable.melon, options));//load sprite
    }

    @Override
    void addToCounter() {

    }

    @Override
    void collectedByPlayer() {

    }
}
