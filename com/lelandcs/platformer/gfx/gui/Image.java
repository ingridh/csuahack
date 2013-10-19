package com.lelandcs.platformer.gfx.gui;

import com.lelandcs.platformer.gfx.particlesystem.Particle;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Paymon
 */
public class Image extends UIEntity {
    private BufferedImage img;
    
    public float a = 1f;
    
    private Rectangle rec;
    
    public Image(String path) {
        URL url = Particle.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(url);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        this.height = img.getHeight();
        this.width = img.getWidth();
        this.name = path;
        
        rec = new Rectangle((int)x, (int)y, width, height);
    }
    
    public void flip(boolean hor) {
        if (!hor) {
            AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
            tx.translate(0, -img.getHeight(null));
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            img = op.filter(img, null);
        }
        else {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-img.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            img = op.filter(img, null);
        }
    }
    
    public void checkForClick(int mousex, int mousey) {
        if (a <= 0) {
            focused = false;
            return;
        }
        rec.x = (int)x;
        rec.y = (int)y;
        if (rec.contains(mousex, mousey)) {
            focused = true;
        }
        else {
            focused = false;
        }
    }
    
    public void render(Graphics2D g) {
        Composite c = g.getComposite();
        g.setComposite( AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));

        g.drawImage(img, (int)x, (int)y, null);
        g.setComposite(c);
    }
}
