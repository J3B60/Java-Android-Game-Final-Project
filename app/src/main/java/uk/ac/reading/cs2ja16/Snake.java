package uk.ac.reading.cs2ja16;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Random;

public class Snake extends Enemy {
    public int posy;//head ycoord
    public int posx;//head xcoord
    Bitmap snakeSprite;
    public ArrayList<Integer> posxTail = new ArrayList();
    public ArrayList<Integer> posyTail = new ArrayList();

    public ArrayList<Integer> direcOptions = new ArrayList();

    private Random rgen = new Random();

    public int direcHolder;

    public int[][] SpriteMapListCopy;


    /**
     * Load snake sprite used for all parts o f the body
     * @param gv
     */
    public Snake(GameView gv){
        BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = false;//fix blur
        snakeSprite = BitmapFactory.decodeResource(gv.getResources(), R.drawable.snake, options);//load sprite
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
     * Move simialrly to ghosts however uses arraylist of coords and not posx and posy. Snake can go through its body
     * which was not planned originonally but was done because of a snake error if the snake ends up in such a position that
     * it curls up with no possible way out which could cause an error or stop the snake from moving. After each move, each body part
     * takes the positon of its neighbour therefore progressing the snake.
     */
    @Override
    void Move() {
        boolean badmove = false;
        direcOptions.clear();
//        switch (direcHolder){
//            case 1:
//                if (posyTail.get(0) > 0){
//                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)+1+14] == 0) {//right
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0)+1 == posxTail.get(i)) && (posyTail.get(0) == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(2);
//                        }
//                    }
//                    if (SpriteMapListCopy[posyTail.get(0)-1][posxTail.get(0)+14] == 0){//up
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0) == posxTail.get(i)) && (posyTail.get(0)-1 == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(1);
//                        }
//                    }
//                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)-1+14] == 0) {//left
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0)-1 == posxTail.get(i)) && (posyTail.get(0) == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(4);
//                        }
//                    }
//                }
//                //else
//                break;
//            case 2:
//                if (posxTail.get(0)+1+14 < SpriteMapListCopy[posyTail.get(0)].length){
//                    if (SpriteMapListCopy[posyTail.get(0)-1][posxTail.get(0)+14] == 0){//up
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0) == posxTail.get(i)) && (posyTail.get(0)-1 == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(1);
//                        }
//                    }
//                    if (SpriteMapListCopy[posyTail.get(0)+1][posxTail.get(0)+14] == 0) {//down
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0) == posxTail.get(i)) && (posyTail.get(0)+1 == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(4);
//                        }
//                    }
//                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)+1+14] == 0) {//right
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0)+1 == posxTail.get(i)) && (posyTail.get(0) == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(2);
//                        }
//                    }
//                }
//                //else
//                break;
//            case 3:
//                if (posyTail.get(0)+1 < SpriteMapListCopy.length){
//                    if (SpriteMapListCopy[posyTail.get(0)+1][posxTail.get(0)+14] == 0) {//down
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0) == posxTail.get(i)) && (posyTail.get(0)+1 == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(4);
//                        }
//                    }
//                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)+1+14] == 0) {//right
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0)+1 == posxTail.get(i)) && (posyTail.get(0) == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(2);
//                        }
//                    }
//                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)-1+14] == 0) {//left
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0)-1 == posxTail.get(i)) && (posyTail.get(0) == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(4);
//                        }
//                    }
//                }
//                //else
//                break;
//            case 4:
//                if (posxTail.get(0)+14 > 0){
//                    if (SpriteMapListCopy[posyTail.get(0)-1][posxTail.get(0)+14] == 0){//up
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0) == posxTail.get(i)) && (posyTail.get(0)-1 == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(1);
//                        }
//                    }
//                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)-1+14] == 0) {//left
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0)-1 == posxTail.get(i)) && (posyTail.get(0) == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(4);
//                        }
//                    }
//                    if (SpriteMapListCopy[posyTail.get(0)+1][posxTail.get(0)+14] == 0) {//down
//                        badmove = false;
//                        for (int i = 0; i < posxTail.size(); i++){
//                            if(!((i > 4) && (posxTail.get(0) == posxTail.get(i)) && (posyTail.get(0)+1 == posyTail.get(i)))){
//                                badmove = true;
//                                break;
//                            }
//                        }
//                        if (!badmove){
//                            direcOptions.add(4);
//                        }
//                    }
//                }
//                //else
//                break;
//            default://No checks here!! make sure ghost does not start at x=0 or x=xmax or y=0 or y=ymax
//                if (SpriteMapListCopy[posyTail.get(0)-1][posxTail.get(0)+14] == 0){//up
//                    badmove = false;
//                    for (int i = 0; i < posxTail.size(); i++){
//                        if(!((i > 4) && (posxTail.get(0) == posxTail.get(i)) && (posyTail.get(0)-1 == posyTail.get(i)))){
//                            badmove = true;
//                            break;
//                        }
//                    }
//                    if (!badmove){
//                        direcOptions.add(1);
//                    }
//                }
//                if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)+1+14] == 0) {//right
//                    badmove = false;
//                    for (int i = 0; i < posxTail.size(); i++){
//                        if(!((i > 4) && (posxTail.get(0)+1 == posxTail.get(i)) && (posyTail.get(0) == posyTail.get(i)))){
//                            badmove = true;
//                            break;
//                        }
//                    }
//                    if (!badmove){
//                        direcOptions.add(2);
//                    }
//                }
//                if (SpriteMapListCopy[posyTail.get(0)+1][posxTail.get(0)+14] == 0) {//down
//                    badmove = false;
//                    for (int i = 0; i < posxTail.size(); i++){
//                        if(!((i > 4) && (posxTail.get(0) == posxTail.get(i)) && (posyTail.get(0)+1 == posyTail.get(i)))){
//                            badmove = true;
//                            break;
//                        }
//                    }
//                    if (!badmove){
//                        direcOptions.add(4);
//                    }
//                }
//                if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)-1+14] == 0) {//left
//                    badmove = false;
//                    for (int i = 0; i < posxTail.size(); i++){
//                        if(!((i > 4) && (posxTail.get(0)-1 == posxTail.get(i)) && (posyTail.get(0) == posyTail.get(i)))){
//                            badmove = true;
//                            break;
//                        }
//                    }
//                    if (!badmove){
//                        direcOptions.add(4);
//                    }
//                }
//                break;
//        }
        switch (direcHolder){
            case 1:
                if (posyTail.get(0) > 0){
                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)+1+14] == 0) {//right
                        direcOptions.add(2);
                    }
                    if (SpriteMapListCopy[posyTail.get(0)-1][posxTail.get(0)+14] == 0){//up
                        direcOptions.add(1);
                    }
                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)-1+14] == 0) {//left
                        direcOptions.add(4);
                    }
                }
                //else
                break;
            case 2:
                if (posxTail.get(0)+1+14 < SpriteMapListCopy[posyTail.get(0)].length){
                    if (SpriteMapListCopy[posyTail.get(0)-1][posxTail.get(0)+14] == 0){//up
                        direcOptions.add(1);
                    }
                    if (SpriteMapListCopy[posyTail.get(0)+1][posxTail.get(0)+14] == 0) {//down
                        direcOptions.add(3);
                    }
                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)+1+14] == 0) {//right
                        direcOptions.add(2);
                    }
                }
                //else
                break;
            case 3:
                if (posyTail.get(0)+1 < SpriteMapListCopy.length){
                    if (SpriteMapListCopy[posyTail.get(0)+1][posxTail.get(0)+14] == 0) {//down
                        direcOptions.add(3);
                    }
                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)+1+14] == 0) {//right
                        direcOptions.add(2);
                    }
                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)-1+14] == 0) {//left
                        direcOptions.add(4);
                    }
                }
                //else
                break;
            case 4:
                if (posxTail.get(0)+14 > 0){
                    if (SpriteMapListCopy[posyTail.get(0)-1][posxTail.get(0)+14] == 0){//up
                        direcOptions.add(1);
                    }
                    if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)-1+14] == 0) {//left
                        direcOptions.add(4);
                    }
                    if (SpriteMapListCopy[posyTail.get(0)+1][posxTail.get(0)+14] == 0) {//down
                        direcOptions.add(3);
                    }
                }
                //else
                break;
            default://No checks here!! make sure ghost does not start at x=0 or x=xmax or y=0 or y=ymax
                if (SpriteMapListCopy[posyTail.get(0)-1][posxTail.get(0)+14] == 0){//up
                    direcOptions.add(1);
                }
                if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)+1+14] == 0) {//right
                    direcOptions.add(2);
                }
                if (SpriteMapListCopy[posyTail.get(0)+1][posxTail.get(0)+14] == 0) {//down
                    direcOptions.add(3);
                }
                if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)-1+14] == 0) {//left
                    direcOptions.add(4);
                }
                break;
        }

        if (direcOptions.size() == 1){
            direcHolder = direcOptions.get(0);
        }
        else if (direcOptions.size() == 0){//catch error
        }
        else{
            direcHolder = direcOptions.get(rgen.nextInt(direcOptions.size()));//1-4
        }
        ////////////////////////////////
        if (direcHolder == 1){//UP
//            posxTail.get(0) = posxx % 8;
//            posyTail.get(0) = posyy % 8;
            if (posyTail.get(0) <= 0){//WRAP
                posyTail.set(0, 31);
            }
            if (SpriteMapListCopy[posyTail.get(0)-1][posxTail.get(0)+14] == 0){
                //posyy--;//Not enough time to do detailed movements
                posyTail.set(0, posyTail.get(0)-1);
                for (int i = posxTail.size()-1; i > 0 ; i--){
                    posxTail.set(i,posxTail.get(i -1));
                    posyTail.set(i,posyTail.get(i -1));
                }
            }
        }
        else if (direcHolder == 2){//RIGHT
//            posxTail.get(0) = posxx % 8;
//            posyTail.get(0) = posyy % 8;
            if (posxTail.get(0) >= 13){//WARP
                posxTail.set(0,-14);
            }
            if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)+1+14] == 0){
//                posxx++;
                posxTail.set(0, posxTail.get(0)+1);
                for (int i = posxTail.size()-1; i > 0 ; i--){
                    posxTail.set(i,posxTail.get(i -1));
                    posyTail.set(i,posyTail.get(i -1));
                }
            }
        }
        else if (direcHolder == 3){//DOWN
//            posxTail.get(0) = posxx % 8;
//            posyTail.get(0) = posyy % 8;
            if (posyTail.get(0) >= 31){//Warp
                posyTail.set(0,0);
            }
            if (SpriteMapListCopy[posyTail.get(0)+1][posxTail.get(0)+14] == 0){
//                posyy++;
                posyTail.set(0, posyTail.get(0)+1);
                for (int i = posxTail.size()-1; i > 0 ; i--){
                    posxTail.set(i,posxTail.get(i -1));
                    posyTail.set(i,posyTail.get(i -1));
                }
            }
        }
        else if (direcHolder == 4){//LEFT
//            posxTail.get(0) = posxx % 8;
//            posyTail.get(0) = posyy % 8;
            if (posxTail.get(0) <= -14){//WARP
                posxTail.set(0,14);
            }
            if (SpriteMapListCopy[posyTail.get(0)][posxTail.get(0)-1+14] == 0){
//                posxx--;
                posxTail.set(0, posxTail.get(0)-1);
                for (int i = posxTail.size()-1; i > 0 ; i--){
                    posxTail.set(i,posxTail.get(i -1));
                    posyTail.set(i,posyTail.get(i -1));
                }
            }
        }
        //else nothing happens - Initial
    }
}
