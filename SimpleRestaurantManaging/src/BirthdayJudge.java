

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BirthdayJudge {
    public JLabel Judge(Member member){
        Calendar calendar = new GregorianCalendar();
        int m = calendar.get(Calendar.MONTH)+1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        String s = "欢迎您, "+member.getName();
        JLabel label = new JLabel(s,SwingConstants.CENTER);
        if(m==Integer.parseInt(member.getMonth())&&d==Integer.parseInt(member.getDay()))
            label = new JLabel(s+", 祝您生日快乐",SwingConstants.CENTER);
        return label;
    }
}
