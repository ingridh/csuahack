package com.lelandcs.platformer.gfx;

import com.lelandcs.platformer.PlatformerGame;
import com.lelandcs.platformer.states.FishBowl;
import com.lelandcs.platformer.states.GameState;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.HashMap;

/**
 * This is the panel that gets the graphics rendered on
 * It can be added to a JFrame or made into an Applet
 */
public class PlatformerCanvas extends Canvas {
    public static final int CHEIGHT = 480; // The dimensions of the panel
    public static final int CWIDTH = 640;
    
    /* Keep track of the current state */
    public FishBowl currentState;
    
    private Graphics2D dbg; 
    private Image dbImage;
    
    public static HashMap<String, Font> fonts;
    
    public PlatformerGame app;
    
    public PlatformerCanvas(PlatformerGame app, int fps) {
        this.app = app;
        
        System.out.println("Desired FPS: " + fps);
        setDesiredFPS(fps);
		
        // account for window insets
	setPreferredSize(new Dimension(CWIDTH-10, CHEIGHT-10));
		
	setFocusable(true);
	requestFocus();
        
        loadFonts();
        
        setState("bowl");
    }
    
    /* Switches the state and loads it */
    private void setState(String state) {
        // remove listeners on the old state
        if (currentState != null) {
            removeKeyListener(currentState.keyAdapter);
            removeMouseMotionListener(currentState.motionAdapter);
            removeMouseListener(currentState.mouseAdapter);
        }
        
        if (state.equals("bowl")) {
            currentState = new FishBowl(this);
        }
    }
    
    private void loadFonts() {
        fonts = new HashMap<String, Font>();
        Font f1 = new Font("Monospaced", Font.PLAIN, 24);
        fonts.put(f1.getFamily(), f1);
    }

    public void update() {
        currentState.update();
    }
    
    public void render() {
        doDoubleBufferedRender();
    }

    /*
     * Uses a double buffering technique
     * First, render the graphics onto a temporary image (dbImage)
     * Then, render that image onto the panel
     * (Instead of rendering the graphics directly on)
     * (Avoids flickering)
     */
    public void doDoubleBufferedRender() {
            if (dbImage == null){
                dbImage = createImage(CWIDTH, CHEIGHT);
                if (dbImage == null) {
                    System.err.println("dbImage is null");
                    return;
                }
                else {
                    dbg = (Graphics2D) dbImage.getGraphics();
                }
	    }
	    dbg.setRenderingHint
        	(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

            dbg.setColor(Color.WHITE);
	    dbg.fillRect(0, 0, CWIDTH, CHEIGHT);
            
            currentState.render(dbg); // render the current state's graphics
	    
	    Graphics g;
	    try {
                g = getGraphics();
                if ((g != null) && (dbImage != null)) {
                    g.drawImage(dbImage, 0, 0, null);
                }
                g.dispose();
	    }
	    catch (Exception e)
	    { 
                e.printStackTrace();
            }
    }
}
