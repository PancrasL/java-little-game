package indi.pancras.game;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author pancras
 * @version 1.0
 */
public class GameFrame extends Frame {

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.initGame();
    }

    public void initGame() {
        //设置窗口
        setTitle("拼图游戏");
        setSize(500, 530);
        setResizable(false);

        //添加拼图显示组件
        add(new BlockImage(GameUtil.getImage("/resources/images/7.jpg"), 3, 3));
        setVisible(true);

        //添加窗口监听
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
