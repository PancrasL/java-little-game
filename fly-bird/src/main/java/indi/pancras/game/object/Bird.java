package indi.pancras.game.object;

import indi.pancras.game.Config;
import indi.pancras.game.GameUtil;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * @author pancras
 * @version 1.0
 */
public class Bird extends GameObject {

    private double birdSpeed;

    private ArrayList<Image> images;

    private int index;

    public Bird() {
        super(null, 50, 50, 0, 30, 30);

        images = new ArrayList<>();
        images.add(GameUtil.getImage(Config.BIRD_0));
        images.add(GameUtil.getImage(Config.BIRD_1));
        images.add(GameUtil.getImage(Config.BIRD_2));
    }

    public void fly() {
        if (birdSpeed > 0) {
            birdSpeed = 0;
        }
        birdSpeed -= 5;

    }

    @Override
    public void move() {
        birdSpeed += 1;
        y += birdSpeed;
    }

    @Override
    public void draw(Graphics g) {
        index = (index + 1) % images.size();
        g.drawImage(images.get(index), x, y, w, h, null);
    }
}
