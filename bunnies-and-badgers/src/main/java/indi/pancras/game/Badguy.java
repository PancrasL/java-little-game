package indi.pancras.game;

import java.awt.Graphics;
import java.awt.Image;

/**
 * @author pancras
 * @version 1.0
 */
public class Badguy extends GameObject {
    boolean alive;

    public Badguy(Image img, int posX, int posY, int speed) {
        super(img, posX, posY, speed, img.getWidth(null), img.getHeight(null));
        alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public void drawSelf(Graphics g) {
        if (alive) {
            posX -= speed;
            if (posX < 0) {
                alive = false;
            } else {
                g.drawImage(img, posX, posY, null);
            }
        }

    }
}
