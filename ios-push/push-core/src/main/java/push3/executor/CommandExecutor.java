package push3.executor;

/**
 * @author yangkai
 * @date 2018/4/19
 * @email yangkai@hujiang.com
 * @description
 */
public interface CommandExecutor<T> {
     void execute(T target, Command<T> command);
}
