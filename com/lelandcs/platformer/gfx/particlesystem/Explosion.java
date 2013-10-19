package com.lelandcs.platformer.gfx.particlesystem;

import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Paymon
 */
public class Explosion {
    private int a1, a2;

    private final int max;
    private final int life;
    private final int speed;

    private Particle[] particles;

    private int x, y;

    public boolean done = false;

    public Explosion(int x, int y, int a1, int a2, int speed,
            int amount, int particle_life, String str) {
        this.speed = speed;
        this.a1 = a1;
        this.a2 = a2;
        max = amount;
        life = particle_life;
        this.x = x;
        this.y = y;

        URL url = Particle.class.getClassLoader().getResource(str);
        Particle im = null;
        try {
            im = new Particle(ImageIO.read(url), x, y, life, false);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        particles = new Particle[max];
        for (int i = 0; i<particles.length;i++) {
            particles[i] = im.clone();
            int ang = rand(a2 - a1);
            ang += a1;
            particles[i].setAngle(ang);
        }
        im = null;

        done = true;
    }

    public void reset() {
        done = true;
        for (Particle p : particles) {
            p.x = x;
            p.y = y;
            int ang = rand(a2 - a1);
            ang += a1;
            p.setAngle(ang);
        }
    }

    public void start() {
        for (Particle p : particles) {
            p.revive();
        }
        done = false;
    }
    
    public void update() {
        if (done) 
            return;
        int num_dead = 0;
        for (Particle p : particles) {
            if (p.isDead()) {
                num_dead++;
                if (num_dead == max) {
                    done = true;
                }
            }
            else {
                p.x += calc_move_x(p.getAngle());
                p.y += calc_move_y(p.getAngle());
                float r = 1 - (float) p.getAge()/p.getLifeSpan();
                p.setAlpha(r);
                p.setScale(r);
            }
        }
    }
    
    public void render(Graphics2D g) {
        if (done)
            return;

        for (Particle i : particles) {
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
        for (Particle p : particles) {
            p.x = x;
            p.y = y;
        }
    }
}