

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Manager extends Member {
    private HashMap<String, Member> memberHashMap;
    private HashMap<String, Table> tableHashMap;
    private ArrayList<String[]> rowsTable;
    private JTable tableAttendant;
    private DefaultTableModel defaultTableModel;
    private String cols[] = {"ID", "姓名", "联系方式", "性别"};


    public Manager(String ID, String name, String number, String identity, String sex, String account, String password, String month, String day) {
        super(ID, name, number, identity, sex, account, password, month, day);
    }

    public void ManagerFrame(Manager manager) {
        DemoFrame demoFrame = new DemoFrame(700, 400);
        JLabel jLabel = new BirthdayJudge().Judge(manager);
        jLabel.setFont(new Font("楷体", Font.BOLD, 25));
        jLabel.setBounds(0, 0, 700, 30);
        DataM();
        JButton button_change = new JButton("本人信息修改");
        button_change.setBounds(0, 65, 120, 30);
        JButton button_delete = new JButton("服务人员删除");
        button_delete.setBounds(200, 65, 120, 30);
        JButton button_quit = new JButton("返回");
        button_quit.setBounds(300, 320, 70, 30);
        JButton button_search = new JButton("查找特定桌对应服务员");
        button_search.setBounds(520, 65, 160, 30);
        tableAttendant = new JTable();
        defaultTableModel = new DefaultTableModel(rowsTable.toArray(new String[0][]), cols);
        tableAttendant.setModel(defaultTableModel);
        tableAttendant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane pane = new JScrollPane(tableAttendant);
        pane.setBounds(0, 100, 690, 200);

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
                            if(textField_number.getText().length()!=11)
                                throw new DemoException("电话号码格式有误");
                            memberHashMap.get(getID()).setNumber(textField_number.getText());
                            memberHashMap.get(getID()).setAccount(textField_account.getText());
                            memberHashMap.get(getID()).setPassword(new String(passwordField.getPassword()));
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
                            }
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

        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int k = tableAttendant.getSelectedRow();
                    String IDSelect = (String) defaultTableModel.getValueAt(k, 0);
                    //得到要删除的服务人员ID
                    for (String s : tableHashMap.keySet()) {
                        if(tableHashMap.get(s).getAttendantID()!=null&&tableHashMap.get(s).getAttendantID().equals(IDSelect)){
                            tableHashMap.get(s).setAttendantID(null);
                        }
                    }
                    //tableHashmap中服务人员为将要删去的，将对应Table对象的attendantID设置为空
                    File file1 = new File("Table.txt");
                    BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(file1));
                    for(int i=1;i<=15;i++) {
                        if (tableHashMap.get(Integer.toString(i)).getAttendantID() == null)
                            bufferedWriter1.write(i + "\n");
                        else
                            bufferedWriter1.write(i + "," + tableHashMap.get(Integer.toString(i)).getAttendantID() + "\n");
                    }
                    bufferedWriter1.close();
                    memberHashMap.remove(IDSelect);//memberHashmap元素删除
                    File file = new File("Member.txt");
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    for (String s1 : memberHashMap.keySet()) {
                        Member member = memberHashMap.get(s1);
                        bufferedWriter.write(member.getID() + "," + member.getName() + "," + member.getNumber() + "," +
                                member.getIdentity() + "," + member.getSex() + "," + member.getAccount() + "," +
                                member.getPassword() + "," + member.getMonth() + "," + member.getDay() + "\n");
                    }
                    bufferedWriter.close();
                    defaultTableModel.removeRow(k);
                    tableAttendant.setModel(defaultTableModel);
                    //表格更新
                } catch (Exception exception) {

                }

            }
        });

        button_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DemoFrame demoFrame1 = new DemoFrame(350, 250);
                JLabel Label = new JLabel("特定桌对应服务员查看",SwingConstants.CENTER);
                Label.setBounds(0, 0, 350, 30);
                Label.setFont(new Font("楷体", Font.BOLD, 25));
                JLabel label_index = new JLabel("请选择桌号",SwingConstants.CENTER);
                label_index.setBounds(50, 60, 120, 30);
                JComboBox comboBox = new JComboBox<>();
                for (int i = 1; i <= 15; i++){
                    comboBox.addItem(Integer.toString(i));
                }
                comboBox.setBounds(180,60,60,30);
                JLabel label_name = new JLabel("该桌服务员为",SwingConstants.CENTER);
                label_name.setBounds(50,120,120,30);
                JTextField textField_name = new JTextField();
                textField_name.setBounds(180,120,120,30);
                JButton button_sure = new JButton("查找");
                button_sure.setBounds(80,180,60,30);
                JButton button_quit = new JButton("返回");
                button_quit.setBounds(200,180,60,30);

                button_sure.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String index =  (String)comboBox.getSelectedItem();
                        for (String s : tableHashMap.keySet()) {
                            if(s.equals(index)){
                                if(tableHashMap.get(s).getAttendantID()!=null){
                                    textField_name.setText(memberHashMap.get(tableHashMap.get(s).getAttendantID()).getName());
                                }
                                else textField_name.setText("无");
                                //循环遍历tableHashMap中的元素进行寻找
                            }
                        }
                    }
                });

                button_quit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        demoFrame1.getjFrame().setVisible(false);
                    }
                });


                demoFrame1.getPanel().add(Label);
                demoFrame1.getPanel().add(label_index);
                demoFrame1.getPanel().add(comboBox);
                demoFrame1.getPanel().add(label_name);
                demoFrame1.getPanel().add(textField_name);
                demoFrame1.getPanel().add(button_sure);
                demoFrame1.getPanel().add(button_quit);
                demoFrame1.getjFrame().setVisible(true);
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
        demoFrame.getPanel().add(button_delete);
        demoFrame.getPanel().add(button_quit);
        demoFrame.getPanel().add(button_search);
        demoFrame.getPanel().add(pane);
        demoFrame.getjFrame().setVisible(true);
    }

    public void DataM() {
        try {
            File file = new File("Member.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = bufferedReader.readLine();
            rowsTable = new ArrayList<>();
            memberHashMap = new HashMap<>();
            tableHashMap = new HashMap<>();
            while (s != null) {
                String[] t = s.split(",");
                if (t[3].equals("服务员")) {
                    rowsTable.add(new String[]{t[0], t[1], t[2], t[4]});
                }
                memberHashMap.put(t[0], new Member(t[0], t[1], t[2], t[3], t[4], t[5], t[6], t[7], t[8]));
                s = bufferedReader.readLine();
            }
            File file1 = new File("Table.txt");
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));
            String s1 = bufferedReader1.readLine();
            while (s1 != null) {
                String[] t = s1.split(",");
                if (t.length == 1) {
                    tableHashMap.put(t[0], new Table(t[0], null));
                } else {
                    tableHashMap.put(t[0], new Table(t[0], memberHashMap.get(t[1]).getID()));
                }
                s1 = bufferedReader1.readLine();
            }
            bufferedReader1.close();
        } catch (Exception e) {

        }
    }
}
