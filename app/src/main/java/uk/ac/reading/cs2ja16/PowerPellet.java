package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PowerPellet extends Collectible {
    public Bitmap Sprite;//Array list needed for flashing animation
    public int posx;//x coord
    public int posy;//y coord

    public PowerPellet(GameView gv, int x, int y){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//fix blurring

        Sprite = (BitmapFactory.decodeResource(gv.getResources(), R.drawable.powerpellet, options));//load sprite

        posx = x;//set x
        posy = y;//set y
    }

    @Override
    void collectedByPlayer() {

    }

    @Override
    void addToCounter() {

    }
}
