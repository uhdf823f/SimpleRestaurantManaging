

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoException extends Exception{
    public DemoException(String message){
        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setSize(350,200);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        int WindowWidth = jFrame.getWidth();
        int WindowHeight = jFrame.getHeight();
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int ScreenWidth = ScreenSize.width;
        int ScreenHeight = ScreenSize.height;
        jFrame.setLocation(ScreenWidth/2-WindowWidth/2,ScreenHeight/2-WindowHeight/2);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        jFrame.setContentPane(panel);
        panel.setLocation(ScreenWidth/2-WindowWidth/2,ScreenHeight/2-WindowHeight/2);

        //信息格式异常窗口创建
        JLabel jLabel = new JLabel(message,SwingConstants.CENTER);
        jLabel.setBounds(0,20,350,100);
        JButton jButton = new JButton("确定");
        jButton.setBounds(130,130,80,20);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
            }
        });
        panel.add(jLabel);
        panel.add(jButton);
        jFrame.setVisible(true);
    }

}
