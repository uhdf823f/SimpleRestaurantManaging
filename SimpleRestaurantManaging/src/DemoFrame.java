

import javax.swing.*;
import java.awt.*;

public class DemoFrame {
    private JFrame jFrame;
    private JPanel panel;

    public JFrame getjFrame() {
        return jFrame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public  DemoFrame(int x, int y){
        jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setSize(x, y);
        jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        jFrame.setResizable(false);//大小不可变
        int WindowWidth = jFrame.getWidth();
        int WindowHeight = jFrame.getHeight();
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();//得到屏幕大小以做到居中
        int ScreenWidth = ScreenSize.width;
        int ScreenHeight = ScreenSize.height;
        jFrame.setLocation(ScreenWidth / 2 - WindowWidth / 2, ScreenHeight / 2 - WindowHeight / 2);
        panel = new JPanel();
        panel.setLayout(null);
        jFrame.setContentPane(panel);
        panel.setLocation(ScreenWidth / 2 - WindowWidth / 2, ScreenHeight / 2 - WindowHeight / 2);
    }
}
