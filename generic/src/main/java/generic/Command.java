/**
 * 
 */
package generic;

/**
 * @author izene
 *
 */
public interface Command {
	<T> T execute(T t);

}
