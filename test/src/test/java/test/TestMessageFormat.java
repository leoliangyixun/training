/**
 * 
 */
package test;

import java.text.MessageFormat;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestMessageFormat {
	
	@Test
	public void test() {
		 MessageFormat mf = new MessageFormat("hello: {1}, this is {0}, nice to meet you");
		 String s = mf.format(new Object[]{"yangkai", "alisa"});
		 System.out.println(s);
		 mf = new MessageFormat("hello: {0}, this''s {1}, nice to meet you");
		 s = mf.format(new Object[]{"yangkai", "alisa"});
		 System.out.println(s); 
		 mf = new MessageFormat("{0}?access_token={1}");
		 String url = mf.format(new Object[]{"https://api.weixin.qq.com/cgi-bin/message/template/send", "ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY"});
		 System.out.println(url);


	}

	@Test
	public void test2() {
		String s = MessageFormat.format("hello:{0}", null);
		System.out.println(s);//result: hello:{0}
	}

}
