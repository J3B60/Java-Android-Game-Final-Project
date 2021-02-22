package uk.ac.reading.cs2ja16;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.SoundPool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayArenaSnake {
    //public int[][]  GameMap = new int[224][248];//28x31 (each tile is 8 pixels)
    //public char[][] ArenaMap = new char[29][32];
    public int mCanvasWidth;
    public int mCanvasHeight;

    public int itemPowerupPosx;
    public int itemPowerupPosy;

    ArrayList<Bitmap> Sprite;
    public int[][] SpriteNumberList = new int[31][28];//This has been changed from 29 to 28 may cause issues
    public ArrayList<PDot> pDotList = new ArrayList<>();
    public ArrayList<PowerPellet> powerPelletsList = new ArrayList<>();

    int pdotsfx;
    int pacdeathsfx;
    int pacslowsfx;
    int pacfastsfx;

    SoundPool menusfx;

    //public char[][] levelOne = {{'O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O',},{'O','P','P','P','P','P','P','P','P','P','P','P','P','W','W','P','P','P','P','P','P','P','P','P','P','P','P','O',},{'O','P','W','W','W','W','P','W','W','W','W','W','P','W','W','P','W','W','W','W','W','P','W','W','W','W','P','O',},{'O','Q','W','W','W','W','P','W','W','W','W','W','P','W','W','P','W','W','W','W','W','P','W','W','W','W','Q','O',},{'O','P','W','W','W','W','P','W','W','W','W','W','P','W','W','P','W','W','W','W','W','P','W','W','W','W','P','O',},{'O','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','O',},{'O','P','W','W','W','W','P','W','W','P','W','W','W','W','W','W','W','W','P','W','W','P','W','W','W','W','P','O',},{'O','P','W','W','W','W','P','W','W','P','W','W','W','W','W','W','W','W','P','W','W','P','W','W','W','W','P','O',},{'O','P','P','P','P','P','P','W','W','P','P','P','P','W','W','P','P','P','P','W','W','P','P','P','P','P','P','O',},{'O','O','O','O','O','O','P','W','W','W','W','W','E','W','W','E','W','W','W','W','W','P','O','O','O','O','O','O',},{'O','O','O','O','O','O','P','W','W','W','W','W','E','W','W','E','W','W','W','W','W','P','O','O','O','O','O','O',},{'O','O','O','O','O','O','P','W','W','E','E','E','E','G','G','E','E','E','E','W','W','P','O','O','O','O','O','O',},{'O','O','O','O','O','O','P','W','W','E','B','B','B','B','B','B','B','B','E','W','W','P','O','O','O','O','O','O',},{'O','O','O','O','O','O','P','W','W','E','B','B','B','B','B','B','B','B','E','W','W','P','O','O','O','O','O','O',},{'X','E','E','E','E','E','P','E','E','E','B','B','B','B','B','B','B','B','E','E','E','P','E','E','E','E','E','X',}, {'O','O','O','O','O','O','P','W','W','E','B','B','B','B','B','B','B','B','E','W','W','P','O','O','O','O','O','O',},{'O','O','O','O','O','O','P','W','W','E','B','B','B','B','B','B','B','B','E','W','W','P','O','O','O','O','O','O',},{'O','O','O','O','O','O','P','W','W','E','E','E','E','E','E','E','E','E','E','W','W','P','O','O','O','O','O','O',},{'O','O','O','O','O','O','P','W','W','E','W','W','W','W','W','W','W','W','E','W','W','P','O','O','O','O','O','O',},{'O','O','O','O','O','O','P','W','W','E','W','W','W','W','W','W','W','W','E','W','W','P','O','O','O','O','O','O',},{'O','P','P','P','P','P','P','P','P','P','P','P','P','W','W','P','P','P','P','P','P','P','P','P','P','P','P','O',},{'O','P','W','W','W','W','P','W','W','W','W','W','P','W','W','P','W','W','W','W','W','P','W','W','W','W','P','O',},{'O','P','W','W','W','W','P','W','W','W','W','W','P','W','W','P','W','W','W','W','W','P','W','W','W','W','P','O',},{'O','Q','P','P','W','W','P','P','P','P','P','P','P','S','S','P','P','P','P','P','P','P','W','W','P','P','Q','O',},{'O','W','W','P','W','W','P','W','W','P','W','W','W','W','W','W','W','W','P','W','W','P','W','W','P','W','W','O',},{'O','W','W','P','W','W','P','W','W','P','W','W','W','W','W','W','W','W','P','W','W','P','W','W','P','W','W','O',},{'O','P','P','P','P','P','P','W','W','P','P','P','P','W','W','P','P','P','P','W','W','P','P','P','P','P','P','O',},{'O','P','W','W','W','W','W','W','W','W','W','W','P','W','W','P','W','W','W','W','W','W','W','W','W','W','P','O',},{'O','P','W','W','W','W','W','W','W','W','W','W','P','W','W','P','W','W','W','W','W','W','W','W','W','W','P','O',},{'O','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','O',},{'O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O',}};

    private GameView copy;

    public Pcman pacman;
    public Snake snake;
    /**
     * load all sprites, music and create objects of the moving objects
     * @param gv
     */
    public PlayArenaSnake(GameView gv){
        //Load all sprites ready for use
        copy = gv;//NOTE SHOULD BE FIND ONLY NEED IT FOR A FILE PATH
        Sprite = new ArrayList<Bitmap>();


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.blank, options));                  //0
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.bigbottomleft, options));          //1
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.bigbottomright, options));         //2
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.bigtopleft, options));             //3
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.bigtopright, options));            //4
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.bottomleft, options));             //5
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.bottomright, options));            //6
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.boxbottomleft, options));          //7
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.boxbottomright, options));         //8
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.boxtopleft, options));             //9
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.boxtopright, options));            //10
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.hardbottomleft, options));         //11
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.hardbottomright, options));        //12
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.hardupperleft, options));          //13
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.hardupperright, options));         //14
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.horizontal, options));             //15
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.innerhorizontal, options));        //16
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outerbottomleft, options));        //17
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outerbottomright, options));       //18
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outerdowntleft, options));         //19
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outerdowntright, options));        //20
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outerlowerhorizontal, options));   //21
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outertleft, options));             //22
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outertopleft, options));           //23
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outertopright, options));          //24
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outertright, options));            //25
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outerupperhorizontal, options));   //26
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outeruppertleft, options));        //27
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outeruppertright, options));       //28
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outerverleft, options));           //29
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.outerverright, options));          //30
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.pacdot, options));                 //31
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.powerpellet, options));            //32
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.powerpelletsmall, options));       //33
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.topleft, options));                //34
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.topright, options));               //35
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.uleft, options));                  //36
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.uright, options));                 //37
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.verticalleft, options));           //38
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.verticalright, options));          //39
        //BONUS
        Sprite.add(BitmapFactory.decodeResource(gv.getResources(), R.drawable.bonus, options));          //40

        pacman = new Pcman(gv);
        snake = new Snake(gv);

        SoundPool.Builder tmpbuild = new SoundPool.Builder();
        tmpbuild.setMaxStreams(10);
        menusfx = tmpbuild.build();

        pdotsfx = menusfx.load(gv.getContext(),R.raw.dot,1);
        pacdeathsfx = menusfx.load(gv.getContext(),R.raw.death,1);
        pacslowsfx = menusfx.load(gv.getContext(),R.raw.slow,1);
        pacfastsfx = menusfx.load(gv.getContext(),R.raw.fast,1);

    }
    /**
     * read char map and set the right sprites for the arena. Set positions of the box, snake(uses same G as Blinky), pellets, pdots, pacman and give
     * a copy of the resulting spritenumberMap to the moving objects
     * @param levelcharArray
     */
    public void newMapLoad(char[][] levelcharArray){
        boolean rightNeighbour;
        boolean leftNeighbour ;
        boolean aboveNeighbour;
        boolean belowNeighbour;
        for (int i = 0; i < levelcharArray[0].length; i++){//column (x)
            for (int j = 0; j< levelcharArray.length; j ++){//row (y)
                if (levelcharArray[j][i] == 'W'){
                    //needs to find neighbours to find type
                    rightNeighbour = false;
                    leftNeighbour = false;
                    aboveNeighbour = false;
                    belowNeighbour = false;
                    if (levelcharArray[j-1][i] == 'W'){
                        aboveNeighbour = true;
                    }
                    //else it stays false

                    if (levelcharArray[j+1][i] == 'W'){
                        belowNeighbour = true;
                    }
                    //else it stays false
                    if (levelcharArray[j][i-1] == 'W'){
                        leftNeighbour = true;
                    }
                    //else it stays false
                    if (levelcharArray[j][i+1] == 'W'){
                        rightNeighbour = true;
                    }
                    //else it stays false
                    if (aboveNeighbour && rightNeighbour && belowNeighbour && leftNeighbour){
                        ///Diagonal checks required
                        boolean topleft = false;
                        boolean topright = false;
                        boolean bottomleft = false;
                        boolean bottomright = false;
                        //////CHECKS
                        if (levelcharArray[j-1][i-1] == 'W'){
                            topleft = true;

                        }
                        if (levelcharArray[j-1][i+1] == 'W'){
                            topright = true;
                        }
                        if (levelcharArray[j+1][i-1] == 'W'){
                            bottomleft = true;
                        }
                        if (levelcharArray[j+1][i+1] == 'W'){
                            bottomright = true;
                        }
                        ////////////WORK
                        if (topleft && topright && bottomleft && bottomright){//EMPTY
                            SpriteNumberList[j][i] = 0;
                        }
                        else if (!topleft && topright && bottomleft && bottomright){//big topleft arc
                            SpriteNumberList[j][i] = 3;
                        }
                        else if (topleft && !topright && bottomleft && bottomright){//big topright arc
                            SpriteNumberList[j][i] = 4;
                        }
                        else if (topleft && topright && !bottomleft && bottomright){//big bottomleft arc
                            SpriteNumberList[j][i] = 1;
                        }
                        else if (topleft && topright && bottomleft && !bottomright){//big bottomright arc
                            SpriteNumberList[j][i] = 2;
                        }
                    }
                    //SMALL ARCS
                    else if (!(aboveNeighbour) && rightNeighbour && belowNeighbour && !(leftNeighbour)){//small bottom right arc
                        SpriteNumberList[j][i] = 6;
                    }
                    else if (aboveNeighbour && rightNeighbour && !(belowNeighbour) && !(leftNeighbour)){//small top right arc
                        SpriteNumberList[j][i] = 35;
                    }
                    else if (!(aboveNeighbour) && !(rightNeighbour) && belowNeighbour && leftNeighbour){//small bottomleft arc
                        SpriteNumberList[j][i] = 5;
                    }
                    else if (aboveNeighbour && !(rightNeighbour) && !(belowNeighbour) && leftNeighbour){//small topleft arc
                        SpriteNumberList[j][i] = 34;
                    }
                    //EDGES
                    else if (aboveNeighbour && !(rightNeighbour) && belowNeighbour && leftNeighbour){//left vertical edge
                        SpriteNumberList[j][i] = 38;
                    }
                    else if (aboveNeighbour && rightNeighbour && belowNeighbour && !(leftNeighbour)){//right vertical edge
                        SpriteNumberList[j][i] = 39;
                    }
                    else if (aboveNeighbour && rightNeighbour && !(belowNeighbour) && leftNeighbour){//top horizontal edge
                        SpriteNumberList[j][i] = 16;
                    }
                    else if (!(aboveNeighbour) && rightNeighbour && belowNeighbour && leftNeighbour){//bottom horizontal edge
                        SpriteNumberList[j][i] = 15;
                    }
                }
                else if (levelcharArray[j][i] == 'O'){
                    SpriteNumberList[j][i] = 40;
                }
                else if (levelcharArray[j][i] == 'P'){
                    pDotList.add(new PDot(copy, i, j));
                    SpriteNumberList[j][i] = 0;
                }
                else if (levelcharArray[j][i] == 'E' || levelcharArray[j][i] == 'X' || levelcharArray[j][i] == 'Y' || levelcharArray[j][i] == '1' || levelcharArray[j][i] == '2' || levelcharArray[j][i] == '3' || levelcharArray[j][i] == '4' || levelcharArray[j][i] == '5' || levelcharArray[j][i] == '6' || levelcharArray[j][i] == '7' || levelcharArray[j][i] == '8' || levelcharArray[j][i] == '9' || levelcharArray[j][i] == 'Z'){
                    SpriteNumberList[j][i] = 0;
                }
                else if (levelcharArray[j][i] == 'B'){
                    if (levelcharArray[j-1][i] == 'B' && levelcharArray[j+1][i] == 'B' && levelcharArray[j][i-1] == 'B' && levelcharArray[j][i+1] == 'B'){//Surrounded by B's
                        SpriteNumberList[j][i] = 0;
                    }
                    else if (levelcharArray[j-1][i] != 'B' && levelcharArray[j][i+1] != 'B'){//
                        SpriteNumberList[j][i] = 7;
                    }
                    else if (levelcharArray[j+1][i] != 'B' && levelcharArray[j][i+1] != 'B'){
                        SpriteNumberList[j][i] = 9;
                    }
                    else if (levelcharArray[j-1][i] != 'B' && levelcharArray[j][i-1] != 'B'){
                        SpriteNumberList[j][i] = 8;
                    }
                    else if (levelcharArray[j+1][i] != 'B' && levelcharArray[j][i-1] != 'B'){
                        SpriteNumberList[j][i] = 10;
                    }
                    else if (levelcharArray[j-1][i] == 'B' && levelcharArray[j+1][i] == 'B' && levelcharArray[j][i-1] != 'B'){//Empty left
                        SpriteNumberList[j][i] = 30;
                    }
                    else if (levelcharArray[j-1][i] == 'B' && levelcharArray[j+1][i] == 'B' && levelcharArray[j][i+1] != 'B'){//Empty right
                        SpriteNumberList[j][i] = 29;
                    }
                    else if (levelcharArray[j][i-1] == 'B' && levelcharArray[j][i+1] == 'B' && levelcharArray[j-1][i] != 'B'){//empty to the left
                        SpriteNumberList[j][i] = 21;
                    }
                    else if (levelcharArray[j][i-1] == 'B' && levelcharArray[j][i+1] == 'B' && levelcharArray[j+1][i] != 'B'){//empty to the right
                        SpriteNumberList[j][i] = 26;
                    }
                }
                else if (levelcharArray[j][i] == 'S'){
                    pacman.posx = i-15;
                    pacman.posy = j;
                    ///
                    //Power-ups and other bonus items also spawn here!! TODO
                    itemPowerupPosx = i -15;
                    itemPowerupPosy = j;
                    ///
                    SpriteNumberList[j][i] = 0;
                }
                else if (levelcharArray[j][i] == 'G'){
                    snake.posxTail.add(i-15);
                    snake.posxTail.add(i-16);
                    snake.posxTail.add(i-17);
                    snake.posxTail.add(i-18);
                    snake.posxTail.add(i-19);
                    snake.posxTail.add(i-20);
                    snake.posxTail.add(i-21);
                    snake.posxTail.add(i-22);
                    snake.posyTail.add(j);
                    snake.posyTail.add(j);
                    snake.posyTail.add(j);
                    snake.posyTail.add(j);
                    snake.posyTail.add(j);
                    snake.posyTail.add(j);
                    snake.posyTail.add(j);
                    snake.posyTail.add(j);
                    SpriteNumberList[j][i] = 0;
                }
                else if (levelcharArray[j][i] == 'Q'){
                    powerPelletsList.add(new PowerPellet(copy, i, j));
                    SpriteNumberList[j][i] = 0;
                }
            }
        }
        pacman.SpriteMapListCopy = SpriteNumberList;
        snake.SpriteMapListCopy = SpriteNumberList;
    }

    /**
     * Move both Pacman and snake
     */
    public void moveAll(){
        pacman.Move();
        snake.Move();
    }

    /**
     * move pacman (Read PlayArena.movePacman())
     * @param d
     */
    public void movePacman(int d){
        pacman.direcStack = d;
    }

    /**
     * check if pacman overlaps with any of the snakes body parts if true then set pacman to start, lose points and grow the snake
     * @return
     */
    public int deadCheck(){
        for (int i = 0; i < snake.posxTail.size(); i++){
            if ((pacman.posx == snake.posxTail.get(i)) && (pacman.posy == snake.posyTail.get(i))){
                pacman.posx = itemPowerupPosx ;//Start positions
                pacman.posy = itemPowerupPosy;
                if(snake.posyTail.get(snake.posyTail.size()-1)<= 1){
                    snake.posxTail.add(snake.posxTail.get(snake.posxTail.size()-1)-1);
                    snake.posyTail.add(snake.posyTail.get(snake.posyTail.size()-1));
                }
                else{
                    snake.posxTail.add(snake.posxTail.get(snake.posxTail.size()-1));
                    snake.posyTail.add(snake.posyTail.get(snake.posyTail.size()-1)-1);
                }
                //lose life
                return -100;//lose points instead
            }
        }
        return 0;
    }

    /**
     * if pacman lands on a pellet or p-dot then eat and increase score, removing that item from the game arena
     * @return
     */
    public int pickUp(){//return points
        for (int i = 0; i < powerPelletsList.size(); i++){
            if (pacman.posx == powerPelletsList.get(i).posx -14 && pacman.posy == powerPelletsList.get(i).posy){
                powerPelletsList.remove(i);
                return 50;
            }
        }
        for (int i = 0; i < pDotList.size(); i++){
            if (pacman.posx == pDotList.get(i).posx -14 && pacman.posy == pDotList.get(i).posy){
                pDotList.remove(i);
                menusfx.play(pdotsfx,0.5f,0.5f,0,0,1.0f);
                return 10;
            }
        }
        return 0;
    }

}
