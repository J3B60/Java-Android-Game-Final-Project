package uk.ac.reading.cs2ja16;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final int MENU_RESUME = 1;
    private static final int MENU_START = 2;
    private static final int MENU_STOP = 3;

    MediaPlayer mPlayer;
    MenuEffects me = new MenuEffects();
    //EXAMPLE HOW TO USE
    //TextView score = (TextView)findViewById(R.id.score);
    //score.setTypeface(pcmanfont);

    private GameView mGameView;

    /**
     * Called when the activity is first created. general main menu with buttons to other Activities
     * */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        hideSystemUI();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//CRASHES ON 6.0.1, works fine in 7.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGameView = (GameView)findViewById(R.id.gamearea);


        mPlayer = MediaPlayer.create(this, R.raw.entrance);
        mPlayer.setVolume(me.musicvolume,me.musicvolume);
        mPlayer.setLooping(true);
        mPlayer.start();//menu music
        //Code Below is for a menu loop
//        if (!(mPlayer.isPlaying())){
//            mPlayer.reset();
//        }
        /////////////////////////////

        SoundPool.Builder tmpbuild = new SoundPool.Builder();
        tmpbuild.setMaxStreams(10);
        SoundPool menusfx = tmpbuild.build();

        final int pacsfx = menusfx.load(mGameView.getContext(),R.raw.dot,1);//button effects

        //ImageView MainTitle = (ImageView) findViewById(R.id.MainTitle);//NotWorking???

        Button startbtn = (Button) findViewById(R.id.Startbutton);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                mPlayer.stop();
                buttonEffect(startbtn, LevelSelect.class, 2);//TODO CHANGE TO LEVEL SELECT ACTIVITY
            }
        });
        final Button exitbtn = (Button) findViewById(R.id.Exitbutton);
        exitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(exitbtn, null, 1);//finish()
            }
        });

        final ImageButton highscorebtn = (ImageButton) findViewById(R.id.highscoreButton);
        highscorebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(highscorebtn, ScoreListActivity.class, 2);
            }
        });

        final ImageButton settingsbtn = (ImageButton) findViewById(R.id.settingsButton);
        settingsbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(settingsbtn, SettingsActivity.class, 2);
            }
        });

        final ImageButton infobtn = (ImageButton) findViewById(R.id.infoButton);
        infobtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(infobtn, AboutActivity.class, 2);
            }
        });

        final ImageButton helpbtn = (ImageButton) findViewById(R.id.helpButton);
        helpbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(helpbtn, HowToPlayActivity.class, 2);
            }
        });
    }

	/*
	 * Activity state functions
	 */
    @Override
    protected void onPause() {
        super.onPause();
        //mPlayer.stop();
//        if(mGameThread.getMode() == GameThread.STATE_RUNNING) {
//            mGameThread.setState(GameThread.STATE_PAUSE);
//        }
    }

    /**
     * stop music
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }

    /**
     * if focus re gained then re hide system UI
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) hideSystemUI();
    }


    /**
     * collection of settings to set the screen to full screen immersive view. This hides the navigation bar, notification bar etc.
     */
    private void hideSystemUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /**
     * Button flashing animation then open the correct activity or finish/exit
     * @param btn
     * @param cls
     * @param x
     */
    private void buttonEffect(View btn, Class cls, int x){
        btn.animate().alpha(0).setDuration(20).withEndAction(new Runnable(){
            @Override
            public void run(){
                btn.animate().alpha(1).setDuration(20).withEndAction(new Runnable(){
                    @Override
                    public void run(){
                        btn.animate().alpha(0).setDuration(20).withEndAction(new Runnable(){
                            @Override
                            public void run(){
                                btn.animate().alpha(1).setDuration(20).withEndAction(new Runnable(){
                                    @Override
                                    public void run(){
                                        btn.animate().alpha(0).setDuration(20).withEndAction(new Runnable(){
                                            @Override
                                            public void run(){
                                                btn.animate().alpha(1).setDuration(20).withEndAction(new Runnable(){
                                                    @Override
                                                    public void run(){
                                                        btn.animate().alpha(0).setDuration(20).withEndAction(new Runnable(){
                                                            @Override
                                                            public void run(){
                                                                btn.animate().alpha(1).setDuration(20).withEndAction(new Runnable(){
                                                                    @Override
                                                                    public void run(){
                                                                        if (x == 1){//Exit button
                                                                            finish();
                                                                        }
                                                                        else if (x == 2){//Other menu button + Start
                                                                            Intent intent = new Intent(getApplicationContext(), cls);
                                                                            intent.putExtra("MEffects", me);
                                                                            startActivity(intent);
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

//    private void showSystemUI(){//just in case I need it
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//    }
}

// This file is part of the course "Begin Programming: Build your first mobile game" from futurelearn.com
// Copyright: University of Reading and Karsten Lundqvist
// It is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// It is is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
//
// You should have received a copy of the GNU General Public License
// along with it.  If not, see <http://www.gnu.org/licenses/>.
