package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Key extends Collectible {
    public Bitmap Sprite;

    public Key(GameView gv){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//fix burring

        Sprite = (BitmapFactory.decodeResource(gv.getResources(), R.drawable.key, options));//load sprite
    }

    @Override
    void addToCounter() {

    }

    @Override
    void collectedByPlayer() {

    }
}
