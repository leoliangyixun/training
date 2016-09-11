/**
 * //TODO
 */
package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * //TODO
 * @author Lawrence.Yang
 * @email jiqingchuan@b5m.com
 * @version 2015年12月3日 上午10:46:32
 */
public class DateTest3 {

    /**
     * //TODO
     */
    public DateTest3() {
        // TODO Auto-generated constructor stub
    }
    
    public static void main(String[] args) {
        long cur = System.currentTimeMillis();
        
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        int s = (int)((c.getTimeInMillis() - cur) / 1000);
       System.out.println(s);
       System.out.println("-----------------------------------------------------------");
       Calendar c2 = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(c2
                .get(Calendar.YEAR), c.get(Calendar.MONTH), c2
                .get(Calendar.DATE) + 1, 0, 0, 0);
       int re = (int)(tommorowDate.getTimeInMillis() - c2 .getTimeInMillis()) / 1000;
       System.out.println(re);
    }

}
