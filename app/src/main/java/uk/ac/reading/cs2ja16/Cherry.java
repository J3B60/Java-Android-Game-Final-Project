package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Cherry extends Collectible {
    public Bitmap Sprite;

    public Cherry(GameView gv){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//fix scaling blur

        Sprite = (BitmapFactory.decodeResource(gv.getResources(), R.drawable.cherry, options));//load sprite
    }

    @Override
    void addToCounter() {

    }

    @Override
    void collectedByPlayer() {

    }
}
