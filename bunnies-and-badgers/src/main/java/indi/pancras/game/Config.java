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
            put("rabbit", "/src/main/resources/images/rabbit.png");
            put("grass", "/src/main/resources/images/grass.png");
            put("castle", "/src/main/resources/images/castle.png");
            put("arrow", "/src/main/resources/images/arrow.png");
            put("badguy", "/src/main/resources/images/badguy.png");
            put("healthbar", "/src/main/resources/images/healthbar.png");
            put("health", "/src/main/resources/images/health.png");
            put("gameover", "/src/main/resources/images/gameover.jpg");
            put("youwin", "/src/main/resources/images/youwin.png");
        }
    };
    //音频路径
    public static final HashMap<String, String> audioPath = new HashMap<String, String>() {
        {
            put("enemy", "/src/main/resources/audio/enemy.wav");
            put("explode", "/src/main/resources/audio/explode.wav");
            put("moonlight", "/src/main/resources/audio/moonlight.wav");
            put("shoot", "/src/main/resources/audio/shoot.wav");

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
