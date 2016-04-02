package test;

import java.util.Calendar;
import java.util.Date;

public class DateTest2 {

    public DateTest2() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {       
   
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date d1 = c.getTime();
        System.out.println(d1);
/*        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Date d2 = c.getTime();
        System.out.println(d2);

        Date now = new Date();
        System.out.println(now);
        System.out.println(now.after(d1));
        System.out.println(now.before(d2));*/

    }

}
