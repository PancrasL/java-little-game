package indi.pancras.game.object;

import indi.pancras.game.Config;
import indi.pancras.game.GameUtil;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 * 地面类
 *
 * @author pancras
 * @version 1.0
 */
public class Cloud {
    private List<CloudImp> clouds = new LinkedList<>();

    private int count;

    public void move() {
        moveClouds();
        createNewCloud();
    }

    public void draw(Graphics g) {
        for (CloudImp cloud : clouds) {
            cloud.draw(g);
        }
    }

    private void moveClouds() {
        //移动云朵
        for (CloudImp cloud : clouds) {
            cloud.move();
        }

        //删除越界的云朵
        for (int i = 0; i < clouds.size(); ) {
            if (clouds.get(i).outOfBoundary()) {
                clouds.remove(i);
            } else {
                i++;
            }
        }
    }

    private void createNewCloud() {
        //生成新的云朵
        if (--count < 0) {
            clouds.add(new CloudImp(GameUtil.randomInt(80, 150), GameUtil.randomInt(20, 50)));
            count = 100;
        }
    }
}

class CloudImp extends GameObject {
    public CloudImp(int w, int h) {
        super(GameUtil.getImage(Config.CLOUD), Config.FRAME_W, GameUtil.randomInt(0, 170), Config.CLOUD_SPEED, w, h);
    }

    /**
     * 判断云朵移动到窗口之外
     *
     * @return true or false
     */
    public boolean outOfBoundary() {
        return x <= -w;
    }

    @Override
    public void move() {
        x -= speed;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, w, h, null);
    }
}

