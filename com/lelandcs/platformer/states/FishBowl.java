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
public class FishBowl extends GameState {
    
    public FishBowl(PlatformerCanvas master) {
        super(master); 
    }
    
    public void init() {
        super.init();
        // startup code here
        loadUI(); 
    }
    
    private void loadUI() {
        uiEntities.add(new Button(370, 0, Color.YELLOW, Color.BLACK, "x", master.fonts.get("Arial"), 30, 30));
    }
    
    public void update() {
        
    }
    
    public void render(Graphics g) {
        //g.setFont(master.fonts.get("Arial"));
	//g.setColor(Color.white);
        //g.drawString("In the Game Menu!", 300 , 300);
        
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
                    //System.out.println("Clicked on " + b.name + "!");
                    if (b.name.equals("x")) {
                        master.app.exit();
                    }
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
