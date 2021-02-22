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

public class SettingsActivity extends Activity {
    /**
     * Show sound icons and the sliders
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);

        hideSystemUI();

        GameView mGameView = (GameView)findViewById(R.id.gamearea);

        MenuEffects me = (MenuEffects) getIntent().getSerializableExtra("MEffects");

        final SoundPool menusfx;
        SoundPool.Builder tmpbuild = new SoundPool.Builder();
        tmpbuild.setMaxStreams(10);
        menusfx = tmpbuild.build();

        final int pacsfx = menusfx.load(mGameView.getContext(),R.raw.dot,1);

        final Button Setreturnbtn = (Button) findViewById(R.id.backbuttonSettings);
        Setreturnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(Setreturnbtn);
            }
        });

        final TextView SetTitle = (TextView) findViewById(R.id.SettingsTitle);
    }

    /**
     * re hide the UI on focus gain
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) hideSystemUI();
    }

    /**
     * animate exit button and then finish activity
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
     * Hide the OS UI
     */
    private void hideSystemUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        |View.SYSTEM_UI_FLAG_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
