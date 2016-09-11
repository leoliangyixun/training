package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    public DateTest() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
/*        Date now = new Date();
        System.out.println(new SimpleDateFormat("yyyy年MM月dd日HH:mm").format(now));*/
        int minute = 30;
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        c.add(Calendar.MINUTE, -minute);
        Date end = c.getTime();
        c.add(Calendar.MINUTE, -minute);
        Date start = c.getTime();
        System.out.println(date);
        System.out.println(end);
        System.out.println(start);

    }

}
