package com.lelandcs.platformer.gfx.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A basic button
 */
public class Button extends UIEntity {
    /* The offset between button's left edge and the start of the text */
    public int xoffset = 10;
    /* The offset between button's bottom edge and the bottom of the text */
    public int yoffset = 10;
    
    /* The clickable region */
    private Rectangle rec;
    
    /* The colors of the button */
    public Color buttonColor;
    public Color highlightColor;
    public Color textColor;

    public Font font;
    
    public Button(int x, int y, Color buttonColor, 
            Color textColor, String name, Font font) {
        this.x = x;
        this.y = y;
        
        this.width = name.length() * font.getSize();
        this.height = 10 + font.getSize();
        
        this.buttonColor = buttonColor;
        highlightColor = buttonColor.brighter();
        this.textColor = textColor;
        this.name = name;
        this.font = font;
        
        rec = new Rectangle(x, y, width, height);
    }
    
    public void checkForClick(int mousex, int mousey) {
        if (rec.contains(mousex, mousey)) {
            focused = true;
        }
        else {
            focused = false;
        }
    }
    
    public void render(Graphics g) {
        Color initial = g.getColor(); // preserve old color
        
        if (focused) {
            g.setColor(highlightColor);
        }
        else {
            g.setColor(buttonColor);
        }
        g.fillRect(x, y, width, height);
       
        g.setColor(textColor);
        g.setFont(font);
        g.drawString(name, x + xoffset, y + height - yoffset);
        
        g.setColor(initial);
    }
}
