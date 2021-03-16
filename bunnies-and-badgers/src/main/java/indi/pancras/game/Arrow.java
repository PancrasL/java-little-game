package indi.pancras.game;

import java.awt.Graphics;
import java.awt.Image;

/**
 * @author pancras
 * @version 1.0
 */
public class Arrow extends GameObject {
    private boolean alive;
    //弓箭的飞行方向
    private double degree;

    public Arrow(Image img, int posX, int posY, int speed, double degree) {
        super(img, posX, posY, speed, img.getWidth(null), img.getHeight(null));
        this.degree = degree;
        this.alive = true;
        this.img = GameUtil.rotate(img, degree);
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
            posX += (int) (speed * Math.cos(degree));
            posY += (int) (speed * Math.sin(degree));

            if (posX < 0 || posX > Config.WINDOW_WIDTH - width || posY < 30 || posY > Config.WINDOW_HEIGHT - height)
                alive = false;

            g.drawImage(img, posX, posY, null);
        }

    }
}
