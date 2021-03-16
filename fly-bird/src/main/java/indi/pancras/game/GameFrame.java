package indi.pancras.game;

import javax.swing.JFrame;

/**
 * 游戏窗体类
 *
 * @author pancras
 * @version 1.0
 */
public class GameFrame extends JFrame {
    public GameFrame() {
        initFrame();
    }

    public static void main(String[] args){
        //创建窗体
        GameFrame frame = new GameFrame();
        //创建画板
        GamePanel panel = new GamePanel();
        //向窗体添加画板
        frame.add(panel);

        //显示窗体
        frame.setVisible(true);

        //开始游戏
        panel.startGame();

    }

    private void initFrame() {
        /*窗口设置*/
        //设置标题
        setTitle("小鸟飞行游戏");
        //设置窗口大小
        setSize(Config.FRAME_W, Config.FRAME_H);
        //设置窗口位置：屏幕中间
        setLocationRelativeTo(null);
        //禁止修改窗口大小
        setResizable(false);

        //设置关闭窗口时终止程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置Logo图标
        this.setIconImage(GameUtil.getImage(Config.LOGO));
    }
}
