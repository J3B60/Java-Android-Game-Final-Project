package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bell extends Collectible {
    public Bitmap Sprite;

    public Bell(GameView gv){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//fix scaling blur

        Sprite = (BitmapFactory.decodeResource(gv.getResources(), R.drawable.bell, options));//load sprite
    }

    @Override
    void addToCounter() {

    }

    @Override
    void collectedByPlayer() {

    }
}
