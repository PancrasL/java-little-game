package indi.pancras.game;

import java.awt.Graphics;
import java.awt.Image;

/**
 * @author pancras
 * @version 1.0
 */
public class Castle extends GameObject {
    public Castle(Image img, int posX, int posY) {
        super(img, posX, posY, 0, img.getWidth(null), img.getHeight(null));
    }

    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
    }
}
