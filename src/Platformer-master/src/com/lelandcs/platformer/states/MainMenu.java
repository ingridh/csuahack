package com.lelandcs.platformer.states;

import com.lelandcs.platformer.gfx.PlatformerCanvas;
import com.lelandcs.platformer.gfx.gui.Button;
import com.lelandcs.platformer.gfx.gui.UIEntity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 */
public class MainMenu extends GameState {
    
    public MainMenu(PlatformerCanvas master) {
        super(master); 
    }
    
    public void init() {
        super.init();
        // startup code here
        loadUI(); 
    }
    
    private void loadUI() {
        uiEntities.add(new Button(350, 350, Color.GRAY, Color.WHITE, "Test", master.fonts.get("Arial")));
    }
    
    public void update() {
        
    }
    
    public void render(Graphics g) {
        g.setFont(master.fonts.get("Arial"));
	g.setColor(Color.white);
        g.drawString("In the Game Menu!", 300 , 300);
        
        for (UIEntity e : uiEntities) {
            e.render(g);
        }
    }
 
    public void handleMousePress(int x, int y) {
        
    }
    
    public void handleMouseRelease(int x, int y) {
        for (UIEntity e : uiEntities) {
            if (e instanceof Button) {
                Button b = (Button) e;
                if (b.focused) {
                    System.out.println("Clicked on " + b.name + "!");
                }
            }
        }
    } 
    
    public void handleMouseMotion(int x, int y) {
        for (UIEntity e : uiEntities) {
            if (e instanceof Button) {
                Button b = (Button) e;
                b.checkForClick(x, y);
            }
        }
    }
    
    public void handleKeyPress(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            master.app.exit();
        }
    }
    
    public void handleKeyRelease(int code) {
        
    }
}
