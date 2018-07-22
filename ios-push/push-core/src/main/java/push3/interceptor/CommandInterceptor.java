package push3.interceptor;

import com.hujiang.notifycenter.push.core.executor.Command;

/**
 * @author yangkai
 * @date 2018/4/15
 * @email yangkai@hujiang.com
 * @version
 * @description
 */
public interface CommandInterceptor<T> {

    void execute(T target, Command<T> command);

    CommandInterceptor<T> next(CommandInterceptor<T> next);
}
