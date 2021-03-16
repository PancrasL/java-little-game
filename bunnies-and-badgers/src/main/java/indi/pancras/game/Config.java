package indi.pancras.game;

import java.awt.Point;
import java.util.HashMap;

/**
 * 配置文件
 *
 * @author pancras
 * @version 1.0
 */
public class Config {
    //游戏窗口大小
    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;
    //图片路径
    public static final HashMap<String, String> imgPath = new HashMap<String, String>() {
        {
            put("rabbit", "/images/rabbit.png");
            put("grass", "/images/grass.png");
            put("castle", "/images/castle.png");
            put("arrow", "/images/arrow.png");
            put("badguy", "/images/badguy.png");
            put("healthbar", "/images/healthbar.png");
            put("health", "/images/health.png");
            put("gameover", "/images/gameover.jpg");
            put("youwin", "/images/youwin.png");
        }
    };
    //音频路径
    public static final HashMap<String, String> audioPath = new HashMap<String, String>() {
        {
            put("enemy", "/audio/enemy.wav");
            put("explode", "/audio/explode.wav");
            put("moonlight", "/audio/moonlight.wav");
            put("shoot", "/audio/shoot.wav");

        }
    };
    //弓箭速度
    public static final int ARROW_SPEED = 5;
    //兔子速度
    public static final int RABBIT_SPEED = 3;
    //獾的速度
    public static final int BADGUY_SPEED = 2;
    //生命值损失
    public static final int HEALTH_LOSS = 5;
    //刷新频率
    public static final int FPS = 120;
    //显示器大小
    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;
    //游戏窗口位置，默认在屏幕中央
    public static final Point WINDOW_LOCATION = new Point((SCREEN_WIDTH - WINDOW_WIDTH) / 2, (SCREEN_HEIGHT - WINDOW_HEIGHT) / 2);
}
