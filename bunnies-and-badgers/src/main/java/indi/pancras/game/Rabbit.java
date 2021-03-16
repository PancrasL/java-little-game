package indi.pancras.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author pancras
 * @version 1.0
 */
public class Rabbit extends GameObject {
    //兔子的运动方向
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    //兔子的角度
    private double degree;
    //兔子的射击坐标
    private int shootX;
    private int shootY;

    public Rabbit(Image img, int posX, int posY, int speed) {
        super(img, posX, posY, speed, img.getWidth(null), img.getHeight(null));
    }

    public double getDegree() {
        return degree;
    }

    public int getShootX() {
        return shootX;
    }

    public int getShootY() {
        return shootY;
    }

    /**
     * 按下某个键，增加相应的方向
     *
     * @param e 按键操作
     */
    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    /**
     * 松开某个键，减去相应的方向
     *
     * @param e 按键操作
     */
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }

    /**
     * 修改物体朝向
     */
    public void modifyOrientation(MouseEvent e) {
        //鼠标的坐标
        int mPosX = e.getX();
        int mPosY = e.getY();

        //兔子中心点的坐标
        int rPosX = posX + width / 2;
        int rPosY = posY + height / 2;

        //计算旋转弧度
        degree = Math.atan2(mPosY - rPosY, mPosX - rPosX);
    }

    @Override
    public void drawSelf(Graphics g) {
        Image rotateImg = GameUtil.rotate(img, degree);
        //物体所在矩形的对角线长度
        double diagonal = Math.sqrt(width * width + height * height);
        shootX = (int) (posX + width / 2 + Math.cos(degree) * diagonal / 2);
        shootY = (int) (posY + width / 2 + Math.sin(degree) * diagonal / 2);

        g.drawImage(rotateImg, posX, posY, null);

        if (left && posX >= 0) {
            posX -= speed;
        }
        if (right && posX <= Config.WINDOW_WIDTH - this.width) {
            posX += speed;
        }
        if (up && posY >= 30) {
            posY -= speed;
        }
        if (down && posY <= Config.WINDOW_HEIGHT - this.height) {
            posY += speed;
        }

    }
}
