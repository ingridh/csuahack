package com.lelandcs.platformer.states;

import com.lelandcs.platformer.gfx.FishManager;
import com.lelandcs.platformer.gfx.PlatformerCanvas;
import com.lelandcs.platformer.gfx.gui.Button;
import com.lelandcs.platformer.gfx.gui.Image;
import com.lelandcs.platformer.gfx.gui.UIEntity;
import com.lelandcs.platformer.gfx.particlesystem.ParticleStream;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 */
public class FishBowl extends GameState {
    
    private FishManager fishman;
    
    private ParticleStream[] bubbles = {
        new ParticleStream(200, 230, 105, 75, 1, 1,3, 1.5f, "./bubble.png", false, true),
        new ParticleStream(260, 320, 100, 80, 1, 1,2, 1.9f, "./bubble.png", false, true),
        new ParticleStream(400, 300, 105, 75, 1, 1,3, 2.5f, "./bubble.png", false, true)
    };
    
    public FishBowl(PlatformerCanvas master) {
        super(master); 
        init();
    }
    
    public void init() {
        super.init();
        // startup code here
        for (ParticleStream s : bubbles)
            s.start();
        fishman = new FishManager();
        
        loadUI(); 
    }
    
    private void loadUI() {
        uiEntities.add(new Image("./bowl.png"));
        uiEntities.add(new Button(master.CWIDTH - 30, 0, Color.YELLOW, Color.BLACK, "X", master.fonts.get("Arial"), 30, 30));
    }
    
    public void update() {
        for (ParticleStream s : bubbles)
            s.update();
        fishman.update();
        
    }
    
    public void render(Graphics2D g) {
        //g.setFont(master.fonts.get("Arial"));
	//g.setColor(Color.white);
        //g.drawString("In the Game Menu!", 300 , 300);
        for (UIEntity e : uiEntities) {
            e.render(g);
        }
        
        fishman.render(g);
        
        for (ParticleStream s : bubbles)
            s.render(g);
    }
 
    public void handleMousePress(int x, int y) {
        
    }
    
    public void handleMouseRelease(int x, int y) {
        UIEntity last = null;
        for (UIEntity e : uiEntities) {
            if (e.focused) {
                last = e;
            }
        }
        
        if (last != null) {
            System.out.println("Clicked on " + last.name + "!");
            if (last instanceof Button) {
                Button b = (Button) last;
                if (b.name.equals("X")) {
                    master.app.exit();
                }
            }
            else if (last instanceof Image) {
                Image i = (Image) last;
                if (i.name.equals("./bowl.png")) {
                    fishman.sendAddTask();
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
