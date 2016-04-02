/**
 * 
 */
package generic;

/**
 * @author izene
 *
 */
public class CommandTest {
	private Command cmd;
	/**
	 * 
	 */
	public String test() {
		cmd = new MyCommand();
		User user = new User();
		User u = cmd.execute(user);
		return u.getName();
	}
	
	public static void main(String[] args) {
		CommandTest test = new CommandTest();
		test.test();
	}

}
