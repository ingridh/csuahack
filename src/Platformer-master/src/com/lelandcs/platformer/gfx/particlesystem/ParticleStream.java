package com.lelandcs.platformer.gfx.particlesystem;

import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 */
public class ParticleStream {
    private int a1, a2;

    private final int max;
    private final int life;
    private final int speed;
    private final float period;

    private Particle[] p;

    private int x, y;

    public boolean done = false;

    private ArrayList<Particle> buffer;

    private float start;

    private boolean alpha, size;

    public ParticleStream(int x, int y, int a1, int a2, int speed,
            int amount, int particle_life,float period, String str
            ,boolean alpha, boolean size) {
        this.speed = speed;
        this.period = period;
        this.a1 = a1;
        this.a2 = a2;
        max = amount;
        life = particle_life;
        this.x = x;
        this.y = y;

        this.alpha = alpha;
        this.size = size;

        buffer = new ArrayList<Particle>();

        URL url = Particle.class.getClassLoader().getResource(str);
        Particle im = null;
        try {
            im = new Particle(ImageIO.read(url), x, y, life, false);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        p = new Particle[max];
        for (int i = 0; i<p.length;i++) {
            p[i] = im.clone();
            int ang = rand(a2 - a1);
            ang += a1;
            p[i].setAngle(ang);
            buffer.add(p[i]);
        }
        im = null;

        done = true;
    }

    public void stop() {
        done = true;
        buffer = new ArrayList<Particle>();
        for (Particle i : p) {
            i.x = x;
            i.y = y;
            int ang = rand(a2 - a1);
            ang += a1;
            i.setAngle(ang);
            buffer.add(i);
        }
    }

    public void start() {
        for (Particle i : p) {
            i.revive();
        }
        start = (float) System.nanoTime()/1000000000;
        done = false;
    }

    public void update() {
        if (done)
            return;

        if ((((float) System.nanoTime()/1000000000) - start) > period) {
            if (!buffer.isEmpty()) {
                buffer.get(buffer.size()-1).revive();
                buffer.remove(buffer.size()-1);
                start = (float) System.nanoTime()/1000000000;
            }
        }

        for (Particle i : p) {
            if (i.isDead()) {
                if (!buffer.contains(i)) {
                    buffer.add(i);
                    i.x = x;
                    i.y = y;
                    int ang = rand(a2 - a1);
                    ang += a1;
                    i.setAngle(ang);
                }
            }
            else {
                if (!buffer.contains(i)) {
                    i.x += calc_move_x(i.getAngle());
                    i.y += calc_move_y(i.getAngle());
                    float r = 1 - (float) i.getAge()/i.getLifeSpan();
                    if (alpha) i.setAlpha(r);
                    else i.setAlpha(1);
                    if (size) i.setScale(r);
                    else i.setScale(1);
                }
            }
        }
    }

    public void render(Graphics2D g) {
        if (done)
            return;

        for (Particle i : p) {
            if (!buffer.contains(i))
                i.draw(g);
        }
    }

    private int rand(int max) { //0-max
        return (int) (Math.random() * (max+1));
    }

    private float calc_move_x(int ang) {
        return (float) (speed * Math.cos(Math.toRadians(ang)));
    }

    private float calc_move_y(int ang) {
        return (float) (speed * -Math.sin(Math.toRadians(ang)));
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        for (Particle i : p) {
            i.x = x;
            i.y = y;
        }
    }
}