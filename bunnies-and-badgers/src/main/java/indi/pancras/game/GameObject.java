package indi.pancras.game;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


/**
 * 游戏物体的父类
 *
 * @author pancras
 * @version 1.0
 */
public class GameObject {
    //物体的图片
    protected Image img;
    //物体的位置
    protected int posX;
    protected int posY;
    //物体的速度
    protected int speed;
    //物体的宽度和高度
    protected int width, height;

    public GameObject(Image img, int posX, int posY, int speed, int width, int height) {
        this.img = img;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public GameObject(Image img, int posX, int posY) {
        this(img, posX, posY, 0, 0, 0);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * 在当前位置绘制物体图像
     *
     * @param g 绘制的窗口
     */
    public void drawSelf(Graphics g) {
        g.drawImage(img, posX, posY, null);
    }

    /**
     * 返回物体所在的矩形，用于后续的碰撞检测
     *
     * @return 物体所在的矩形
     */
    public Rectangle getRect() {
        return new Rectangle(posX, posY, width, height);
    }

}
