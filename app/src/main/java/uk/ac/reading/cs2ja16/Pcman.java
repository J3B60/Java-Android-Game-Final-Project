package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class Pcman extends MovingObject {
    ArrayList<Bitmap> Sprite;
    public int frameCount;//Current Frame used to fetch from Sprite Arraylist
    public int coinCounter;
    public int speed;
    public int posx;//xcoord
    public int posy;//ycoord
    public int posxx;
    public int posyy;
    public int direcHolder = 0;//1-Up, 2-Right, 3-Down, 4-Left, 0 - None/Initial/Start
    public int direcStack = 0;//0= no change, else change direction
    public int activePowerUp = 0;//powerup numbers. Note powerpellets are 1, others are 2+

    public int[][] SpriteMapListCopy;

    /**
     * Load all of Pacma's Sprites
     * @param gv
     */
    public Pcman(GameView gv){
        frameCount = 0;
        Sprite = new ArrayList<Bitmap>();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcframe0all, options));           //0
        /////
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe1down, options));       //1
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe1left, options));       //2
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcframe1right, options));         //3
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe1up, options));         //4
        /////
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe2down, options));       //5
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe2left, options));       //6
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe2right, options));      //7
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe2up, options));         //8
        /////
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe1death, options));      //9
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe2death, options));      //10
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe3death, options));      //11
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe4death, options));      //12
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe5death, options));      //13
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe6death, options));      //14
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe7death, options));      //15
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe8death, options));      //16
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe9death, options));      //17
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe10death, options));     //18
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pcmanframe11death, options));     //19
    }

    //Player Controls

    /**
     * Player Controls. This handles all of Pacmans movements including the holding of the requested direction
     * just like the original arcade game until the requested direction has been completed. Wrap around the arena
     * if beyond the boundaries and handle the correct frame for the direction of travel
     */
    @Override
    public void Move() {//Sprite number list
        ////////////////////////////////////
        ////Change direction
        switch(direcStack){
            case 1:
                if (SpriteMapListCopy[posy-1][posx+14] == 0){
                    direcHolder = direcStack;
                    direcStack = 0;
                }
                break;
            case 2:
                if (SpriteMapListCopy[posy][posx+1+14] == 0){
                    direcHolder = direcStack;
                    direcStack = 0;
                }
                break;
            case 3:
                if (SpriteMapListCopy[posy+1][posx+14] == 0){
                    direcHolder = direcStack;
                    direcStack = 0;
                }
                break;
            case 4:
                if (SpriteMapListCopy[posy][posx-1+14] == 0){
                    direcHolder = direcStack;
                    direcStack = 0;
                }
                break;

        }
        //else no change in direction
        ////////////////////////////////////

        ///////////////////////Travel in direction
        if (direcHolder == 1){//UP
//            posx = posxx % 8;
//            posy = posyy % 8;
            if (posy <= 0){//WRAP
                posy = 31;
            }
            if (SpriteMapListCopy[posy-1][posx+14] == 0){
                //posyy--;//Not enough time to do detailed movements
                posy--;
                if (frameCount == 0){
                    frameCount = 4;
                }
                else if (frameCount == 1 || frameCount == 2 || frameCount == 3 || frameCount == 4){
                    frameCount = 8;
                }
                else if (frameCount == 5 || frameCount == 6 || frameCount == 7 || frameCount == 8){
                    frameCount = 0;
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
                if (frameCount == 0){
                    frameCount = 3;
                }
                else if (frameCount == 1 || frameCount == 2 || frameCount == 3 || frameCount == 4){
                    frameCount = 7;
                }
                else if (frameCount == 5 || frameCount == 6 || frameCount == 7 || frameCount == 8){
                    frameCount = 0;
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
                if (frameCount == 0){
                    frameCount = 1;
                }
                else if (frameCount == 1 || frameCount == 2 || frameCount == 3 || frameCount == 4){
                    frameCount = 5;
                }
                else if (frameCount == 5 || frameCount == 6 || frameCount == 7 || frameCount == 8){
                    frameCount = 0;
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
                if (frameCount == 0){
                    frameCount = 2;
                }
                else if (frameCount == 1 || frameCount == 2 || frameCount == 3 || frameCount == 4){
                    frameCount = 6;
                }
                else if (frameCount == 5 || frameCount == 6 || frameCount == 7 || frameCount == 8){
                    frameCount = 0;
                }
            }
        }
        //else nothing happens - Initial
    }


}
