package com.lelandcs.platformer.gfx;

import com.lelandcs.platformer.Date;
import com.lelandcs.platformer.TaskManager;
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
    
    public FishManager() {
        fishies = new ArrayList<Fish>();
        bounds = new Rectangle(80, 60, 480, 420);
        taskMan = new TaskManager(this);
    }
    
    public void sendAddTask() {
        taskMan.createNew();
    }
    
    public void addFish(String name, Date d) {
        fishies.add(new Fish(name, d, this));
    }
    
    public void removeFish(Fish f) {
        fishies.remove(f);
    }
    
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
