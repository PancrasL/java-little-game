package indi.pancras.game;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 游戏主窗口类
 *
 * @author pancras
 * @version 1.0
 */
public class GameFrame extends Frame {
    /**
     * 保存程序用到的图片对象
     */
    static HashMap<String, Image> gameImg = new HashMap<>();

    static {
        //加载图片资源
        for (Entry<String, String> img : Config.imgPath.entrySet()) {
            gameImg.put(img.getKey(), GameUtil.getImage((img.getValue())));
        }
    }

    /**
     * 游戏相关标志
     */
    boolean gameOver = false;
    int healthValue = 100;
    int timer;

    /**
     * 游戏物体
     */
    //背景图片
    private Background bg = new Background(gameImg.get("grass"), 0, 0);
    //兔子
    private Rabbit rabbit = new Rabbit(gameImg.get("rabbit"), 100, (Config.WINDOW_HEIGHT - 50) / 2, Config.RABBIT_SPEED);
    //城堡
    private Castle[] castles = new Castle[4];
    //弓箭
    private ArrayList<Arrow> arrows = new ArrayList<>();
    //獾
    private ArrayList<Badguy> badguys = new ArrayList<>();
    //生命条
    private HealthBar healthBar = new HealthBar(gameImg.get("healthbar"), 20, 40);
    private Image offScreenImage = null;


    public GameFrame() throws HeadlessException {
        //创建游戏物体对象
        for (int i = 0; i < castles.length; i++) {
            castles[i] = new Castle(gameImg.get("castle"), 30, 50 + 100 * i);
        }
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        frame.initGame();
//        Image img = GameUtil.getImage("/images/arrow.png");
//        int a= 1;
    }

    /**
     * 初始化游戏
     */
    public void initGame() {
        //初始化窗口
        this.setTitle("保卫城堡 by lpz");
        this.setVisible(true);
        this.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        this.setLocation(Config.WINDOW_LOCATION);

        //播放背景音乐
        GameUtil.playSound(Config.audioPath.get("moonlight"));

        //监听游戏窗口的关闭
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //启动重画窗口线程
        new PaintThread().start();

        //启动键盘监听线程
        this.addKeyListener(new KeyMonitor());

        //启动鼠标监听线程
        MouseMonitor mouseMonitor = new MouseMonitor();
        this.addMouseListener(mouseMonitor);//注册后可以识别鼠标的按、松
        this.addMouseMotionListener(mouseMonitor);//注册后可以识别鼠标的移动、拖拽
    }

    /**
     * 创建弓箭
     *
     * @param e 鼠标事件
     */
    private void createOneArrow(MouseEvent e) {
        arrows.add(new Arrow(gameImg.get("arrow"), rabbit.getShootX(), rabbit.getShootY(), Config.ARROW_SPEED, rabbit.getDegree()));
    }

    /**
     * 创建獾
     */
    private void createOneBadguy() {
        if (timer <= 0) {
            badguys.add(new Badguy(gameImg.get("badguy"), Config.WINDOW_WIDTH, (int) (50 + Math.random() * (Config.WINDOW_HEIGHT - 100)), Config.BADGUY_SPEED));
            timer = (int) (400 + Math.random() * 500);//每50-100ms生成一只獾
        }
    }

    /**
     * 碰撞检测：    当弓箭和獾碰撞时，清除獾和弓箭。
     * 当獾和城堡碰撞时，削减生命值。
     * 当獾和弓箭跑出边界时，清除獾。
     */
    private void collisionDetection() {
        //清除已经死掉的獾，并检测獾是否到达城堡
        for (int i = 0; i < badguys.size(); i++) {
            if (!badguys.get(i).isAlive()) {
                badguys.remove(i);
            } else if (badguys.get(i).getPosX() < 100) {//到达城堡
                GameUtil.playSound(Config.audioPath.get("explode"));
                healthValue -= Config.HEALTH_LOSS;
                if (healthValue < 0)
                    healthValue = 0;
                badguys.get(i).setAlive(false);
            }
        }
        //清除弓箭
        for (int i = 0; i < arrows.size(); i++) {
            if (!arrows.get(i).isAlive()) {
                arrows.remove(i);
            }
        }

        //检测獾是否和弓箭相撞,相撞则清除獾和弓箭
        for (int i = 0; i < arrows.size(); i++) {
            Rectangle arrowRect = arrows.get(i).getRect();
            for (int j = 0; j < badguys.size(); j++) {
                if (badguys.get(j).getRect().intersects(arrowRect)) {
                    GameUtil.playSound(Config.audioPath.get("enemy"));
                    arrows.get(i).setAlive(false);
                    badguys.get(j).setAlive(false);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (!gameOver) {
            //绘制背景图片
            bg.drawSelf(g);
            //绘制城堡
            for (Castle c : castles) {
                c.drawSelf(g);
            }
            //绘制兔子
            rabbit.drawSelf(g);
            //绘制弓箭
            for (Arrow arrow : arrows) {
                arrow.drawSelf(g);
            }
            //绘制獾
            for (Badguy badguy : badguys) {
                badguy.drawSelf(g);
            }
            //绘制生命值
            healthBar.setHelthValue(healthValue);
            healthBar.drawSelf(g);
        } else {
            g.drawImage(gameImg.get("gameover"), 0, 0, null);
        }

    }

    /**
     * 双缓冲区的实现
     *
     * @param g 绘制窗口
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        }

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 窗口重画
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                //计时
                timer -= 1000 / Config.FPS;
                //创建新的獾
                createOneBadguy();
                //碰撞检测
                collisionDetection();
                if (healthValue == 0) {
                    gameOver = true;
                }
                //调用外部类的重画方法
                repaint();
                try {
                    Thread.sleep(1000 / Config.FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 键盘监听
     */
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            rabbit.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            rabbit.minusDirection(e);
        }
    }

    /**
     * 鼠标监听
     */
    class MouseMonitor extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            rabbit.modifyOrientation(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            GameUtil.playSound(Config.audioPath.get("shoot"));
            createOneArrow(e);
        }
    }
}
