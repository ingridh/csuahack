package com.lelandcs.platformer.gfx;
 
import com.lelandcs.platformer.Date;
import com.lelandcs.platformer.gfx.gui.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Calendar;
 
public class Fish {
     
    public enum State{
       DEAD, SWIM, FADEIN, DIE, FADEOUT
    }
    State state;
    
    String fishName; 
    FishImage image; 
    Font font;
    FishManager manager;
    Date expirationTime;
    
    int dirx = 0;
    int diry = 0;
    float magx = 0.5f;
    float magy = 0.5f;
    
    int xdist = -1;
    
     
    public Fish(String name, Date date, FishManager manager) {
        this.manager = manager;
        Rectangle rectangle = manager.getBounds();
        image = new FishImage("./fish.png", this);
        
        image.x = (float)(Math.random()*(rectangle.width - 10 - image.width) + rectangle.x); 
        image.y = (float)(Math.random()*(rectangle.height - 10 - image.height) + rectangle.y);
        
        if (image.x < (rectangle.x + (rectangle.width/2.f))) {
            dirx = 1;
            image.flip(true);
        }
        else {
            dirx = -1;
        }
        
        if (dirx == 1) {
            xdist = (int) (Math.random() * (rectangle.x + rectangle.width - image.x - 30) + 20);
        }
        else {
            xdist = (int) (Math.random() * (image.x - rectangle.x - 30) + 20);
        }
        
        state = State.FADEIN; 
        image.a = 0;
        
        expirationTime = date;
        
        fishName = name;
        
        font = PlatformerCanvas.fonts.get("Monospaced");
    }
     
    public void update() {
        Rectangle rectangle = manager.getBounds();
        
        switch (state) {
            case DEAD:
                magx = 0;
                magy = 0;
                dirx = 0;
                diry = 0;
                break;
            case DIE:
                diry = -1; //floats fish to the top
                magy = 0.6f;
                dirx = 0;
                if (image.y <= rectangle.y - 15) {
                    image.flip(false);
                    diry = 0;
                    state = State.DEAD;
                }
                break;
            case FADEOUT:
                magx = 0;
                magy = 0;
                image.a -= 0.01f;
                if (image.a <= 0) {
                    image.a = 0;
                    //manager.removeFish(this);
                    return;
                }
                break;
            case FADEIN:
                magx = 0;
                magy = 0;
                image.a += 0.01f;
                if (image.a >= 1) {
                    state = State.SWIM;
                    image.a = 1;
                    magx = 0.5f;
                    magy = (float)(Math.random() * 0.2);
                }
                break;
            case SWIM:
                checkDates();
                
                xdist -= magx;
                
                if (image.y <= rectangle.y) {
                    diry = 1;
                }
                else if (image.y + image.height >= rectangle.y + rectangle.height) {
                    diry = -1;
                }
                
                if (xdist <= 0) {
                    dirx *= -1;
                    image.flip(true);
                    magy = (float)(Math.random() * 0.2);
                    diry = ((int) (Math.random() * 2)) == 0 ? -1 : 1;
                    if (dirx == 1) {
                        xdist = (int) (Math.random() * (rectangle.x + rectangle.width - image.x - 30) + 20);
                    }
                    else {
                        xdist = (int) (Math.random() * (image.x - rectangle.x - 30) + 20);
                    }
                }  
                break;
        } 
        
        image.x += dirx * magx;
        image.y += diry * magy;
    }
    
    // do not call unless you know exactly what you are doing
    public void setState(State s) {
        this.state = s;
    }
     
    public void draw(Graphics2D g) {
        if (image.a <= 0) {
            return;
        }
        image.render(g);
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        //g.setFont(font);
        g.drawString(fishName, image.x - 5, image.y - 5);
        g.setColor(c);
    }
     
    public void checkDates() {
        Calendar calender = Calendar.getInstance();
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        int hour = calender.get(Calendar.HOUR_OF_DAY);
        Date d = new Date("" + hour, "" + day, "" + month);
        if (expirationTime.compareTo(d) <= 0) {
            state = State.DIE;
        }
    }
}