package com.lelandcs.platformer.gfx.particlesystem;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * 
 */
public class Particle implements Cloneable {
    private int life_span;

    private float age = 0;

    private BufferedImage origimg, img, render;

    public float x = 0;
    public float y = 0;

    private long nstart;

    private boolean dead = false;
    private boolean rotate = false;

    private float a = 1;

    private int angle;

    public boolean isDead() {
        return dead;
    }

    public void revive() {
        age = 0;
        a = 1;
        clearImage(render);
        nstart = System.nanoTime();
        dead = false;
    }
    
    public Particle(BufferedImage image, float x, float y, int life_span
            , boolean rotate) {
        img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        img.setData(image.getData());
        origimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        origimg.setData(image.getData());

        render = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        clearImage(render);

        this.rotate = rotate;

        this.x = x;
        this.y = y;

        this.life_span = life_span;
        if (life_span < 1) {
            this.life_span  = 1;
        }

        nstart = System.nanoTime();
    }
    
    public void draw(Graphics2D g) {
        if (dead)
            return;

        age = (float) ((System.nanoTime() - nstart) / (float)1000000000L);
        if (age >= life_span) {
            dead = true;
            return;
        }

        //g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
             //   RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        Composite c = g.getComposite();
        g.setComposite( AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));

        g.drawImage(render, (int)x, (int)y, null);
        g.setComposite(c);

        clearImage(render);
    }

    public void setAlpha(float a) {
        this.a = a;
    }


    public void setScale(float percentage) {
        Graphics2D g = render.createGraphics();
        img.setData(origimg.getData());

        int w = (int) (img.getWidth() * percentage);
        int h = (int) (img.getHeight() * percentage);

        int finalx = img.getWidth()/2 - w/2;
        int finaly = img.getHeight()/2 - h/2;

        g.drawImage(img, finalx, finaly, w, h, null);
    }

    private void clearImage(BufferedImage bi) {
        int h = bi.getHeight();
        int w = bi.getWidth();

        WritableRaster wr = bi.getAlphaRaster().getWritableParent();

        for (int i=0; i < h; i++) {
            for (int j=0; j < w; j++) {
                wr.setSample(j, i, 0, 1);
                wr.setSample(j, i, 1, 1);
                wr.setSample(j, i, 2, 1);
                wr.setSample(j, i, 3, 0);
            }
        }
    }

    public Particle clone() {
        return new Particle(img, x, y, life_span, rotate);
    }

    public float getAge() {
        return age;
    }

    public int getLifeSpan() {
        return life_span;
    }

    public void setAngle(int ang) {
        angle = ang;
    }
    public int getAngle() {
        return angle;
    }
}