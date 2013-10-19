package com.lelandcs.platformer.gfx;

import com.lelandcs.platformer.Date;
import com.lelandcs.platformer.TaskManager;
import com.lelandcs.platformer.states.FishBowl;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Paymon
 */
public class FishManager {
    
    private ArrayList<Fish> fishies;
    
    private Rectangle bounds;
    private TaskManager taskMan;
    private FishBowl bowl;
    
    public FishManager(FishBowl bowl) {
        fishies = new ArrayList<Fish>();
        bounds = new Rectangle(130, 100, 390, 255);
        taskMan = new TaskManager(this);
        this.bowl = bowl;
    }
    
    public void sendAddTask() {
        taskMan.createNew();
    }
    
    // also not thread safe, whatever
    public void addFish(String name, Date d) {
        Fish f = new Fish(name, d, this);
        fishies.add(f);
        bowl.uiEntities.add(f.image);
    }
    
    // not thread safe
    /*public void removeFish(Fish f) {
        fishies.remove(f);
    }*/
    
    public Rectangle getBounds() {
        return bounds;
    }
    
    public ArrayList<Fish> getFish() {
        return fishies;
    }
    
    public void update() {
        for (Fish f : fishies) {
            f.update();
        }
    }
    
    public void render(Graphics2D g) {
        for (Fish f : fishies) {
            f.draw(g);
        }
    }
}
