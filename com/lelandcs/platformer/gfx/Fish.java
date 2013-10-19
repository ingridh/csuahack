package com.lelandcs.platformer.gfx;

import com.lelandcs.platformer.Date;
import com.lelandcs.platformer.gfx.gui.Image;
import java.awt.Graphics2D;

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
		
		image = new Image("./fish.png");
		image.x = 0; //Get random x and y coords for 
		image.y = 0;
		state = State.FADEIN; 
		
		
				
	}
	
	public void update() {
		//currTime += time;
		if (state == State.DIE) {
			state = State.FADEOUT; //If it has died in the last cycle begin fadeout! 
		}
		if (currTime >= timeInterval) {
			state = State.DIE; // Once the time's up this fish dies!
		}
		
		
		
	}
	
	public void draw(Graphics2D g) {
		image.render(g);
		
	}
	

	
	
}