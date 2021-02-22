package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Peach extends Collectible {
    public Bitmap Sprite;

    public Peach(GameView gv){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//remove blur

        Sprite = (BitmapFactory.decodeResource(gv.getResources(), R.drawable.peach, options));//load sprite
    }

    @Override
    void addToCounter() {

    }

    @Override
    void collectedByPlayer() {

    }
}
