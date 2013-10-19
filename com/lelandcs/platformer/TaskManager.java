package com.lelandcs.platformer;

import com.lelandcs.platformer.gfx.FishManager;
import javax.swing.JFrame;

/**
 *
 * @author Paymon
 */
public class TaskManager {
    
    private JFrame currWindow; 
    private FishManager fishman;
    
    
    public TaskManager(FishManager fishman) {
        this.fishman = fishman;  
    }
    
    public void createNew() {
        currWindow = new JFrame("Create new task");
        currWindow.add(new TaskPanel(this));
        currWindow.pack();
        currWindow.setResizable(false);
        currWindow.setLocationRelativeTo(null);
        currWindow.setVisible(true);
        currWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void pressedOk(String s, Date d) {
        fishman.addFish(s, d);
        currWindow.dispose();
        currWindow = null;
    }
}
