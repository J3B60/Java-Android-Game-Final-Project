package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Random;

public class Blinky extends Enemy {
    public ArrayList<Bitmap> Sprite;//load all sprites
    public int frameCount = 0;//sprite frame
    public int posx;//xcoord
    public int posy;//y coord
    public int posyy;//Not used
    public int posxx;//Not used
    public int direcHolder;//Current direction

    public int boxMove = 0;//track movement in box

    private int boxBounce;//track movement in box repetitions

    boolean weak = false;//track if pacman power pellet active
    boolean dead = false;

    ArrayList<Integer> direcOptions = new ArrayList<>();//list of possible choices for random movement

    Random rgen = new Random();//random generator for movement

    public int[][] SpriteMapListCopy;//copy of playarena sprite list after loading
/**
 *Load all of Blinky's Sprites with scaling blur removed
 */
    public Blinky(GameView gv){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//fisx scaling quality

        Sprite = new ArrayList<Bitmap>();

        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.blinkyframe1down, options));//0
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.blinkyframe1left, options));//1
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.blinkyframe1right, options));//2
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.blinkyframe1up, options));//3
        //////
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.blinkyframe2down, options));//4
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.blinkyframe2left, options));//5
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.blinkyframe2right, options));//6
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.blinkyframe2up, options));//7
        //////
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.deadframedown, options));//8
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.deadframeleft, options));//9
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.deadframeright, options));//10
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.deadframeup, options));//11
        //////
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.catchframe1, options));//12
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.catchframe2, options));//13
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.catchframe3, options));//14
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.catchframe4, options));//15
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
     * Handles all of Blinky's movements. When within the Ghost box move up and down and repeat for 8 times. When
     * not in the box then look at possible options and make a list, choose one at random. Next move in that direction
     * including warp and changing frames depending on direction
     */
    @Override
    void Move() {
        if (boxMove != 0){
            switch (boxMove){//box pos movements
                case 1:
                    posx = -1;
                    posy = 15;
                    break;
                case 2:
                case 4:
                    posx = -1;
                    posy = 14;
                    break;
                case 3:
                case 5:
                    posx = -1;
                    posy = 13;
                    break;
            }
            boxMove++;//next pos
            if (boxMove == 6 && boxBounce == 8){//begin exiting box
                posx = -1;
                posy = 12;
            }
            else if (boxMove == 7 && boxBounce == 8){//finish
                boxMove = 0;
                boxBounce = 0;
                posx = -1;
                posy = 11;
            }
            else if (boxMove > 5 && boxBounce != 8){//box repetitions
                boxMove = 1;
                boxBounce++;
            }
        }
        else {
            direcOptions.clear();
            switch (direcHolder){
                case 1:
                    if (posy > 0){
                        if (SpriteMapListCopy[posy][posx+1+14] == 0) {//right
                            direcOptions.add(2);
                        }
                        if (SpriteMapListCopy[posy-1][posx+14] == 0){//up
                            direcOptions.add(1);
                        }
                        if (SpriteMapListCopy[posy][posx-1+14] == 0) {//left
                            direcOptions.add(4);
                        }
                    }
                    else {
                        direcOptions.add(1);
                    }
                    break;
                case 2:
                    if (posx+1+14 < SpriteMapListCopy[posy].length){
                        if (SpriteMapListCopy[posy-1][posx+14] == 0){//up
                            direcOptions.add(1);
                        }
                        if (SpriteMapListCopy[posy+1][posx+14] == 0) {//down
                            direcOptions.add(3);
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
                        if (SpriteMapListCopy[posy][posx+1+14] == 0) {//right
                            direcOptions.add(2);
                        }
                        if (SpriteMapListCopy[posy][posx-1+14] == 0) {//left
                            direcOptions.add(4);
                        }
                    }
                    else {
                        direcOptions.add(3);
                    }
                    break;
                case 4:
                    if (posx+14 > 0){
                        if (SpriteMapListCopy[posy-1][posx+14] == 0){//up
                            direcOptions.add(1);
                        }
                        if (SpriteMapListCopy[posy][posx-1+14] == 0) {//left
                            direcOptions.add(4);
                        }
                        if (SpriteMapListCopy[posy+1][posx+14] == 0) {//down
                            direcOptions.add(3);
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
                direcHolder = direcOptions.get(0);//random.nextInt(0) gives errors
            }
            else if (direcOptions.size() ==0){//catch the no moves error (same idea as snake error)

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
                if (posy >= SpriteMapListCopy.length-1){//Warp
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
