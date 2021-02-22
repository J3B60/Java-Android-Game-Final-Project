package uk.ac.reading.cs2ja16;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class LevelSelect extends Activity {

    MenuEffects me;

    /**
     * Present options to user as buttons for each stage then send user to gameActivity with the correct stage.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_level_select);

        hideSystemUI();

        GameView mGameView = (GameView)findViewById(R.id.gamearea);

        me = (MenuEffects) getIntent().getSerializableExtra("MEffects");

        final SoundPool menusfx;
        SoundPool.Builder tmpbuild = new SoundPool.Builder();
        tmpbuild.setMaxStreams(10);
        menusfx = tmpbuild.build();

        final int pacsfx = menusfx.load(mGameView.getContext(),R.raw.dot,1);

        Button stgonebtn  = (Button) findViewById(R.id.Stage1button);
        stgonebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(stgonebtn, 1);//HIGHWAY
            }
        });
        Button stgtwobtn  = (Button) findViewById(R.id.Stage2button);
        stgtwobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(stgtwobtn,2);//This STAYS AS IS//CLASSIC
            }
        });
        Button stgthreebtn  = (Button) findViewById(R.id.Stage3button);
        stgthreebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(stgthreebtn, 3);//SNAKE
            }
        });
        Button stgfourbtn  = (Button) findViewById(R.id.Stage4button);
        stgfourbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(stgfourbtn, 4);//Editor
            }
        });
        final Button backlvlselectbtn = (Button) findViewById(R.id.backbuttonLvlselect);
        backlvlselectbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(backlvlselectbtn, 0);
            }
        });
    }

    /**
     * Re hide system UI
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) hideSystemUI();
    }

    /**
     * Button effects, if x=0 then finish, else pass the number as the level number for the gameActivity to decide which
     * game to run
     * @param btn
     * @param x
     */
    private void buttonEffect(View btn, int x){//x = level
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
                                                                        if (x == 0){
                                                                            finish();
                                                                        }
                                                                        else{
                                                                            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                                                                            intent.putExtra("MEffects", me);
                                                                            intent.putExtra("lvl", x);//USER
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

    /**
     * Hide the system UI for fullscreen immersive view
     */
    private void hideSystemUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        |View.SYSTEM_UI_FLAG_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
