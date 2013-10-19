package com.lelandcs.platformer.states;

import com.lelandcs.platformer.gfx.Fish;
import com.lelandcs.platformer.gfx.Fish.State;
import com.lelandcs.platformer.gfx.FishImage;
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
        new ParticleStream(200, 230, 105, 75, 1, 1,2, 1.5f, "./bubble.png", false, true),
        new ParticleStream(260, 320, 100, 80, 1, 1,2, 1.9f, "./bubble.png", false, true),
        new ParticleStream(420, 300, 105, 75, 1, 1,3, 2.5f, "./bubble.png", false, true)
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
        
        loadUI(); 
        fishman = new FishManager(this);
    }
    
    private void loadUI() {
        uiEntities.add(new Image("./bowl2.png"));
        uiEntities.add(new Button(master.CWIDTH - 35, 0, Color.CYAN, Color.BLACK, "X", master.fonts.get("Arial"), 35, 30));
        // add recovery stuff
        
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
    
    boolean dragging = false;
    int ix = 0, iy = 0;
    public void handleMousePress(int x, int y) {
        dragging = true;
        ix = x;
        iy = y;
    }
    
    public void handleMouseRelease(int x, int y) {
        if (dragging &&
            ((x - ix) > 5) && ((y-iy)>5)) {
            System.out.println(x + " " +  y);
            master.app.setLocation(master.app.getX() + x - ix, master.app.getY() + y - iy);
            dragging = false;
            return;
        }
        dragging = false;
        
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
            else if (last instanceof FishImage) {
                FishImage i = (FishImage) last;
                Fish f = i.f;
                f.setState(State.FADEOUT);
            }
            else if (last instanceof Image) {
                Image i = (Image) last;
                if (i.name.equals("./bowl2.png")) {
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
            else if (e instanceof FishImage) {
                FishImage i = (FishImage) e;
                i.checkForClick(x, y);
            }
            else if (e instanceof Image) {
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
