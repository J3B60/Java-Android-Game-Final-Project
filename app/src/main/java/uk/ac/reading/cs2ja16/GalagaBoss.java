package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GalagaBoss extends Collectible {
    public Bitmap Sprite;

    public GalagaBoss(GameView gv){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//scaling fix

        Sprite = (BitmapFactory.decodeResource(gv.getResources(), R.drawable.galagaboss, options));//load sprite
    }

    @Override
    void addToCounter() {

    }

    @Override
    void collectedByPlayer() {

    }
}
