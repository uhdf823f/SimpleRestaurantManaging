

public class Table {
    //“桌”类
    private String index;
    private String attendantID;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAttendantID() {
        return attendantID;
    }

    public void setAttendantID(String attendantID) {
        this.attendantID = attendantID;
    }

    public Table(String index, String attendantName) {
        this.index = index;
        this.attendantID = attendantName;
    }
}

