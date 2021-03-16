package indi.pancras.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * @author pancras
 * @version 1.0
 */
public class HealthBar extends GameObject {
    //当前生命值
    private int healthValue;

    public HealthBar(Image img, int posX, int posY) {
        super(img, posX, posY, 0, img.getWidth(null), img.getHeight(null));
    }

    public int getHelthValue() {
        return healthValue;
    }

    public void setHelthValue(int helthValue) {
        this.healthValue = helthValue;
    }

    @Override
    public void drawSelf(Graphics g) {
        g.drawImage(img, posX, posY, null);

        int healthWidth = (int) (healthValue / 100.0 * img.getWidth(null));

        Color c = g.getColor();
        g.setColor(Color.green);
        g.fillRect(posX + 2, posY + 2, healthWidth, img.getHeight(null) - 4);
        g.setColor(c);
    }
}
