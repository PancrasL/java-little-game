package indi.pancras.game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * @author pancras
 * @version 1.0
 */
public class BlockImage extends Canvas {
    //图块的行数
    private int row;
    //图块的列数
    private int col;
    //图像的宽度
    private int imgWidth;
    //图像的高度
    private int imgHeight;
    //图块的宽度
    private int blockWidth;
    //图块的高度
    private int blockHeight;

    private Image img = GameUtil.getImage("/resources/images/1.jpg");

    /**
     * 拼图对象的构造函数，内部调用init方法
     *
     * @param row 图像分解的行数
     * @param col 图像分解的列数
     * @param img 图像
     */
    public BlockImage(Image img, int row, int col) {
        init(img, row, col);
    }

    private void init(Image img, int row, int col) {
        //图像、拆分的行数、拆分的列数
        this.img = img;
        this.row = row;
        this.col = col;

        //修改图片的大小
        this.img = GameUtil.resizeImage((BufferedImage) img, 500, 500);

        //图像的宽高
        imgWidth = img.getWidth(null);
        imgHeight = img.getHeight(null);

        //拼图块的宽高
        blockWidth = imgWidth / col;
        blockHeight = imgHeight / row;


        //鼠标监听
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
                repaint();
            }
        });

        //键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });


    }

    @Override
    public void paint(Graphics g) {
        System.out.println(img.getWidth(null));
        g.drawImage(img, 0, 0, null);
    }
}
