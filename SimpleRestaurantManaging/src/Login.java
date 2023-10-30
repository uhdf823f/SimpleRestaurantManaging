

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Login {
    public Login(){
        DemoFrame demoFrame = new DemoFrame(450,350);
        JLabel jLabel = new JLabel("登录界面",SwingConstants.CENTER);
        jLabel.setBounds(0,0,450,30);
        jLabel.setFont(new Font("楷体",Font.BOLD,25));
        JLabel label_ID = new JLabel("请输入ID",SwingConstants.CENTER);
        label_ID.setBounds(50,80,150,30);
        JTextField textField_ID = new JTextField();
        textField_ID.setBounds(225,80,130,30);
        JLabel label_account = new JLabel("请输入账号",SwingConstants.CENTER);
        label_account.setBounds(50,140,150,30);
        JTextField textField_account = new JTextField();
        textField_account.setBounds(225,140,130,30);
        JLabel label_password = new JLabel("请输入密码",SwingConstants.CENTER);
        label_password.setBounds(50,200,150,30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(225,200,130,30);
        JButton button_sure = new JButton("登录");
        button_sure.setBounds(56,270,120,30);
        JButton button_quit = new JButton("返回");
        button_quit.setBounds(255,270,120,30);

        button_sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    boolean flag = false;
                    String message[] = new String[3];
                    message[0] = textField_ID.getText();
                    message[1] = textField_account.getText();
                    message[2] = new String(passwordField.getPassword());
                    File file = new File("Member.txt");
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String s = bufferedReader.readLine();
                    while(s!=null){
                        if(s.split(",")[0].equals(message[0])&&s.split(",")[5].equals(message[1])
                                &&s.split(",")[6].equals(message[2])){
                            flag = true;
                            break;
                        }
                        s = bufferedReader.readLine();
                    }
                    if(!flag){
                        throw new DemoException("信息不符，请确认");
                        //ID，账号，密码不能完全匹配，抛出异常
                    }
                    Member member;
                    if(s.split(",")[3].equals("服务员")){
                        Attendant attendant = new Attendant(s.split(",")[0],s.split(",")[1],
                                s.split(",")[2],s.split(",")[3],s.split(",")[4],s.split(",")[5],
                                s.split(",")[6],s.split(",")[7],s.split(",")[8]);
                        attendant.AttendantFrame(attendant);
                    }
                    if(s.split(",")[3].equals("大堂经理")){
                        Manager manager = new Manager(s.split(",")[0],s.split(",")[1],
                                s.split(",")[2],s.split(",")[3],s.split(",")[4],s.split(",")[5],
                                s.split(",")[6],s.split(",")[7],s.split(",")[8]);
                        manager.ManagerFrame(manager);
                    }
                    //如果身份为服务员，生成对应Attendant类对象并进入服务员界面进行后续操作，身份为大堂经理亦然
                    textField_ID.setText("");
                    textField_account.setText("");
                    passwordField.setText("");
                }catch (Exception exception){

                }
            }
        });

        button_quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demoFrame.getjFrame().setVisible(false);
            }
        });

        demoFrame.getPanel().add(jLabel);
        demoFrame.getPanel().add(label_ID);
        demoFrame.getPanel().add(label_account);
        demoFrame.getPanel().add(label_password);
        demoFrame.getPanel().add(textField_ID);
        demoFrame.getPanel().add(textField_account);
        demoFrame.getPanel().add(passwordField);
        demoFrame.getPanel().add(button_sure);
        demoFrame.getPanel().add(button_quit);
        demoFrame.getjFrame().setVisible(true);
    }

}
