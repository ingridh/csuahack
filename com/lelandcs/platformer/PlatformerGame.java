package com.lelandcs.platformer;

import com.lelandcs.platformer.gfx.PlatformerCanvas;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 * The entry point for the game
 */
public class PlatformerGame extends JFrame implements WindowListener {
        
        private static final int FPS = 60;
        
	private PlatformerCanvas canvas;

	public PlatformerGame() {
		super("FishStuff"); // argument - title of window
        this.setUndecorated(true); //Removes the window

        canvas = new PlatformerCanvas(this, FPS);
        add(canvas); // add the game graphics panel to the JFrame
        
        pack(); // fixes window size

        addWindowListener(this); // keep track of events on the window

        setResizable(false); // sets the window unresizable

        setVisible(true); // sets the window visible
        
        this.setBounds(100, 100, 400, 400);
	}
        
        /* Start point */
        public static void main(String[] args) {
            System.out.println("Platformer Game");
            PlatformerGame game = new PlatformerGame();
	}
        
        public void exit() {
            canvas.setRunning(false);
            System.exit(0);
        }

	public void windowActivated(WindowEvent e) {}

	public void windowClosed(WindowEvent e) {}

	public void windowClosing(WindowEvent e) {
            exit();
	}

	public void windowDeactivated(WindowEvent e) {}

	public void windowDeiconified(WindowEvent e) {}

	public void windowIconified(WindowEvent e) {}

	public void windowOpened(WindowEvent e) {}
}
