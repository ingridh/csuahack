package com.lelandcs.platformer.gfx;

import com.lelandcs.platformer.Date;
import com.lelandcs.platformer.gfx.gui.Image;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.lang.Object*;

public class Fish{
	
	public enum State{
		SWIM, FADEIN, DIE, FADEOUT
	}
	State state;
	String fishName; 
	Image image; 
	FishManager manager;
	float currTime = 0f;
	float timeInterval = 0f;
	
	
	public Fish(String name, Date date, FishManager manager) {
		
		image = new Image("fish.png");
		image.x = 0; //Get random x and y coords for 
		image.y = 0;
		state = State.FADEIN; 			
	}
	
	public void update() {
		//currTime += time;
		if (state == State.DIE) {
			image.diry += 1; //floats fish to the top
			if (image.y == rectangle.y) {
			state = State.FADEOUT; //If it has died in the last cycle begin fadeout! 
			}
		}
		if (currTime >= this.expiredDate) {
			state = State.DIE; // Once the time's up this fish dies!
		}
		else if (state == FADEIN) {
			state = SWIM;
		} else {
			if (image.x <= rectangle.x) || (image.x >= rectangle.x + width) { //moves fish back and forth the bowl
				image.dirx = x(-1)*(image.dirx);
			image.diry = Math.random()/3.0f; //to scale down the y movement 
		} if (image.y <= rectangle.y -length || (image.y >= rectangle.y)) {
			image.diry = -image.diry;

		}
		
		
	}
	
	public void draw(Graphics2D g) {
		image.render(g);
		
	}
	

	
	
}