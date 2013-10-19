package com.lelandcs.platformer.gfx.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 */
public abstract class UIEntity {
    /* The position and dimensions of the entity */
    public int x;
    public int y;
    public int height;
    public int width;
    
    public String name;
    public boolean enabled = true;
    
    /* Indicates if this entity is focused */
    public boolean focused = false;
    
    public abstract void render(Graphics2D g);
}
