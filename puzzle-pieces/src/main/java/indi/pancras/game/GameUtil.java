package indi.pancras.game;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 游戏工具类
 *
 * @author pancras
 * @version 1.0
 */
public class GameUtil {
    private GameUtil() {
    }

    /**
     * 返回指定路径文件的图片对象
     *
     * @param path 图片路径
     * @return Image对象
     */
    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getResource(path);
            if (u == null)
                System.out.println(path);
            else
                bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }

    /**
     * 修改图像为指定大小
     *
     * @param img 需要修改的图像
     * @param w   目标宽度
     * @param h   目标高度
     * @return 修改后的图像
     */
    public static BufferedImage resizeImage(BufferedImage img,
                                            int w, int h) {
        //设置生成图片的宽、高、色彩
        BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        //创建画布
        Graphics2D g2 = newImg.createGraphics();
        //设置图片透明，只有png图片才可以设置
        //bi = g2.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);

        //重新创建画布
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();

        System.out.println("#" + img.getWidth());
        return newImg;
    }

}
