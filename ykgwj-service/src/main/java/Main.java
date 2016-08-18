import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 */

/**
 * @author yangkai
 *
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//StringJoiner
		String[] ss = StringUtils.split("status,,,,,,createTime,updateTime", ",");
		for (String s : ss) {
		System.out.println(s);
		}

	}

}
