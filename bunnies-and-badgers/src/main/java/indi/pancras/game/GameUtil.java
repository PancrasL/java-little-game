package indi.pancras.game;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
     * 播放指定文件路径的音频
     *
     * @param path 音频文件路径
     */
    public static void playSound(String path) {
        try {
            FileInputStream f = new FileInputStream(GameUtil.class.getResource(path).getFile());
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 旋转图片角度
     *
     * @param src    源图片
     * @param degree 角度
     * @return 目标图片
     */
    public static BufferedImage rotate(Image src, double degree) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        // calculate the new image size
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
                src_width, src_height)), degree);

        BufferedImage res = new BufferedImage(rect_des.width, rect_des.height,
                ((BufferedImage) src).getColorModel().getTransparency());
        Graphics2D g2 = res.createGraphics();
        // transform(这里先平移、再旋转比较方便处理；绘图时会采用这些变化，绘图默认从画布的左上顶点开始绘画，源图片的左上顶点与画布左上顶点对齐，然后开始绘画，修改坐标原点后，绘画对应的画布起始点改变，起到平移的效果；然后旋转图片即可)
        //平移（原理修改坐标系原点，绘图起点变了，起到了平移的效果，如果作用于旋转，则为旋转中心点）
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
        //旋转（原理transalte(dx,dy)->rotate(radians)->transalte(-dx,-dy);修改坐标系原点后，旋转90度，然后再还原坐标系原点为(0,0),但是整个坐标系已经旋转了相应的度数 ）
        g2.rotate(degree, src_width / 2.0, src_height / 2.0);


        g2.drawImage(src, null, null);
        return res;
    }

    /**
     * 计算转换后目标矩形的宽高
     *
     * @param src    源矩形
     * @param degree 角度
     * @return 目标矩形
     */
    private static Rectangle CalcRotatedSize(Rectangle src, double degree) {
        double cos = Math.abs(Math.cos(degree));
        double sin = Math.abs(Math.sin(degree));
        int des_width = (int) (src.width * cos) + (int) (src.height * sin);
        int des_height = (int) (src.height * cos) + (int) (src.width * sin);
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }
}
