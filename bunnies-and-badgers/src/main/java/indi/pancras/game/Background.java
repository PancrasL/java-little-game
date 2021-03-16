package indi.pancras.game;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 背景图片类
 *
 * @author pancras
 * @version 1.0
 */
public class Background extends GameObject {

    public Background(Image img, int posX, int posY) {
        super(img, posX, posY);
    }

    /**
     * 绘制背景图片
     *
     * @param g 绘制的窗口
     */
    @Override
    public void drawSelf(Graphics g) {
        int imgWidth = img.getWidth(null);
        int imgHeight = img.getHeight(null);

        //将背景图片铺满整个窗口
        for (int i = 0; i <= Config.WINDOW_HEIGHT / imgHeight; i++) {
            for (int j = 0; j <= Config.WINDOW_WIDTH / imgWidth; j++) {
                posX = j * imgWidth;
                g.drawImage(img, posX, posY, null);
            }
            posY = i * imgHeight;
        }
    }
}
