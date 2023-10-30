

public class Member {
    private String ID;
    private String name;
    private String number;
    private String identity;
    private String sex;
    private String account;
    private String password;
    private String month;
    private String day;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Member(String ID, String name, String number, String identity, String sex, String account,
                  String password, String month, String day) {
        this.ID = ID;
        this.name = name;
        this.number = number;
        this.identity = identity;
        this.sex = sex;
        this.account = account;
        this.password = password;
        this.month = month;
        this.day = day;
    }
}
