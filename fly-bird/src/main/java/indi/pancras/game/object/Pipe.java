package indi.pancras.game.object;

import indi.pancras.game.Config;
import indi.pancras.game.GameUtil;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pancras
 * @version 1.0
 */
public class Pipe {
    private static final Image DOWN_IMG = GameUtil.getImage(Config.PIPE_DOWN);
    private static final Image UP_IMG = GameUtil.getImage(Config.PIPE_UP);

    private List<PipeImp> pipes = new LinkedList<>();
    private int count;

    public void move() {
        //移动管道并删除越界的管道
        movePipes();
        //创建新的管道
        createNewPipe();
    }

    public void draw(Graphics g) {
        for (PipeImp pipe : pipes) {
            pipe.draw(g);
        }
    }

    public List<Rectangle> getRect() {
        if (pipes.size() < 2)
            return null;

        List<Rectangle> list = new ArrayList<>();
        list.add(pipes.get(0).getRect());
        list.add(pipes.get(1).getRect());

        return list;
    }

    private void movePipes() {
        //移动管道
        for (PipeImp pipe : pipes) {
            pipe.move();
        }

        //删除越界的管道
        for (int i = 0; i < pipes.size(); ) {
            if (pipes.get(i).outOfBoundary()) {
                pipes.remove(i);
            } else {
                i++;
            }
        }
    }

    private void createNewPipe() {
        if (count-- < 0) {
            //生成上面的管道
            int posX = Config.FRAME_W;
            int posY = GameUtil.randomInt(Config.FRAME_H * 0.3, Config.FRAME_H * 0.7);
            int width = DOWN_IMG.getWidth(null);
            int height = DOWN_IMG.getHeight(null);
            pipes.add(new PipeImp(UP_IMG, posX, posY, Config.LAND_SPEED, width, height));

            //生成下面的管道
            posY = 0;
            height = GameUtil.randomInt(Config.FRAME_H - height - 120, Config.FRAME_H - height - 30);
            pipes.add(new PipeImp(DOWN_IMG, posX, posY, Config.LAND_SPEED, width, height));

            count = 100;
        }
    }
}

class PipeImp extends GameObject {

    public PipeImp(Image img, int x, int y, int speed, int w, int h) {
        super(img, x, y, speed, w, h);
    }

    public boolean outOfBoundary() {
        return x <= -w;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, w, h, null);
    }

    @Override
    public void move() {
        x -= speed;
    }
}
