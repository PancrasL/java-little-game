package indi.pancras.game;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
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
     * 返回[a,b)之间的随机数
     *
     * @param a 下界
     * @param b 上界
     * @return [a, b)之间的随机数
     */
    public static int randomInt(double a, double b) {
        return (int) (Math.random() * (b - a) + a);
    }
}
