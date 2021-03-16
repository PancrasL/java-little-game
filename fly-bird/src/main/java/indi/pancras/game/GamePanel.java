package indi.pancras.game;

import indi.pancras.game.object.Bird;
import indi.pancras.game.object.Cloud;
import indi.pancras.game.object.Land;
import indi.pancras.game.object.Pipe;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * 面板类
 *
 * @author pancras
 * @version 1.0
 */
public class GamePanel extends JPanel {
    private Image bg;
    private Image game_ready;
    private Image game_over;

    private Cloud cloud;
    private Bird bird;
    private Land land;
    private Pipe pipe;

    //游戏状态： 0 -> 等待界面
    //         1 -> 游戏开始
    //         2 -> 游戏结束
    //         3 -> 游戏帮助
    //         4 -> 游戏排行榜
    private int status;

    public GamePanel() {
        initGame();
    }

    public void startGame() {
        //启动游戏线程
        new Thread(new GameThread()).start();

        //添加鼠标监听器
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (status) {
                    case Config.GAME_READY:
                        status = Config.GAME_RUNNING;
                        break;
                    case Config.GAME_RUNNING:
                        bird.fly();
                        break;
                    case Config.GAME_OVER:
                        initGame();
                        status = Config.GAME_READY;
                        break;
                    case Config.GAME_HELP:
                        //TODO
                        status = Config.GAME_READY;
                        break;
                    case Config.GAME_SCORE:
                        //TODO
                        status = Config.GAME_READY;
                        break;
                }
            }
        });
    }

    private void initGame() {
        //背景图片
        bg = GameUtil.getImage(Config.BG);
        game_ready = GameUtil.getImage(Config.TEXT_READY);
        game_over = GameUtil.getImage(Config.TEXT_GAME_OVER);
        //云朵
        cloud = new Cloud();
        //小鸟
        bird = new Bird();
        //大地
        land = new Land();
        //管道
        pipe = new Pipe();

        //初始化游戏状态
        status = Config.GAME_READY;
    }

    private void collisionDetection() {
        //判断是否和天空大地碰撞
        if (bird.getPosY() < 0 || bird.getPosY() > land.getPosY()) {
            status = Config.GAME_OVER;
        }

        //判断是否和柱子碰撞
        List<Rectangle> list = pipe.getRect();
        if (null != list) {
            if (bird.getRect().intersects(list.get(0)) || bird.getRect().intersects(list.get(1))) {
                status = Config.GAME_OVER;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        switch (status) {
            case Config.GAME_READY:
                g.drawImage(bg, 0, 0, null);

                g.drawImage(game_ready, (Config.FRAME_W - game_ready.getWidth(null)) / 2, (Config.FRAME_H - game_ready.getHeight(null)) / 2, null);
                break;
            case Config.GAME_RUNNING:
                g.drawImage(bg, 0, 0, null);
                cloud.draw(g);
                bird.draw(g);
                pipe.draw(g);
                land.draw(g);
                break;
            case Config.GAME_OVER:
                g.drawImage(bg, 0, 0, null);
                g.drawImage(game_over, (Config.FRAME_W - game_ready.getWidth(null)) / 2, (Config.FRAME_H - game_ready.getHeight(null)) / 2, null);
                break;
            case Config.GAME_HELP:
                //TODO
                break;
            case Config.GAME_SCORE:
                //TODO
                break;
        }
    }

    class GameThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (status == Config.GAME_RUNNING) {
                    cloud.move();
                    bird.move();
                    pipe.move();
                    land.move();

                    collisionDetection();
                }
                try {
                    Thread.sleep(50);
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }
    }
}
