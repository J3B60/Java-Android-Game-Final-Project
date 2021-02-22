package uk.ac.reading.cs2ja16;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class HowToPlayActivity extends Activity {

    public static float sfxvolume = 1.0f;//NEED TO MAKE THIS SHARED

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_how_to_play);

        hideSystemUI();//Hide OS UI

        GameView mGameView = (GameView)findViewById(R.id.gamearea);

        MenuEffects me = (MenuEffects) getIntent().getSerializableExtra("MEffects");//Volume settings

        final SoundPool menusfx;
        SoundPool.Builder tmpbuild = new SoundPool.Builder();
        tmpbuild.setMaxStreams(10);
        menusfx = tmpbuild.build();

        final int pacsfx = menusfx.load(mGameView.getContext(),R.raw.dot,1);

        final Button HowToPlayreturnbtn = (Button) findViewById(R.id.backbuttonHowToPlay);
        HowToPlayreturnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(HowToPlayreturnbtn);//Finish()
            }
        });

        final TextView HTPtitle = (TextView) findViewById(R.id.HowToPlayTitle);

        final TextView HTPtext = (TextView) findViewById(R.id.HowToPlayText);
    }

    /**
     * Re hide system UI when back in focus
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) hideSystemUI();
    }

    /**
     * Button Flashing Effects for back button
     * @param btn
     */
    private void buttonEffect(View btn) {
        btn.animate().alpha(0).setDuration(20).withEndAction(new Runnable() {
            @Override
            public void run() {
                btn.animate().alpha(1).setDuration(20).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        btn.animate().alpha(0).setDuration(20).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                btn.animate().alpha(1).setDuration(20).withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn.animate().alpha(0).setDuration(20).withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                btn.animate().alpha(1).setDuration(20).withEndAction(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        btn.animate().alpha(0).setDuration(20).withEndAction(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                btn.animate().alpha(1).setDuration(20).withEndAction(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        finish();
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
     * Hide system UI notification/buttons
     */
    private void hideSystemUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        |View.SYSTEM_UI_FLAG_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
