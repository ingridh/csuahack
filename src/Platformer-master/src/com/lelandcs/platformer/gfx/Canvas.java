package com.lelandcs.platformer.gfx;

import javax.swing.JPanel;

/*
 * A basic threaded canvas meant to be used for most 2D games.
 */
public abstract class Canvas extends JPanel implements Runnable {
    
        /* Number of frames with a delay of 0 ms before the animation thread yields
        to other running threads. */
	private static final int YIELD_PERIOD = 15; 

        /* if it lags, max number of updates it can do before rendering */
	private static int MAX_FRAME_SKIPS = 5;
	
	public long initGameTime = 0;
	
	private long period = -1L;  // in nanoseconds
	
	public Thread gameThread;
	
	private volatile boolean running = false; // indicates whether the game thread is running
	
	public void setDesiredFPS(int fps) {	
		setPeriod(1000000000L/fps); // fps -> ns per frame
	}
        
        public int getDesiredFPS() {
            return (int) (1000000000L/period);
        }
	
	public void addNotify(){ 
		super.addNotify();  
                startGame();         
	}


	public void startGame() { 
		if (gameThread == null || !running) {
			gameThread = new Thread(this);
		  	gameThread.start();
		}
	}
	
	/* Set this to false to stop execution of the game */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public boolean getRunning() {
		return running;
	}
	
	public void setPeriod(long period) {
		this.period = period;
	}
	
	public long getPeriod() {
		return period;
	}
	
	public void run() {
		requestFocus();
		
		if (period == -1L) {
			System.err.println("Canvas - period is -1. Please set a value for the period.");
			System.exit(1);
		}
		
                long beforeTime, afterTime, timeDiff, sleepTime;
                long overSleepTime = 0L;
                int noDelays = 0;
                long excess = 0L;
	    
                initGameTime = System.nanoTime();
                beforeTime = System.nanoTime();

		running = true;

		while(running) {
                    update();
                    render();

                    afterTime = System.nanoTime();
                    timeDiff = afterTime - beforeTime;
                    sleepTime = (period - timeDiff) - overSleepTime;  

                    if (sleepTime > 0) {   // some time left in this cycle
                        try {
                            Thread.sleep(sleepTime/1000000L);  // nano -> ms
                        } catch(InterruptedException ex){}
                        overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
                    }
                    else {    // sleepTime <= 0; the frame took longer than the period
                        excess -= sleepTime;  // store excess time value
                        overSleepTime = 0L;

                        if (++noDelays >= YIELD_PERIOD) {
                            Thread.yield();   // give another thread a chance to run
                            noDelays = 0;
                        }
                    }

                    beforeTime = System.nanoTime();

                    int skips = 0;
                    while((excess > period) && (skips < MAX_FRAME_SKIPS)) {
                        excess -= period;
                        update();
                        skips++;
                    }
                }
	}
	
	public abstract void update();
	
	public abstract void render();
}