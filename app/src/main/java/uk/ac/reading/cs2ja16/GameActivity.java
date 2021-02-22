package uk.ac.reading.cs2ja16;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity {

    GameView mGameView;
    GameThread mGameThread;

    MediaPlayer mPlayer;
    MenuEffects me;

    int level=0;

    /**
     * load the correct level based on the passed "lvl" integer
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//Crashes android 6.0.1
        setContentView(R.layout.activity_game);
        mGameView = (GameView)findViewById(R.id.gamearea);

        me = (MenuEffects) getIntent().getSerializableExtra("MEffects");//get music volume settings

        level = (int) getIntent().getSerializableExtra("lvl");//Get level

        SoundPool.Builder tmpbuild = new SoundPool.Builder();
        tmpbuild.setMaxStreams(10);
        SoundPool menusfx = tmpbuild.build();

        final int pacintrosfx = menusfx.load(mGameView.getContext(),R.raw.intro,1);

        menusfx.play(pacintrosfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);

        TextView hStatic = (TextView) findViewById(R.id.highscoreStaticText);//non-changing text
        TextView hValue= (TextView) findViewById(R.id.highscoreValueText);//highscore
        TextView sValue= (TextView) findViewById(R.id.scoreText);//Score
        ImageView stateImage = (ImageView) findViewById(R.id.stateImage);

        mGameView.setStatusView(stateImage);
        mGameView.setScoreView(sValue);

        if (level == 1){
            startGameHighway(mGameView, null, savedInstanceState);
        }
        else if (level == 2){
            startGame(mGameView, null, savedInstanceState);
        }
        else if (level == 3){
            startGameSnake(mGameView, null, savedInstanceState);
        }
        else if (level == 4){
            startGameEdit(mGameView, null, savedInstanceState);
        }
    }

    /**
     * re hide UI on focus gained
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) hideSystemUI();
    }

    /**
     * Play the correct music depending on the stage currently playing (play music even when returning to app)
     */
    @Override
    protected  void onResume(){
        super.onResume();
        if (level == 1){
            mPlayer = MediaPlayer.create(this, R.raw.dimensions);
        }
        else if (level == 2){
            mPlayer = MediaPlayer.create(this, R.raw.rainbow);
        }
        else if (level == 3){
            mPlayer = MediaPlayer.create(this, R.raw.pactwinattack);
        }
        else if (level == 4){
            mPlayer = MediaPlayer.create(this, R.raw.pacmadness);
        }

        mPlayer.setVolume(me.musicvolume,me.musicvolume);
        mPlayer.setLooping(true);
        mPlayer.start();
    }

    /**
     * Stop music and thread when leaving the app
     */
    @Override
    protected void onPause() {
        super.onPause();
        mPlayer.stop();
        if(mGameThread.getMode() == GameThread.STATE_RUNNING) {
            mGameThread.setState(GameThread.STATE_PAUSE);
        }
    }

    /**
     * Start TheGame version which is the "Classic" stage
     * @param gView
     * @param gThread
     * @param savedInstanceState
     */
    private void startGame(GameView gView, GameThread gThread, Bundle savedInstanceState) {

        //Set up a new game, we don't care about previous states
        mGameThread = new TheGame(mGameView);
        mGameView.setThread(mGameThread);
        mGameThread.setState(GameThread.STATE_READY);
        mGameView.startSensor((SensorManager)getSystemService(Context.SENSOR_SERVICE));
    }

    /**
     * Start The Highway stage
     * @param gView
     * @param gThread
     * @param savedInstanceState
     */
    private void startGameHighway(GameView gView, GameThread gThread, Bundle savedInstanceState) {

        //Set up a new game, we don't care about previous states
        mGameThread = new TheGameHighway(mGameView);
        mGameView.setThread(mGameThread);
        mGameThread.setState(GameThread.STATE_READY);
        mGameView.startSensor((SensorManager)getSystemService(Context.SENSOR_SERVICE));
    }

    /**
     * Start the snake stage
     * @param gView
     * @param gThread
     * @param savedInstanceState
     */
    private void startGameSnake(GameView gView, GameThread gThread, Bundle savedInstanceState) {

        //Set up a new game, we don't care about previous states
        mGameThread = new TheGameSnake(mGameView);
        mGameView.setThread(mGameThread);
        mGameThread.setState(GameThread.STATE_READY);
        mGameView.startSensor((SensorManager)getSystemService(Context.SENSOR_SERVICE));
    }

    /**
     * Start the Edit stage
     * @param gView
     * @param gThread
     * @param savedInstanceState
     */
    private void startGameEdit(GameView gView, GameThread gThread, Bundle savedInstanceState) {

        //Set up a new game, we don't care about previous states
        mGameThread = new TheGameEdit(mGameView);
        mGameView.setThread(mGameThread);
        mGameThread.setState(GameThread.STATE_READY);
        mGameView.startSensor((SensorManager)getSystemService(Context.SENSOR_SERVICE));
    }

    /**
     * Hide the system UI (buttons bar, notifications bar etc.) to make fullscreen immersive view
     */
    private void hideSystemUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        |View.SYSTEM_UI_FLAG_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /**
     * Stoop music when leaving the game and going back to previous activity
     */
    @Override
    public void finish(){
        mPlayer.stop();
        super.finish();
    }
}
