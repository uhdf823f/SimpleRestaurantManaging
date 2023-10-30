

public class Time {
    //时间类，统一规定便于处理操作
    private static Time time;
    private int month[];
    private int day[];

    public int[] getMonth() {
        return month;
    }

    public int[] getDay() {
        return day;
    }

    private Time() {
        month = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12};
        day = new int[]{0,31,29,31,30,31,30,31,31,30,31,30,31};
    }
    public static Time getTime(){
        if(time == null) {
            time = new Time();
        }
        return time;
    }
}
