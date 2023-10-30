

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Attendant extends Member {
    private HashMap<String, Table> tableHashMap;
    private HashMap<String, Member> memberHashMap;
    private ArrayList<String[]> rowsTable;
    private JTable tableServe;
    private DefaultTableModel defaultTableModel;
    private String cols[] = {"桌号", "本桌服务员"};

    public Attendant(String ID, String name, String number, String identity, String sex, String account, String password, String month, String day) {
        super(ID, name, number, identity, sex, account, password, month, day);
    }


    public void AttendantFrame(Attendant attendant) {
        DemoFrame demoFrame = new DemoFrame(460, 400);
        JLabel jLabel = new BirthdayJudge().Judge(attendant);
        jLabel.setFont(new Font("楷体", Font.BOLD, 20));
        jLabel.setBounds(0, 0, 460, 30);
        DataA();
        JButton button_change = new JButton("本人信息修改");
        button_change.setBounds(0, 65, 120, 30);
        JButton button_set = new JButton("设置服务桌");
        button_set.setBounds(150, 65, 120, 30);
        JButton button_cancel = new JButton("停止对该桌服务");
        button_cancel.setBounds(300, 65, 140, 30);
        JButton button_quit = new JButton("返回");
        button_quit.setBounds(190, 320, 70, 30);
        tableServe = new JTable();
        defaultTableModel = new DefaultTableModel(rowsTable.toArray(new String[0][]), cols);
        tableServe.setModel(defaultTableModel);
        JScrollPane pane = new JScrollPane(tableServe);
        pane.setBounds(0, 100, 450, 200);

        button_change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DemoFrame demoFrame1 = new DemoFrame(450, 450);
                JLabel jlabel = new JLabel("本人信息修改", SwingConstants.CENTER);
                jlabel.setBounds(0, 0, 450, 30);
                jlabel.setFont(new Font("楷体", Font.BOLD, 25));
                JLabel label_number = new JLabel("联系方式", SwingConstants.CENTER);
                label_number.setBounds(50, 100, 150, 30);
                JTextField textField_number = new JTextField();
                textField_number.setText(getNumber());
                textField_number.setBounds(225, 100, 130, 30);
                JLabel label_account = new JLabel("账号", SwingConstants.CENTER);
                label_account.setBounds(50, 152, 150, 30);
                JTextField textField_account = new JTextField();
                textField_account.setText(getAccount());
                textField_account.setBounds(225, 152, 130, 30);
                JLabel label_password = new JLabel("密码", SwingConstants.CENTER);
                label_password.setBounds(50, 202, 150, 30);
                JPasswordField passwordField = new JPasswordField();
                passwordField.setText(getPassword());
                passwordField.setBounds(225, 202, 130, 30);
                JButton button_sure = new JButton("确认");
                button_sure.setBounds(56, 300, 120, 30);
                JButton button_quit = new JButton("返回");
                button_quit.setBounds(255, 300, 120, 30);
                button_sure.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if(textField_number.getText().length()!=11){
                                throw new DemoException("电话号码格式有误");
                            }//抛出异常(号码不是11位)
                            memberHashMap.get(getID()).setNumber(textField_number.getText());
                            memberHashMap.get(getID()).setAccount(textField_account.getText());
                            memberHashMap.get(getID()).setPassword(new String(passwordField.getPassword()));
                            //memberHashmap对应元素更新
                            setNumber(textField_number.getText());
                            setAccount(textField_account.getText());
                            setPassword(new String(passwordField.getPassword()));
                            File file = new File("Member.txt");
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                            for (String s : memberHashMap.keySet()) {
                                Member member = memberHashMap.get(s);
                                bufferedWriter.write(member.getID() + "," + member.getName() + "," + member.getNumber() + "," +
                                        member.getIdentity() + "," + member.getSex() + "," + member.getAccount() + "," +
                                        member.getPassword() + "," + member.getMonth() + "," + member.getDay() + "\n");
                            }//文件写入
                            bufferedWriter.close();
                            demoFrame1.getjFrame().setVisible(false);
                        } catch (Exception exception) {

                        }
                    }
                });
                button_quit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        demoFrame1.getjFrame().setVisible(false);
                    }
                });
                demoFrame1.getPanel().add(jlabel);
                demoFrame1.getPanel().add(label_number);
                demoFrame1.getPanel().add(textField_number);
                demoFrame1.getPanel().add(label_account);
                demoFrame1.getPanel().add(textField_account);
                demoFrame1.getPanel().add(label_password);
                demoFrame1.getPanel().add(passwordField);
                demoFrame1.getPanel().add(button_sure);
                demoFrame1.getPanel().add(button_quit);
                demoFrame1.getjFrame().setVisible(true);
            }
        });

        button_set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow[] = tableServe.getSelectedRows();
                    for (int i = 0; i < selectedRow.length; i++) {
                        rowsTable.set(selectedRow[i], new String[]{(String) defaultTableModel.getValueAt(selectedRow[i], 0), getName()});
                        tableHashMap.get((String) defaultTableModel.getValueAt(selectedRow[i], 0)).setAttendantID(getID());
                        defaultTableModel = new DefaultTableModel(rowsTable.toArray(new String[0][]), cols);
                        tableServe.setModel(defaultTableModel);
                    }
                    //表格,tableHashmap更新
                    File file = new File("Table.txt");
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    for (int i = 1; i <= 15; i++) {
                        if (tableHashMap.get(Integer.toString(i)).getAttendantID() == null)
                            bufferedWriter.write(i + "\n");
                        else
                            bufferedWriter.write(i + "," + tableHashMap.get(Integer.toString(i)).getAttendantID() + "\n");
                    }
                    //文件写入
                    bufferedWriter.close();
                } catch (Exception exception) {

                }

            }
        });

        button_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow[] = tableServe.getSelectedRows();
                    for (int i = 0; i < selectedRow.length;i++){
                        if(((String) defaultTableModel.getValueAt(selectedRow[i],1)).equals(getName())){
                            rowsTable.set(selectedRow[i], new String[]{(String) defaultTableModel.getValueAt(selectedRow[i], 0), "无"});
                            tableHashMap.get((String) defaultTableModel.getValueAt(selectedRow[i], 0)).setAttendantID(null);
                            defaultTableModel = new DefaultTableModel(rowsTable.toArray(new String[0][]), cols);
                            tableServe.setModel(defaultTableModel);
                        }
                    }
                    File file = new File("Table.txt");
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    for (int i = 1; i <= 15; i++) {
                        if (tableHashMap.get(Integer.toString(i)).getAttendantID() == null)
                            bufferedWriter.write(i + "\n");
                        else
                            bufferedWriter.write(i + "," + tableHashMap.get(Integer.toString(i)).getAttendantID() + "\n");
                    }
                    bufferedWriter.close();
                } catch (Exception exception) {

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
        demoFrame.getPanel().add(button_change);
        demoFrame.getPanel().add(button_set);
        demoFrame.getPanel().add(button_cancel);
        demoFrame.getPanel().add(button_quit);
        demoFrame.getPanel().add(pane);
        demoFrame.getjFrame().setVisible(true);
    }

    public void DataA() {
        try {
            tableHashMap = new HashMap<>();
            memberHashMap = new HashMap<>();
            rowsTable = new ArrayList<>();
            File file = new File("Table.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = bufferedReader.readLine();
            File file1 = new File("Member.txt");
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));
            String s1 = bufferedReader1.readLine();
            while (s1 != null) {
                String[] t = s1.split(",");
                memberHashMap.put(t[0], new Member(t[0], t[1], t[2], t[3], t[4], t[5], t[6], t[7], t[8]));
                s1 = bufferedReader1.readLine();
            }
            //文件读入，memberHashmap元素添加
            bufferedReader1.close();
            while (s != null) {
                String[] t = s.split(",");
                if (t.length == 1) {
                    rowsTable.add(new String[]{t[0], "无"});
                    tableHashMap.put(t[0], new Table(t[0], null));
                } else {
                    rowsTable.add(new String[]{t[0], memberHashMap.get(t[1]).getName()});
                    tableHashMap.put(t[0], new Table(t[0], memberHashMap.get(t[1]).getID()));
                }
                s = bufferedReader.readLine();
            }
            //文件读入，tableHashmap元素添加
        } catch (Exception e) {

        }
    }
}
