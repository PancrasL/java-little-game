package indi.pancras.game.object;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * @author pancras
 * @version 1.0
 */
public abstract class GameObject {
    //物体的图片
    protected Image img;
    //物体的位置
    protected int x;
    protected int y;
    //物体的速度
    protected int speed;
    //物体的宽度和高度
    protected int w;
    protected int h;

    public GameObject(Image img, int x, int y, int speed, int w, int h) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.w = w;
        this.h = h;
    }

    public GameObject(Image img, int x, int y) {
        this(img, x, y, 0, 0, 0);
    }

    public GameObject(Image img, int x, int y, int speed) {
        this(img, x, y, speed, 0, 0);
    }

    public int getPosX() {
        return x;
    }

    public void setPosX(int posX) {
        this.x = posX;
    }

    public int getPosY() {
        return y;
    }

    public void setPosY(int posY) {
        this.y = posY;
    }

    /**
     * 在当前位置绘制物体图像
     *
     * @param g 绘制的窗口
     */
    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    /**
     * 返回物体所在的矩形，用于后续的碰撞检测
     *
     * @return 物体所在的矩形
     */
    public Rectangle getRect() {
        return new Rectangle(x, y, w, h);
    }

    public void move() {
    }
}
