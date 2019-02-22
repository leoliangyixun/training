/**
 * 
 */
package test;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author izene
 *
 */
public class TestCalendar {

    /**
     * 
     */
    public TestCalendar() {
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

    @Test
	public void test() {
		Date d = Calendar.getInstance().getTime();
		System.out.println(d.getTime());
	}

	@Test
	public void test2() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date now = df.parse(df.format(new Date()));
		Date begin = df.parse("7:00");
		Date end = df.parse("18:30");
		System.out.println("now" + now);
		System.out.println("begin" + begin);
		System.out.println("end" + end);


		Calendar nowTime = Calendar.getInstance();
		nowTime.setTime(now);
		Calendar beginTime = Calendar.getInstance();
		beginTime.setTime(begin);
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(end);
		System.out.println(nowTime.before(endTime) );
		System.out.println(nowTime.after(beginTime));
		System.out.println(now.equals(end));
		System.out.println(now==end);
	}

	@Test
	public void test3() {
		Calendar c = Calendar.getInstance();
		//c.set(Calendar.YEAR, 2013);
		//c.set(Calendar.MONTH, 1);
		System.out.println("------------" + c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) +"月" + c.get(Calendar.DAY_OF_MONTH) + "日" + "-------------");
		System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println(c.getActualMaximum(Calendar.DATE));
	}

}
