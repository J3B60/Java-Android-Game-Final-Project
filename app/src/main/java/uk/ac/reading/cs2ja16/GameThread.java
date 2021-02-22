package uk.ac.reading.cs2ja16;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

public abstract class GameThread extends Thread {
	//Different mMode states
	public static final int STATE_LOSE = 1;
	public static final int STATE_PAUSE = 2;
	public static final int STATE_READY = 3;
	public static final int STATE_RUNNING = 4;
	public static final int STATE_WIN = 5;

	//Control variable for the mode of the game (e.g. STATE_WIN)
	protected int mMode = 1;

	//Control of the actual running inside run()
	private boolean mRun = false;
		
	//The surface this thread (and only this thread) writes upon
	private SurfaceHolder mSurfaceHolder;
	
	//the message handler to the View/Activity thread
	private Handler mHandler;
	
	//Android Context - this stores almost all we need to know
	private Context mContext;
	
	//The view
	public GameView mGameView;

	//We might want to extend this call - therefore protected
	protected int mCanvasWidth = 1;
	protected int mCanvasHeight = 1;

	//Last time we updated the game physics
	protected long mLastTime = 0;
    protected Bitmap mBackgroundImage;
	protected long score = 0;

    //Used for time keeping
	private long now;
	private float elapsed;

    //Rotation vectors used to calculate orientation
    float[] mGravity;
    float[] mGeomagnetic;

    //Used to ensure appropriate threading
    static final Integer monitor = 1;

	/**
	 * setup the various elements and put gameView into a variable for referencing
	 * @param gameView
	 */
	public GameThread(GameView gameView) {		
		mGameView = gameView;
		
		mSurfaceHolder = gameView.getHolder();
		mHandler = gameView.getmHandler();
		mContext = gameView.getContext();

		mBackgroundImage = BitmapFactory.decodeResource
				(gameView.getContext().getResources(),
						R.drawable.background);
	}
	
	/**
	 * Called when app is destroyed, so not really that important here
	 * But if (later) the game involves more thread, we might need to stop a thread, and then we would need this
	 * Dare I say memory leak...
	 */
	public void cleanup() {		
		this.mContext = null;
		this.mGameView = null;
		this.mHandler = null;
		this.mSurfaceHolder = null;
	}
	
	//Pre-begin a game

	/**
	 * used in "TheGame" classes
	 */
	abstract public void setupBeginning();
	
	//Starting up the game

	/**
	 * Starting the game (TheGame class), setting up scores and timer
	 */
	public void doStart() {
		synchronized(monitor) {
			
			setupBeginning();
			
			mLastTime = System.currentTimeMillis() + 100;

			setState(STATE_RUNNING);
			
			setScore(0);
		}
	}
	
	//The thread start

	/**
	 * Run the Thread, drawing to canvas etc.
	 */
	@Override
	public void run() {
		Canvas canvasRun;
		while (mRun) {
			canvasRun = null;
			try {
				canvasRun = mSurfaceHolder.lockCanvas(null);
				synchronized (monitor) {
					if (mMode == STATE_RUNNING) {
						updatePhysics();
					}
					doDraw(canvasRun);
				}
			} 
			finally {
				if (canvasRun != null) {
					if(mSurfaceHolder != null)
						mSurfaceHolder.unlockCanvasAndPost(canvasRun);
				}
			}
		}
	}
	
	/**
	 * Surfaces and drawing. Setting the canvas dimmensions and scaling the background image to fit the screen
	 */
	public void setSurfaceSize(int width, int height) {
		synchronized (monitor) {
			mCanvasWidth = width;
			mCanvasHeight = height;

			// don't forget to resize the background image
			mBackgroundImage = Bitmap.createScaledBitmap(mBackgroundImage, width, height, true);
		}
	}

	/**
	 * draw to canvas the background image. This image is used to clear the screen. (background just draws over current screen)
	 * ready for next draw stuff which happens in TheGame class
	 * @param canvas
	 */
	protected void doDraw(Canvas canvas) {
		
		if(canvas == null) return;

		if(mBackgroundImage != null) canvas.drawBitmap(mBackgroundImage, 0, 0, null);
	}

	/**
	 * updating calls updateGame which will be overriden in TheGame classes
	 */
	private void updatePhysics() {
		now = System.currentTimeMillis();
		elapsed = (now - mLastTime) / 1000.0f;

		updateGame(elapsed);

		mLastTime = now;
	}

	/**
	 * used in TheGame classes
	 * @param secondsElapsed
	 */
	abstract protected void updateGame(float secondsElapsed);
	
	/*
	 * Control functions
	 */

	/**
	 * When finger touches the screen do something depending on the state (start, pause). Send the touch location to
	 * ActionOnTouch which will be overriden in TheGame classes
	 * @param e
	 * @return
	 */
	//Finger touches the screen
	public boolean onTouch(MotionEvent e) {
		if(e.getAction() != MotionEvent.ACTION_DOWN) return false;
		
		if(mMode == STATE_READY || mMode == STATE_LOSE || mMode == STATE_WIN) {
			doStart();
			return true;
		}
		
		if(mMode == STATE_PAUSE) {
			unpause();
			return true;
		}
		
		synchronized (monitor) {
				this.actionOnTouch(e.getRawX(), e.getRawY());
		}
		 
		return false;
	}

	/**
	 * Overriden in TheGame Classes
	 * @param x
	 * @param y
	 */
	protected void actionOnTouch(float x, float y) {
		//Override to do something
	}

	/**
	 *Sensor movement. (Not used since it does not fit the game style)
	 */
	//The Orientation has changed
	@SuppressWarnings("deprecation")
	public void onSensorChanged(SensorEvent event) {
		synchronized (monitor) {

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                mGravity = event.values;
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                mGeomagnetic = event.values;
            if (mGravity != null && mGeomagnetic != null) {
                float R[] = new float[9];
                float I[] = new float[9];
                boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
                if (success) {
                    float orientation[] = new float[3];
                    SensorManager.getOrientation(R, orientation);
                    actionWhenPhoneMoved(orientation[2],orientation[1],orientation[0]);
                }
            }
		}
	}

	/**
	 * To be overriden in TheGame Classes
	 * @param xDirection
	 * @param yDirection
	 * @param zDirection
	 */
	protected void actionWhenPhoneMoved(float xDirection, float yDirection, float zDirection) {
		//Override to do something
	}
	
	/**
	 * Set Pause Game state when paused
	 */
	public void pause() {
		synchronized (monitor) {
			if (mMode == STATE_RUNNING) setState(STATE_PAUSE);
		}
	}

	/**
	 * upause the game and reposition the timer to now.
	 */
	public void unpause() {
		// Move the real time clock up to now
		synchronized (monitor) {
			mLastTime = System.currentTimeMillis();
		}
		setState(STATE_RUNNING);
	}

	/**
	 * messages to Activity
	 * @param mode
	 */
	//Send messages to View/Activity thread
	public void setState(int mode) {
		synchronized (monitor) {
			setState(mode, null);
		}
	}

	/**
	 * Setting the game state based on message from activity
	 * @param mode
	 * @param message
	 */
	public void setState(int mode, CharSequence message) {
		synchronized (monitor) {
			mMode = mode;

			if (mMode == STATE_RUNNING) {
				Message msg = mHandler.obtainMessage();
				Bundle b = new Bundle();
				b.putString("text", "");
				b.putInt("viz", View.INVISIBLE);
				b.putBoolean("showAd", false);	
				msg.setData(b);
				mHandler.sendMessage(msg);
			} 
			else {				
				Message msg = mHandler.obtainMessage();
				Bundle b = new Bundle();
				
				Resources res = mContext.getResources();
				CharSequence str = "";
				if (mMode == STATE_READY);
					//str = res.getText(R.string.mode_ready);
				else 
					if (mMode == STATE_PAUSE)
						str = res.getText(R.string.mode_pause);
					else 
						if (mMode == STATE_LOSE)
							str = res.getText(R.string.mode_GameOver);
						else 
							if (mMode == STATE_WIN) {
								str = res.getText(R.string.mode_win);
							}

				if (message != null) {
					str = message + "\n" + str;
				}

				b.putString("text", str.toString());
				b.putInt("viz", View.VISIBLE);

				msg.setData(b);
				mHandler.sendMessage(msg);
			}
		}
	}
	
	/*
	 * Getter and setter
	 */	
	public void setSurfaceHolder(SurfaceHolder h) {
		mSurfaceHolder = h;
	}
	
	public boolean isRunning() {
		return mRun;
	}

	/**
	 * On or Off
	 * @param running
	 */
	public void setRunning(boolean running) {
		mRun = running;
	}

	/**
	 * mode for states
	 * @return
	 */
	public int getMode() {
		return mMode;
	}

	public void setMode(int mMode) {
		this.mMode = mMode;
	}
	
	
	/* ALL ABOUT SCORES */
	
	//Send a score to the View to view 
	//Would it be better to do this inside this thread writing it manually on the screen?

	/**
	 * send score to the view to show to the user
	 * @param score
	 */
	public void setScore(long score) {
		this.score = score;
		
		synchronized (monitor) {
			Message msg = mHandler.obtainMessage();
			Bundle b = new Bundle();
			b.putBoolean("score", true);
			b.putString("text", getScoreString().toString());
			msg.setData(b);
			mHandler.sendMessage(msg);
		}
	}

	public float getScore() {
		return score;
	}

	/**
	 * Add to the current score and send to view
	 * @param score
	 */
	public void updateScore(long score) {
		this.setScore(this.score + score);
	}

	/**
	 * get score as a charSequence
	 * @return
	 */
	protected CharSequence getScoreString() {
		return Long.toString(Math.round(this.score));
	}
	
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