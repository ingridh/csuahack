package com.lelandcs.platformer.states;

import com.lelandcs.platformer.gfx.FishManager;
import com.lelandcs.platformer.gfx.PlatformerCanvas;
import com.lelandcs.platformer.gfx.gui.Button;
import com.lelandcs.platformer.gfx.gui.Image;
import com.lelandcs.platformer.gfx.gui.UIEntity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 */
public class FishBowl extends GameState {
    
    private FishManager fishman;
    private Image bowl;
    
    public FishBowl(PlatformerCanvas master) {
        super(master); 
        init();
    }
    
    public void init() {
        super.init();
        // startup code here
        fishman = new FishManager();
        
        loadUI(); 
    }
    
    private void loadUI() {
        uiEntities.add(new Image("./bowl.png"));
        uiEntities.add(new Button(master.CWIDTH - 30, 0, Color.YELLOW, Color.BLACK, "x", master.fonts.get("Arial"), 30, 30));
    }
    
    public void update() {
        fishman.update();
        
    }
    
    public void render(Graphics2D g) {
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
                    System.out.println("Clicked on " + b.name + "!");
                    if (b.name.equals("x")) {
                        master.app.exit();
                    }
                }
            }
            else if (e instanceof Image) {
                Image i = (Image) e;
                if (i.focused) {
                    System.out.println("Clicked on " + i.name + "!");
                    if (i.name.equals("./bowl.png")) {
                        fishman.sendAddTask();
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
            if (e instanceof Image) {
                Image i = (Image) e;
                i.checkForClick(x, y);
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
