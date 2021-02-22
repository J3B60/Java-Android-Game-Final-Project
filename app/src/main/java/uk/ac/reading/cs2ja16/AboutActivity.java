package uk.ac.reading.cs2ja16;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity {

    /**
     * Sets up the system UI to hide the title, system buttons bar, notification bar etc.
     * Next loads elements from te layout file, loads menu effects settings, loads standard exit button elements which is the same
     * as other activities. Exit button has animation and finishes the activity
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_about);

        hideSystemUI();

        GameView mGameView = (GameView)findViewById(R.id.gamearea);

        MenuEffects me = (MenuEffects) getIntent().getSerializableExtra("MEffects");//GET SETTINGS VALUES

        final SoundPool menusfx; //BUTTON SFX
        SoundPool.Builder tmpbuild = new SoundPool.Builder();
        tmpbuild.setMaxStreams(10);
        menusfx = tmpbuild.build();

        final int pacsfx = menusfx.load(mGameView.getContext(),R.raw.dot,1);//LOAD SFX

        final Button Aboutreturnbtn = (Button) findViewById(R.id.backbuttonAbout);
        Aboutreturnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);//Play sfx
                buttonEffect(Aboutreturnbtn);//finish
            }
        });

        final TextView AboutTitleText = (TextView) findViewById(R.id.AboutTitle);

        final TextView AboutText = (TextView) findViewById(R.id.AboutText);
    }

    /**
     * re-runs hide system ui elements when activity regains focus
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) hideSystemUI();//re hide
    }


    /**
     * button flashing animation and finish activity
     * @param btn
     */
    private void buttonEffect(View btn) {
        btn.animate().alpha(0).setDuration(20).withEndAction(new Runnable() {//Hide
            @Override
            public void run() {
                btn.animate().alpha(1).setDuration(20).withEndAction(new Runnable() {//Show
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
     * Hide system UI elements to make fullscreen immersive. No system UI buttons bar or notification bar.
     */
    private void hideSystemUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        |View.SYSTEM_UI_FLAG_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
