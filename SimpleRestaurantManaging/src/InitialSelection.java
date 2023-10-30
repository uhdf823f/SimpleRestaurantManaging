

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialSelection {
    public static void main(String[] args) {
        DemoFrame demoFrame = new DemoFrame(650,260);
        JLabel jLabel = new JLabel("欢迎使用该餐厅管理系统",SwingConstants.CENTER);
        jLabel.setFont(new Font("楷体",Font.BOLD,25));
        jLabel.setBounds(0,0,650,30);
        JButton button_sign = new JButton("登录");
        button_sign.setBounds(55,120,120,50);
        JButton button_login = new JButton("注册");
        button_login.setBounds(267,120,120,50);
        JButton button_exit = new JButton("退出");
        button_exit.setBounds(468,120,120,50);
        //添加一系列组件

        button_sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
            }
        });

        button_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Sign();
            }
        });
        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        demoFrame.getPanel().add(jLabel);
        demoFrame.getPanel().add(button_sign);
        demoFrame.getPanel().add(button_login);
        demoFrame.getPanel().add(button_exit);
        demoFrame.getjFrame().setVisible(true);
    }
}
