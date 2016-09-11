/**
 * 
 */
package com.b5m.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author izene
 *
 */
public class CalendarTest {

    /**
     * 
     */
    public CalendarTest() {
        // TODO Auto-generated constructor stub
    }
    
    public static void main(String[] args) throws ParseException {
        
/*        int minute = 20;
       
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, -minute);
        Date end = c.getTime();
        c.add(Calendar.MINUTE, -minute);
        Date start = c.getTime();
        System.out.println("start:" + start );
        System.out.println("end:" + end);*/
        
/*        Calendar c = Calendar.getInstance();
        //c.add(Calendar.DATE, -1);
        c.add(Calendar.DAY_OF_MONTH, -1);

        System.out.println(c.getTime());*/
        /******************************************************************************/
/*        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        System.out.println(sdf.format(new Date()));*/
        /******************************************************************************/
  
    	    long cur = System.currentTimeMillis();
    	    Calendar c = Calendar.getInstance();
    	    c.add(Calendar.DATE, 1);
    	    c.set(Calendar.HOUR_OF_DAY, 0);
    	    c.set(Calendar.MINUTE, 0);
    	    c.set(Calendar.SECOND, 0);
    	    c.set(Calendar.MILLISECOND, 0);
    	    Date d = c.getTime();
    	    System.out.println(d);
    	    
    	    
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	    Date d2 = sdf.parse("2016-03-14 23:59:50");
    	    d2.getTime();
    	    
    	    
    	    long re =(c.getTimeInMillis() - d2.getTime()) / 1000;
    	    System.out.println(re);
    	    

    }

}
