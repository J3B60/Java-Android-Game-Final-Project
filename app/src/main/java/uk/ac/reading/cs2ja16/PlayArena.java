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

public class PlayArena {
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
    public Blinky blinky;
    public Inky inky;
    public Pinky pinky;
    public Clyde clyde;

    /**
     * load all sprites, music and create objects of the moving objects
     * @param gv
     */
    public PlayArena(GameView gv){
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
        blinky = new Blinky(gv);
        inky = new Inky(gv);
        pinky = new Pinky(gv);
        clyde = new Clyde(gv);

        SoundPool.Builder tmpbuild = new SoundPool.Builder();
        tmpbuild.setMaxStreams(10);
        menusfx = tmpbuild.build();

        pdotsfx = menusfx.load(gv.getContext(),R.raw.dot,1);
        pacdeathsfx = menusfx.load(gv.getContext(),R.raw.death,1);
        pacslowsfx = menusfx.load(gv.getContext(),R.raw.slow,1);
        pacfastsfx = menusfx.load(gv.getContext(),R.raw.fast,1);

    }



//    public void LoadMap(Activity act, String str){//DO NOT USE!! NEEDS FIXING #THE WAY TO GO FOR RANDOM GENERATION/USER CREATED LEVELS
//        String arenatext = "";
////        try {
////            InputStream arenafile = act.getAssets().open(str+".txt");
////            int size = arenafile.available();
////            byte [] buffer = new byte[size];
////            arenafile.read(buffer);
////            arenafile.close();
////            arenatext = new String(buffer);
////        } catch (IOException e) {
////            e.printStackTrace();
//        //(R.string.level_one);
//        int j =0;
//        for (int i = 0; i < arenatext.length(); i++){
//            if (arenatext.charAt(i) != '\n'){
//                j++;//Newline -> next row
//                continue;
//            }
//            else{
//                ArenaMap[i][j] = arenatext.charAt(i);
//            }
//        }
//        scanMap();//continue with nex function
//
//    }

//    private void scanMap(){//just to tidy up code//DO NOT USE NOT ENOUGH TIME TO BUILD MAP USE PRE MADE #THE WAY TO GO FOR USER LEVELS OR RANDOM LEVELS
//        ArrayList<warppair> wplis = new ArrayList<warppair>();
//
//        for (int i = 0; i < ArenaMap.length; i ++){//Through all of row
//            for(int j = 0; j < ArenaMap[0].length; j++){//Through all columns (can just us column 0 for length since all level files should have the same number of items in each row)
//                if (ArenaMap[i][j] == 'O'){
////                    boolean rightNeighbour = false;
////                    boolean leftNeighbour = false;
////                    boolean aboveNeighbour = false;
////                    boolean belowNeighbour = false;
////                    if (i != 0){
////                        if (ArenaMap[i - 1][j] == 'O'){
////                            leftNeighbour = true;
////                        }
////                    }
////                    //else it stays false
////                    if (i != ArenaMap.length){
////                        if (ArenaMap[i+1][j] == 'O'){
////                            rightNeighbour = true;
////                        }
////                    }
////                    //else it stays false
////                    if (j != 0){
////                        if (ArenaMap[i][j-1] == 'O'){
////                            aboveNeighbour = true;
////                        }
////                    }
////                    //else it stays false
////                    if (j != ArenaMap[0].length){
////                        if (ArenaMap[i][j+1] == 'O'){
////                            belowNeighbour = true;
////                        }
////                    }
////                    //else it stays false
////                    //16 Cases
////                    if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == false){//SHOULD NOT HAPPEN NO O ISLAND
////                        SpriteNumberList[i][j] = 37;
////                    }
////                    else if (rightNeighbour == true && leftNeighbour == true && aboveNeighbour == true && belowNeighbour == true){//O is Surrounded so empty
////                        SpriteNumberList[i][j] = 0;
////                    }
////                    else if (rightNeighbour == true && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == false){//ERROR
////                        SpriteNumberList[i][j] = 37;
////                    }
////                    else if (rightNeighbour == false && leftNeighbour == true && aboveNeighbour == false && belowNeighbour == false){//ERROR
////                        SpriteNumberList[i][j] = 37;
////                    }
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == true && belowNeighbour == false){//ERROR
////                        SpriteNumberList[i][j] = 37;
////                    }
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == true){//ERROR
////                        SpriteNumberList[i][j] = 37;
////                    }
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == true && belowNeighbour == true){
////                        SpriteNumberList[i][j] =
////                    }
////                    else if (rightNeighbour == false && leftNeighbour == true && aboveNeighbour == false && belowNeighbour == true);
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == true);
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == true);
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == true);
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == true);
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == true);
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == true);
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == true);
////                    else if (rightNeighbour == false && leftNeighbour == false && aboveNeighbour == false && belowNeighbour == true);
//                    char topleft = '!';
//                    char left = '!';
//                    char bottomleft = '!';
//                    char top = '!';
//                    char bottom = '!';
//                    char topright = '!';
//                    char right = '!';
//                    char bottomright = '!';
//                    //Find Neighbour value
//                    if (i != 0){
//                        if(j != 0){
//                            topleft = ArenaMap[i-1][j-1];
//                            top = ArenaMap[i][j-1];
//                        }
//                        ////////////
//                        left = ArenaMap[i-1][j];
//                        ////////////
//                        if (j != ArenaMap[0].length){
//                            bottomleft = ArenaMap[i-1][j+1];
//                            bottom = ArenaMap[i][j+1];
//                        }
//                    }
//                    //else it stays false
//                    if (i != ArenaMap.length){
//                        if(j != 0){
//                            topright = ArenaMap[i+1][j-1];
//                        }
//                        ////////////
//                        right = ArenaMap[i+1][j];
//                        ////////////
//                        if (j != ArenaMap[0].length){
//                            bottomright = ArenaMap[i+1][j+1];
//                        }
//                    }
//                    ///////////////Sprites for Neighbour combinations
//                    ///////////////TODO THIS PART NEEDS TO BE EXTENDED TO USE THE PROPER SPRITES BUT THIS IS GOOD ENOUGH FOR NOW, FOR THE GAME
//                    ////////////////NOTE THAT THIS DOE NOT USE THE DIAGONALS FOR NOW
//                    if ((top == 'O' || top == '!') && (left == 'O' || left == '!') && right == 'O' && bottom == 'O'){//bottomright large arc
//                        ArenaMap[i][j] = 2;
//                    }
//                    else if (top == 'O' && (left == 'O' || left == '!') && right == 'O' && (bottom == 'O' || bottom == '!')){//topright large arc
//                        ArenaMap[i][j] = 4;
//                    }
//                    else if ((top == 'O' || top == '!') && (right == 'O' || right == '!') && left == 'O' && bottom == 'O'){//bottomleft large arc
//                        ArenaMap[i][j] = 1;
//                    }
//                    else if (top == 'O' && (right == 'O' || right == '!') && left == 'O' && (bottom == 'O' || bottom == '!')){//topleft large arc
//                        ArenaMap[i][j] = 3;
//                    }
//                    ///////////SMALL ARCS
//                    else if(top != '!' && top != 'O' && left != '!' && left != 'O' && right == 'O' && bottom == 'O'){//bottomright small arc
//                        ArenaMap[i][j] = 6;
//                    }
//                    else if(top == 'O' &&  left != '!' && left != 'O' && right == 'O' && bottom != 'O' && bottom != '!'){//topright small arc
//                        ArenaMap[i][j] = 35;
//                    }
//                    else if(top != 'O' && top != '!' && left == 'O' && right != 'O' && right != '!' && bottom == 'O'){// bottomleft small arc
//                        ArenaMap[i][j] = 5;
//                    }
//                    else if(top == 'O' && left == 'O' && right != 'O' && right != '!' && bottom != 'O' && bottom != '!'){//topleft small arc
//                        ArenaMap[i][j] = 34;
//                    }
//                    //////////HORIZONTAL
//                    else if(top != '!' && top != 'O' && left == 'O' && right == 'O' && (bottom == 'O' || bottom == '!')){//bottom edge
//                        ArenaMap[i][j] = 15;
//                    }
//                    else if(bottom != '!' && bottom != 'O' && left == 'O' && right == 'O' && (top == 'O' || top == '!')){//top edge
//                        ArenaMap[i][j] = 16;
//                    }
//                    /////////VERTICAL
//                    else if(left != '!' && left != 'O' && top == 'O' && bottom == 'O' && (right == 'O' || right == '!')){//Right edge
//                        ArenaMap[i][j] = 39;
//                    }
//                    else if(right != '!' && right != 'O' && top == 'O' && bottom == 'O' && (left == 'O' || left == '!')){//left edge
//                        ArenaMap[i][j] = 39;
//                    }
//                    //////////REPLACE WITH BLANK IF EMPTY NOTICE THAT THIS IS A NEW IF STATEMENT TO REPLACE ANY MISTAKE
//
//                    if(top == 'O' && left == 'O' && right == 'O' && bottom == 'O' && topleft =='O' && topright == 'O' && bottomleft == 'O' && bottomright == 'O'){//Empty
//                        ArenaMap[i][j] = 0;
//                    }
//
//                }
//                if (ArenaMap[i][j] == 'W'){
//                    //needs to find neighbours to find type
//                    boolean rightNeighbour = false;
//                    boolean leftNeighbour = false;
//                    boolean aboveNeighbour = false;
//                    boolean belowNeighbour = false;
//                    if (i != 0){
//                        if (ArenaMap[i - 1][j] == 'W'){
//                            leftNeighbour = true;
//                        }
//                    }
//                    //else it stays false
//                    if (i != ArenaMap.length){
//                        if (ArenaMap[i+1][j] == 'W'){
//                            rightNeighbour = true;
//                        }
//                    }
//                    //else it stays false
//                    if (j != 0){
//                        if (ArenaMap[i][j-1] == 'W'){
//                            aboveNeighbour = true;
//                        }
//                    }
//                    //else it stays false
//                    if (j != ArenaMap[0].length){
//                        if (ArenaMap[i][j+1] == 'W'){
//                            belowNeighbour = true;
//                        }
//                    }
//                    //else it stays false
//                    if (aboveNeighbour && rightNeighbour && belowNeighbour && leftNeighbour){
//                        ///Diagonal checks required
//                        boolean topleft = false;
//                        boolean topright = false;
//                        boolean bottomleft = false;
//                        boolean bottomright = false;
//                        //////CHECKS
//                        if (ArenaMap[i-1][j-1] == 'W'){
//                            topleft = true;
//
//                        }
//                        if (ArenaMap[i+1][j-1] == 'W'){
//                            topright = true;
//                        }
//                        if (ArenaMap[i-1][j+1] == 'W'){
//                            bottomleft = true;
//                        }
//                        if (ArenaMap[i+1][j+1] == 'W'){
//                            bottomright = true;
//                        }
//                        ////////////WORK
//                        if (topleft && topright && bottomleft && bottomright){//EMPTY
//                            ArenaMap[i][j] = 0;
//                        }
//                        if (!topleft && topright && bottomleft && bottomright){//big topleft arc
//                            ArenaMap[i][j] = 3;
//                        }
//                        if (topleft && !topright && bottomleft && bottomright){//big topright arc
//                            ArenaMap[i][j] = 4;
//                        }
//                        if (topleft && topright && !bottomleft && bottomright){//big bottomleft arc
//                            ArenaMap[i][j] = 1;
//                        }
//                        if (topleft && topright && bottomleft && !bottomright){//big bottomright arc
//                            ArenaMap[i][j] = 2;
//                        }
//                    }
//                    //SMALL ARCS
//                    else if (!(aboveNeighbour) && rightNeighbour && belowNeighbour && !(leftNeighbour)){//small bottom right arc
//                        ArenaMap[i][j] = 6;
//                    }
//                    else if (aboveNeighbour && rightNeighbour && !(belowNeighbour) && !(leftNeighbour)){//small top right arc
//                        ArenaMap[i][j] = 35;
//                    }
//                    else if (!(aboveNeighbour) && !(rightNeighbour) && belowNeighbour && leftNeighbour){//small bottomleft arc
//                        ArenaMap[i][j] = 5;
//                    }
//                    else if (aboveNeighbour && !(rightNeighbour) && !(belowNeighbour) && leftNeighbour){//small topleft arc
//                        ArenaMap[i][j] = 34;
//                    }
//                    //EDGES
//                    else if (aboveNeighbour && !(rightNeighbour) && belowNeighbour && leftNeighbour){//left vertical edge
//                        ArenaMap[i][j] = 38;
//                    }
//                    else if (aboveNeighbour && rightNeighbour && belowNeighbour && !(leftNeighbour)){//right vertical edge
//                        ArenaMap[i][j] = 39;
//                    }
//                    else if (aboveNeighbour && rightNeighbour && !(belowNeighbour) && leftNeighbour){//top horizontal edge
//                        ArenaMap[i][j] = 16;
//                    }
//                    else if (!(aboveNeighbour) && rightNeighbour && belowNeighbour && leftNeighbour){//bottom horizontal edge
//                        ArenaMap[i][j] = 15;
//                    }
//                }
//                if (ArenaMap[i][j] == 'P'){
//                    SpriteNumberList[i][j] = 0;//SPECIAL CASE Pac-Dot #DO NOT USE PAC DOT FROM THIS SPRITE ARRAYLIST
//                }
//                if (ArenaMap[i][j] == 'Q'){
//                    SpriteNumberList[i][j] = 0;//SPECIAL CASE Power Pellet #DO NOT USE PAC DOT FROM THIS SPRITE ARRAYLIST
//                }
//                if (ArenaMap[i][j] == 'G'){
//                    SpriteNumberList[i][j] = 0; //SPECIAL CASE Will use Sprite 0, sets blinky start pos
//                }
//                if (ArenaMap[i][j] == 'B'){
//                    //Need to find corners and the top//TODO
//                    if (ArenaMap[i-1][j] == 'B' && ArenaMap[i+1][j] == 'B' && ArenaMap[i][j-1] == 'B' && ArenaMap[i][j+1] == 'B'){
//                        SpriteNumberList[i][j] = 0;
//                    }
//                    else if (ArenaMap[i-1][j] != 'B' && ArenaMap[i][j+1] != 'B'){
//                        SpriteNumberList[i][j] = 10;
//                    }
//                    else if (ArenaMap[i+1][j] != 'B' && ArenaMap[i][j+1] != 'B'){
//                        SpriteNumberList[i][j] = 9;
//                    }
//                    else if (ArenaMap[i-1][j] != 'B' && ArenaMap[i][j-1] != 'B'){
//                        SpriteNumberList[i][j] = 8;
//                    }
//                    else if (ArenaMap[i+1][j] != 'B' && ArenaMap[i][j-1] != 'B'){
//                        SpriteNumberList[i][j] = 7;
//                    }
//                    else if (ArenaMap[i-1][j] == 'B' && ArenaMap[i+1][j] == 'B' && ArenaMap[i][j-1] != 'B'){//Empty abpve
//                        SpriteNumberList[i][j] = 15;
//                    }
//                    else if (ArenaMap[i-1][j] == 'B' && ArenaMap[i+1][j] == 'B' && ArenaMap[i][j+1] != 'B'){//Empty below
//                        SpriteNumberList[i][j] = 16;
//                    }
//                    else if (ArenaMap[i][j-1] == 'B' && ArenaMap[i][j+1] == 'B' && ArenaMap[i-1][j] != 'B'){//empty to the left
//                        SpriteNumberList[i][j] = 39;
//                    }
//                    else if (ArenaMap[i][j-1] == 'B' && ArenaMap[i][j+1] == 'B' && ArenaMap[i+1][j] != 'B'){//empty to the right
//                        SpriteNumberList[i][j] = 38;
//                    }
//                }
//                if (ArenaMap[i][j] == 'S'){
//                    SpriteNumberList[i][j] = 0; //SPECIAL CASE will use Sprite 0, sets pacman start pos
//                }
//                if (ArenaMap[i][j] == 'E'){
//                    SpriteNumberList[i][j] = 0;//Empty
//                }
//                if (ArenaMap[i][j] == 'X'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == 'Y'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == '1'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == '2'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == '3'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == '4'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == '5'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == '6'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == '7'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == '8'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == '9'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//                if (ArenaMap[i][j] == 'Z'){
//                    //need to find other pair
//                    SpriteNumberList[i][j] = 0;
//                }
//            }
//        }
//
//        for (int i = 0; i < 12; i++){
//        }
//        warppair x;
//        warppair y;
//        warppair x1;
//        warppair x2;
//        warppair x3;
//        warppair x4;
//        warppair x5;
//        warppair x6;
//        warppair x7;
//        warppair x8;
//        warppair x9;
//        warppair z;
//
//        char[] wplables = {'x','y','1','2','3','4','5','6','7','8','9','z'};
//
//    }

    /**
     * Was to be used to make more interesing warps that can warp player to not just the opposite side of the screen but anywhere
     */
    public class warppair {
        int xOne;
        int xTwo;
        int yOne;
        int yTwo;
        public warppair(int x, int y, int xx, int yy){
            xOne = x;
            xTwo = xx;
            yOne = y;
            yTwo = yy;
        }
    }

    /**
     * read char map and set the right sprites for the arena. Set positions of the box, ghosts, pellets, pdots, pacman and give
     * a copy of the resulting spritenumberMap to the moving objects
     * @param levelcharArray
     */
    public void newMapLoad(char[][] levelcharArray){
        pDotList.clear();
        powerPelletsList.clear();
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
                    //NEW any case
                    else {
                        SpriteNumberList[j][i] = 40;
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
                        clyde.posx = i - 15;
                        clyde.posy = j - 2;

                        pinky.posx = i - 17;
                        pinky.posy = j;

                        inky.posx = i - 19;
                        inky.posy = j - 1;
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
                    blinky.posx = i-15;
                    blinky.posy = j;
                    SpriteNumberList[j][i] = 0;
                }
                else if (levelcharArray[j][i] == 'Q'){
                    powerPelletsList.add(new PowerPellet(copy, i, j));
                    SpriteNumberList[j][i] = 0;
                }
            }
        }
        pacman.SpriteMapListCopy = SpriteNumberList;
        blinky.SpriteMapListCopy = SpriteNumberList;
        inky.SpriteMapListCopy = SpriteNumberList;
        pinky.SpriteMapListCopy = SpriteNumberList;
        clyde.SpriteMapListCopy = SpriteNumberList;
    }

    /**
     * Move all players
     */
    public void moveAll(){
        pacman.Move();
        blinky.Move();
        inky.Move();
        pinky.Move();
        clyde.Move();
    }

    /**
     * move pacman, used because of "keeps stopping" error message rather than direct reference from TheGame class
     * @param d
     */
    public void movePacman(int d){
        pacman.direcStack = d;
    }

    /**
     * Check if ghost and pacman overlap, if ghosts are weak then pacman captures and gains points if not then pacman returns
     * to start and loses points
     * @return
     */
    public int deadCheck(){
        if((pacman.posx == blinky.posx && pacman.posy == blinky.posy) || (pacman.posx == inky.posx && pacman.posy == inky.posy) || (pacman.posx == pinky.posx && pacman.posy == pinky.posy) || (pacman.posx == clyde.posx && pacman.posy == clyde.posy)){
            if (pacman.activePowerUp == 1){
                if (pacman.posx == blinky.posx && pacman.posy == blinky.posy){
                    blinky.boxMove = 1;
                    return 100;
                }
                else if (pacman.posx == inky.posx && pacman.posy == inky.posy){
                    inky.boxMove = 1;
                    return 100;
                }
                else if (pacman.posx == pinky.posx && pacman.posy == pinky.posy){
                    pinky.boxMove = 1;
                    return 100;
                }
                else if (pacman.posx == clyde.posx && pacman.posy == clyde.posy){
                    clyde.boxMove = 1;
                    return 100;
                }
            }
            else{
                pacman.posx = itemPowerupPosx ;//Start positions
                pacman.posy = itemPowerupPosy;
                //lose life
                return -100;//lose points instead
            }
        }
        return 0;
    }

    /**
     * check if picked up p-dot or power pellet, power pellet activates power up and makes ghosts weak
     * @return
     */
    public int pickUp(){//return points
        for (int i = 0; i < powerPelletsList.size(); i++){
            if (pacman.posx == powerPelletsList.get(i).posx -14 && pacman.posy == powerPelletsList.get(i).posy){
                blinky.weak = true;
                inky.weak = true;
                pinky.weak = true;
                clyde.weak = true;
                pacman.activePowerUp =1;
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
