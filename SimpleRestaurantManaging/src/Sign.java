

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Sign {
    public Sign(){
        DemoFrame demoFrame = new DemoFrame(450,640);
        JLabel jLabel = new JLabel("注册界面",SwingConstants.CENTER);
        jLabel.setBounds(0,0,450,30);
        jLabel.setFont(new Font("楷体", Font.BOLD,25));
        JLabel label_identity = new JLabel("请选择注册身份",SwingConstants.CENTER);
        label_identity.setBounds(50,50,150,30);
        JComboBox comboBox_identity = new JComboBox<>();
        comboBox_identity.addItem("服务员");
        comboBox_identity.addItem("大堂经理");
        comboBox_identity.setBounds(225,50,130,30);
        JLabel label_name = new JLabel("请输入姓名",SwingConstants.CENTER);
        label_name.setBounds(50,100,150,30);
        JTextField textField_name = new JTextField();
        textField_name.setBounds(225,100,130,30);
        JLabel label_account = new JLabel("请输入账号",SwingConstants.CENTER);
        label_account.setBounds(50,155,150,30);
        JTextField textField_account = new JTextField();
        textField_account.setBounds(225,155,130,30);
        JLabel label_password = new JLabel("请输入密码",SwingConstants.CENTER);
        label_password.setBounds(50,207,150,30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(225,207,130,30);
        JLabel label_password1 = new JLabel("再确认密码",SwingConstants.CENTER);
        label_password1.setBounds(50,257,150,30);
        JPasswordField passwordField1 = new JPasswordField();
        passwordField1.setBounds(225,260,130,30);
        JButton button_ID = new JButton("点击获取ID");
        button_ID.setBounds(50,311,150,40);
        JTextField textField_ID = new JTextField();
        textField_ID.setBounds(225,315,130,30);
        JLabel label_sex = new JLabel("性别",SwingConstants.CENTER);
        label_sex.setBounds(50,368,150,30);
        JComboBox comboBox_sex = new JComboBox();
        comboBox_sex.addItem("男");
        comboBox_sex.addItem("女");
        comboBox_sex.setBounds(225,368,130,30);
        JLabel label_number = new JLabel("联系方式",SwingConstants.CENTER);
        label_number.setBounds(50,422,150,30);
        JTextField textField_number = new JTextField();
        textField_number.setBounds(225,422,130,30);
        JLabel label_birth = new JLabel("生日",SwingConstants.CENTER);
        label_birth.setBounds(50,476,150,30);
        JTextField textField_month = new JTextField();
        textField_month.setBounds(225,476,40,30);
        JLabel label_month = new JLabel("月",SwingConstants.CENTER);
        label_month.setBounds(270,476,30,30);
        JTextField textField_day = new JTextField();
        textField_day.setBounds(305,476,40,30);
        JLabel label_day = new JLabel("日");
        label_day.setBounds(350,476,30,30);
        JButton button_sure = new JButton("注册");
        button_sure.setBounds(56,550,120,30);
        JButton button_quit = new JButton("返回");
        button_quit.setBounds(255,550,120,30);

        button_ID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int a[] = new int[1000000];
                    File file = new File("Member.txt");
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String s = bufferedReader.readLine();
                    while(s!=null){
                        a[Integer.parseInt(s.split(",")[0])]=1;
                        s = bufferedReader.readLine();
                    }
                    while(true){
                        int r = (int)(Math.random()*900000)+100000;
                        if(a[r]==0){
                            textField_ID.setText(Integer.toString(r));//生成六位随机数
                            break;
                        }
                    }
                }catch (Exception e1){

                }
            }
        });

        button_sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String message[] = new String[9];
                    message[0] = textField_ID.getText();
                    message[1] = textField_name.getText();
                    message[2] = textField_number.getText();
                    message[3] = (String) comboBox_identity.getSelectedItem();
                    message[4] = (String) comboBox_sex.getSelectedItem();
                    message[5] = textField_account.getText();
                    message[6] = new String(passwordField.getPassword());
                    message[7] = textField_month.getText();
                    message[8] = textField_day.getText();
                    if(!message[6].equals(new String(passwordField1.getPassword())))
                        throw new DemoException("两次密码不一致");
                    if(message[2].length()!=11)
                        throw new DemoException("电话号码格式有误");
                    if(Integer.parseInt(message[8])>Time.getTime().getDay()[Integer.parseInt(message[7])])
                        throw new DemoException("日期不正确");
                    //抛出一系列异常
                    File file = new File("Member.txt");
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
                    bufferedWriter.write(message[0]+","+message[1]+","+message[2]+","+message[3]+","
                            +message[4]+","+message[5]+","+message[6]+","+message[7]+","+message[8]+"\n");
                    //信息写入文件
                    bufferedWriter.close();
                    demoFrame.getjFrame().setVisible(false);
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
        demoFrame.getPanel().add(label_identity);
        demoFrame.getPanel().add(comboBox_identity);
        demoFrame.getPanel().add(label_name);
        demoFrame.getPanel().add(textField_name);
        demoFrame.getPanel().add(label_account);
        demoFrame.getPanel().add(textField_account);
        demoFrame.getPanel().add(label_password);
        demoFrame.getPanel().add(passwordField);
        demoFrame.getPanel().add(label_password1);
        demoFrame.getPanel().add(passwordField1);
        demoFrame.getPanel().add(button_ID);
        demoFrame.getPanel().add(textField_ID);
        demoFrame.getPanel().add(label_sex);
        demoFrame.getPanel().add(comboBox_sex);
        demoFrame.getPanel().add(label_number);
        demoFrame.getPanel().add(textField_number);
        demoFrame.getPanel().add(label_birth);
        demoFrame.getPanel().add(textField_month);
        demoFrame.getPanel().add(label_month);
        demoFrame.getPanel().add(textField_day);
        demoFrame.getPanel().add(label_day);
        demoFrame.getPanel().add(button_sure);
        demoFrame.getPanel().add(button_quit);
        demoFrame.getjFrame().setVisible(true);
    }
}
