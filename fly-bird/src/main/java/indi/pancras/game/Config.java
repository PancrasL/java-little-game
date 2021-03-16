package indi.pancras.game;

/**
 * @author pancras
 * @version 1.0
 */
public class Config {
    public static final String BG = "/images/bg_day.png";
    public static final String LOGO = "/images/logo.png";
    public static final String CLOUD = "/images/ground.png";
    public static final String BIRD_0 = "/images/bird1_0.png";
    public static final String BIRD_1 = "/images/bird1_1.png";
    public static final String BIRD_2 = "/images/bird1_2.png";
    public static final String LAND = "/images/land.png";
    public static final String PIPE_DOWN = "/images/pipe_down.png";
    public static final String PIPE_UP = "/images/pipe_up.png";
    public static final String TEXT_READY = "/images/text_ready.png";
    public static final String TEXT_GAME_OVER = "/images/text_game_over.png";

    public static final int FRAME_W = 288;
    public static final int FRAME_H = 512;

    public static final int CLOUD_SPEED = 4;
    public static final int LAND_SPEED = 3;

    public static final int GAME_READY = 0;
    public static final int GAME_RUNNING = 1;
    public static final int GAME_OVER = 2;
    public static final int GAME_HELP = 3;
    public static final int GAME_SCORE = 4;
}
