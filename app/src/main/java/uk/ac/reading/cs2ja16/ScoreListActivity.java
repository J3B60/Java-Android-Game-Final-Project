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

public class ScoreListActivity extends Activity {

    public static float sfxvolume = 1.0f;//NEED TO MAKE THIS SHARED

    /**
     * Show scores to the user
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_highscore);

        hideSystemUI();

        GameView mGameView = (GameView)findViewById(R.id.gamearea);

        MenuEffects me = (MenuEffects) getIntent().getSerializableExtra("MEffects");//load volume setting

        final SoundPool menusfx;
        SoundPool.Builder tmpbuild = new SoundPool.Builder();
        tmpbuild.setMaxStreams(10);
        menusfx = tmpbuild.build();

        final int pacsfx = menusfx.load(mGameView.getContext(),R.raw.dot,1);//sound effect

        final Button Scorereturnbtn = (Button) findViewById(R.id.backbuttonHighscore);
        Scorereturnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                menusfx.play(pacsfx,me.sfxvolume,me.sfxvolume,0,0,1.0f);
                buttonEffect(Scorereturnbtn);//exit
            }
        });
        //Show all text views for the title and scores

        final TextView HighScoreTitleText = (TextView) findViewById(R.id.highscoreTitle);

        final TextView name1 = (TextView) findViewById(R.id.name1);

        final TextView name2 = (TextView) findViewById(R.id.name2);

        final TextView name3 = (TextView) findViewById(R.id.name3);

        final TextView name4 = (TextView) findViewById(R.id.name4);

        final TextView name5 = (TextView) findViewById(R.id.name5);

        final TextView mt1 = (TextView) findViewById(R.id.middleText);
        final TextView mt2 = (TextView) findViewById(R.id.middleText2);
        final TextView mt3 = (TextView) findViewById(R.id.middleText3);
        final TextView mt4 = (TextView) findViewById(R.id.middleText4);
        final TextView mt5 = (TextView) findViewById(R.id.middleText5);

        final TextView score1 = (TextView) findViewById(R.id.score1);

        final TextView score2 = (TextView) findViewById(R.id.score2);

        final TextView score3 = (TextView) findViewById(R.id.score3);

        final TextView score4 = (TextView) findViewById(R.id.score4);

        final TextView score5 = (TextView) findViewById(R.id.score5);

    }

    /**
     * re hide UI when focus gained
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) hideSystemUI();
    }

    /**
     * Animation for exit button then finish() activity
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
     * Hide the system notification bar and buttons bar for fullscreen
     */
    private void hideSystemUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        |View.SYSTEM_UI_FLAG_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
