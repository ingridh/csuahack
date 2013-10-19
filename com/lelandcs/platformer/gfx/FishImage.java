package com.lelandcs.platformer.gfx;

import com.lelandcs.platformer.gfx.gui.Image;


/**
 *
 * @author Paymon
 */
public class FishImage extends Image {
    public Fish f;  
    public FishImage(String s, Fish f) {
        super(s);
        this.f = f;
    }
}
