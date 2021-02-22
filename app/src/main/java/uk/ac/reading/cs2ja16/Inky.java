package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Random;

public class Inky extends Enemy {
    public ArrayList<Bitmap> Sprite;
    public int frameCount = 0;
    public int posx;//x coord
    public int posy;//y coord
    public int posyy;
    public int posxx;
    public int direcHolder;//current direction

    public int boxMove = 1;//start in box

    private int boxBounce = 0;//box movement repetitions

    boolean weak = false;//captured by pacman
    boolean dead = false;

    ArrayList<Integer> direcOptions = new ArrayList<>();//list of choices for ghost to pick at random

    Random rgen = new Random();//For random directions

    public int[][] SpriteMapListCopy;//copy for reference to resources

    /**
     * Load Inky Sprites
     * @param gv
     */
    public Inky(GameView gv){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Sprite = new ArrayList<Bitmap>();
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.inkyframe1down, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.inkyframe1left, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.inkyframe1right, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.inkyframe1up, options));
        //////
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.inkyframe2down, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.inkyframe2left, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.inkyframe2right, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.inkyframe2up, options));
        //
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.deadframedown, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.deadframeleft, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.deadframeright, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.deadframeup, options));
        //////
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.catchframe1, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.catchframe2, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.catchframe3, options));
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.catchframe4, options));
    }

    @Override
    void randomMove() {

    }

    @Override
    void capturePlayer() {

    }

    @Override
    void captureByPlayer() {

    }

    /**
     * All of Inky's Movements (All ghosts are similar See Blinky for more info)
     */
    @Override
    void Move() {
        if (boxMove != 0){
            switch (boxMove){
                case 1:
                case 3:
                    posx = -3;
                    posy = 14;
                    break;
                case 2:
                    posx = -3;
                    posy = 15;
                    break;
                case 4:
                    posx = -3;
                    posy = 13;
                    break;

            }
            boxMove++;
            if (boxMove == 5 && boxBounce == 16){
                posx = -1;
                posy = 12;
            }
            else if (boxMove == 6 && boxBounce == 16){
                boxMove = 0;
                boxBounce = 0;
                posx = -1;
                posy = 11;
            }
            else if (boxMove > 4 && boxBounce != 16){
                boxMove = 1;
                boxBounce++;
            }
        }
        else {
            direcOptions.clear();
            switch (direcHolder){
                case 1:
                    if (posy > 0){
                        if (SpriteMapListCopy[posy][posx-1+14] == 0) {//left
                            direcOptions.add(4);
                        }
                        if (SpriteMapListCopy[posy][posx+1+14] == 0) {//right
                            direcOptions.add(2);
                        }
                        if (SpriteMapListCopy[posy-1][posx+14] == 0){//up
                            direcOptions.add(1);
                        }

                    }
                    else {
                        direcOptions.add(1);
                    }
                    break;
                case 2:
                    if (posx+1+14 < SpriteMapListCopy[posy].length){
                        if (SpriteMapListCopy[posy+1][posx+14] == 0) {//down
                            direcOptions.add(3);
                        }
                        if (SpriteMapListCopy[posy-1][posx+14] == 0){//up
                            direcOptions.add(1);
                        }
                        if (SpriteMapListCopy[posy][posx+1+14] == 0) {//right
                            direcOptions.add(2);
                        }
                    }
                    else{
                        direcOptions.add(2);
                    }
                    break;
                case 3:
                    if (posy+1 < SpriteMapListCopy.length){
                        if (SpriteMapListCopy[posy+1][posx+14] == 0) {//down
                            direcOptions.add(3);
                        }
                        if (SpriteMapListCopy[posy][posx-1+14] == 0) {//left
                            direcOptions.add(4);
                        }
                        if (SpriteMapListCopy[posy][posx+1+14] == 0) {//right
                            direcOptions.add(2);
                        }
                    }
                    else {
                        direcOptions.add(3);
                    }
                    break;
                case 4:
                    if (posx+14 > 0){
                        if (SpriteMapListCopy[posy][posx-1+14] == 0) {//left
                            direcOptions.add(4);
                        }
                        if (SpriteMapListCopy[posy+1][posx+14] == 0) {//down
                            direcOptions.add(3);
                        }
                        if (SpriteMapListCopy[posy-1][posx+14] == 0){//up
                            direcOptions.add(1);
                        }
                    }
                    else{
                        direcOptions.add(4);
                    }
                    break;
                default://No checks here!! make sure ghost does not start at x=0 or x=xmax or y=0 or y=ymax
                    if (SpriteMapListCopy[posy-1][posx+14] == 0){//up
                        direcOptions.add(1);
                    }
                    if (SpriteMapListCopy[posy][posx+1+14] == 0) {//right
                        direcOptions.add(2);
                    }
                    if (SpriteMapListCopy[posy+1][posx+14] == 0) {//down
                        direcOptions.add(3);
                    }
                    if (SpriteMapListCopy[posy][posx-1+14] == 0) {//left
                        direcOptions.add(4);
                    }
                    break;
            }

            if (direcOptions.size() == 1){
                direcHolder = direcOptions.get(0);
            }
            else if (direcOptions.size() == 0){//catch snake error

            }
            else{
                direcHolder = direcOptions.get(rgen.nextInt(direcOptions.size()));//1-4
            }
            ////////////////////////////////
            if (direcHolder == 1){//UP
//            posx = posxx % 8;
//            posy = posyy % 8;
                if (posy <= 0){//WRAP
                    posy = 31;
                }
                if (SpriteMapListCopy[posy-1][posx+14] == 0){
                    //posyy--;//Not enough time to do detailed movements
                    posy--;
                    if (!weak && !dead){
                        if (frameCount != 3){
                            frameCount = 3;
                        }
                        else if (frameCount != 7){
                            frameCount = 7;
                        }
                    }
                    else if (weak && dead){//weak since pacman can go through them else if it were just dead it could still catch pacman
                        if (frameCount != 11){
                            frameCount = 11;
                        }
                    }
                    else if (weak){
                        if (frameCount != 12){
                            frameCount = 12;
                        }
                        else if (frameCount != 13){
                            frameCount = 13;
                        }
                        //NEED TIME RUNNING OUT FLASHING ANIMATION
                    }
                }
            }
            else if (direcHolder == 2){//RIGHT
//            posx = posxx % 8;
//            posy = posyy % 8;
                if (posx >= 13){//WARP
                    posx = -14;
                }
                if (SpriteMapListCopy[posy][posx+1+14] == 0){
//                posxx++;
                    posx++;
                    if (!weak && !dead){
                        if (frameCount != 2){
                            frameCount = 2;
                        }
                        else if (frameCount != 6){
                            frameCount = 6;
                        }
                    }
                    else if (weak && dead){//weak since pacman can go through them else if it were just dead it could still catch pacman
                        if (frameCount != 10){
                            frameCount = 10;
                        }
                    }
                    else if (weak){
                        if (frameCount != 12){
                            frameCount = 12;
                        }
                        else if (frameCount != 13){
                            frameCount = 13;
                        }
                        //NEED TIME RUNNING OUT FLASHING ANIMATION
                    }
                }
            }
            else if (direcHolder == 3){//DOWN
//            posx = posxx % 8;
//            posy = posyy % 8;
                if (posy >= 30){//Warp
                    posy = 0;
                }
                if (SpriteMapListCopy[posy+1][posx+14] == 0){
//                posyy++;
                    posy++;
                    if (!weak && !dead){
                        if (frameCount != 0){
                            frameCount = 0;
                        }
                        else if (frameCount != 4){
                            frameCount = 4;
                        }
                    }
                    else if (weak && dead){//weak since pacman can go through them else if it were just dead it could still catch pacman
                        if (frameCount != 8){
                            frameCount = 8;
                        }
                    }
                    else if (weak){
                        if (frameCount != 12){
                            frameCount = 12;
                        }
                        else if (frameCount != 13){
                            frameCount = 13;
                        }
                        //NEED TIME RUNNING OUT FLASHING ANIMATION
                    }
                }
            }
            else if (direcHolder == 4){//LEFT
//            posx = posxx % 8;
//            posy = posyy % 8;
                if (posx <= -14){//WARP
                    posx = 14;
                }
                if (SpriteMapListCopy[posy][posx-1+14] == 0){
//                posxx--;
                    posx--;
                    if (!weak && !dead){
                        if (frameCount != 1){
                            frameCount = 1;
                        }
                        else if (frameCount != 5){
                            frameCount = 5;
                        }
                    }
                    else if (weak && dead){//weak since pacman can go through them else if it were just dead it could still catch pacman
                        if (frameCount != 9){
                            frameCount = 9;
                        }
                    }
                    else if (weak){
                        if (frameCount != 12){
                            frameCount = 12;
                        }
                        else if (frameCount != 13){
                            frameCount = 13;
                        }
                        //NEED TIME RUNNING OUT FLASHING ANIMATION
                    }
                }
            }
            //else nothing happens - Initial
        }
    }
}
