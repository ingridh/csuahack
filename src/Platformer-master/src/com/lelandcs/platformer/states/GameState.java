package com.lelandcs.platformer.states;

import com.lelandcs.platformer.gfx.PlatformerCanvas;
import com.lelandcs.platformer.gfx.gui.UIEntity;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

/**
 *
 */
public class GameState {
    protected PlatformerCanvas master;
    
    public KeyAdapter keyAdapter;
    public MouseAdapter mouseAdapter;
    public MouseMotionAdapter motionAdapter;
    
    public ArrayList<UIEntity> uiEntities;
    
    public GameState(PlatformerCanvas master) {
        this.master = master;
        init();
    } 
    
    public void init() {
        addInput();
        uiEntities = new ArrayList<UIEntity>();
    }
    
    public void addInput() {
        keyAdapter = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
	        handleKeyRelease(e.getKeyCode());
            }
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        };
        mouseAdapter =  new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                handleMouseRelease(e.getX(), e.getY());
            }
            
            public void mousePressed(MouseEvent e) {
                handleMousePress(e.getX(), e.getY());
            }
        };
        motionAdapter =  new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                handleMouseMotion(e.getX(), e.getY());
            }
        };
         
        master.addKeyListener(keyAdapter);
        master.addMouseMotionListener(motionAdapter);
        master.addMouseListener(mouseAdapter);
    }
    
    public void update() {

    }
    
    public void render(Graphics g) {
        
    }
    
    public void handleMousePress(int x, int y) {
        
    }
    
    public void handleMouseRelease(int x, int y) {
        
    }
    
    public void handleMouseMotion(int x, int y) {
        
    }
    
    public void handleKeyPress(int code) {
        
    }
    
    public void handleKeyRelease(int code) {
        
    }
}
